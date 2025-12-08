package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.acl.*;
import org.apache.kafka.common.resource.PatternType;
import org.apache.kafka.common.resource.ResourcePatternFilter;
import org.apache.kafka.common.resource.ResourceType;
import picocli.CommandLine;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Delete Acl & SCRAM user
 */
@CommandLine.Command(name = "delete-scram-user", version = "delete user 1.0", mixinStandardHelpOptions = true,
                     description = "Delete acl & scram user")
public class DeleteScramUser implements Callable<Integer> {
    @CommandLine.Option(
            names = "--user",
            required = true,
            description = "Kafka username (without 'User:' prefix)."
    )
    String username;

    @CommandLine.Option(
            names = "--dry-run",
            description = "Show what will be deleted, but do not perform deletion."
    )
    boolean dryRun;

    @CommandLine.Option(
            names = {"-y", "--yes"},
            description = "Non-interactive mode. Automatically confirm deletions."
    )
    boolean yes;

    @CommandLine.Option(
            names = {"-s", "--silent", "--quiet"},
            description = "Silent mode. Only errors will be printed."
    )
    boolean silent;

    @Override
    public Integer call() throws Exception {
        Dotenv dotenv = Dotenv.load();
        Properties props = new Properties();
        props.put("bootstrap.servers", dotenv.get("BOOTSTRAP_SERVER"));
        props.put("fetch.max.bytes", 1024);
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", 1000);
        // SECURITY_PROTOCOL=SASL_PLAINTEXT
        props.put("security.protocol", dotenv.get("SECURITY_PROTOCOL"));
        // SASL_MECHANISM=SCRAM-SHA-512
        props.put("sasl.mechanism", dotenv.get("SASL_MECHANISM"));
        props.put("auto.offset.reset","earliest");
        props.put("sasl.jaas.config", dotenv.get("JAAS"));

        if (!silent) {
            System.out.printf("Target user: %s%n", username);
            System.out.printf("Bootstrap servers: %s%n", dotenv.get("BOOTSTRAP_SERVER"));
        }
        try (AdminClient admin = AdminClient.create(props)) {
            if (dryRun) {
                return runDryRun(admin);
            } else {
                return runDeleteFlow(admin);
            }
        } catch (Exception e) {
            error("Unexpected error: " + e.getMessage());
            e.printStackTrace(System.err);
            return 1;
        }
    }

    private int runDryRun(AdminClient admin) throws ExecutionException, InterruptedException {
        if (!silent) {
            System.out.println("=== DRY RUN (no changes will be applied) ===");
        }

        List<AclBinding> acls = listAclsForUser(admin, username);
        printAclList(acls);

        UserScramCredentialsDescription scram = describeScramUser(admin, username);
        printScramInfo(scram);

        if (!silent) {
            System.out.println("\nDry-run completed. No ACLs or SCRAM credentials were deleted.");
        }
        return 0;
    }

    private int runDeleteFlow(AdminClient admin) throws ExecutionException, InterruptedException {
        // 先列出 ACL / SCRAM，讓使用者看清楚
        List<AclBinding> acls = listAclsForUser(admin, username);
        UserScramCredentialsDescription scram = describeScramUser(admin, username);

        if (!silent) {
            System.out.println("=== Planned deletions ===");
            printAclList(acls);
            printScramInfo(scram);
            System.out.println();
        }

        // 沒給 --yes，就問一次
        if (!yes) {
            if (!confirmFromStdin("Proceed with deletion? (y/N): ")) {
                if (!silent) {
                    System.out.println("Aborted by user.");
                }
                return 1;
            }
        }

        // 真的刪除
        deleteAcls(admin, username, acls);
        deleteScram(admin, username, scram);

        if (!silent) {
            System.out.println("Deletion completed.");
        }
        return 0;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // ACL / SCRAM helper methods
    // ─────────────────────────────────────────────────────────────────────────

    private static List<AclBinding> listAclsForUser(AdminClient admin, String username)
            throws ExecutionException, InterruptedException {

        String principal = "User:" + username;

        AclBindingFilter filter = new AclBindingFilter(
                new ResourcePatternFilter(ResourceType.ANY, null, PatternType.ANY),
                new AccessControlEntryFilter(
                        principal,
                        null,
                        AclOperation.ANY,
                        AclPermissionType.ANY
                )
        );

        DescribeAclsResult result = admin.describeAcls(filter);
        return new ArrayList<>(result.values().get());
    }

    private static UserScramCredentialsDescription describeScramUser(AdminClient admin, String username)
            throws ExecutionException, InterruptedException {

        DescribeUserScramCredentialsResult result =
                admin.describeUserScramCredentials(List.of(username));

        Map<String, UserScramCredentialsDescription> map = result.all().get();
        return map.get(username); // 可能是 null
    }

    private void deleteAcls(AdminClient admin, String username, List<AclBinding> acls)
            throws ExecutionException, InterruptedException {

        if (acls.isEmpty()) {
            if (!silent) {
                System.out.println("No ACLs found for user. Skipping ACL deletion.");
            }
            return;
        }

        String principal = "User:" + username;

        AclBindingFilter filter = new AclBindingFilter(
                new ResourcePatternFilter(ResourceType.ANY, null, PatternType.ANY),
                new AccessControlEntryFilter(
                        principal,
                        null,
                        AclOperation.ANY,
                        AclPermissionType.ANY
                )
        );

        if (!silent) {
            System.out.printf("Deleting %d ACL(s) for %s...%n", acls.size(), principal);
        }

        DeleteAclsResult result = admin.deleteAcls(List.of(filter));
        result.all().get();
        if (!silent) {
            System.out.println("ACL deletion finished.");
        }
    }

    private void deleteScram(AdminClient admin, String username, UserScramCredentialsDescription scram)
            throws ExecutionException, InterruptedException {

        if (scram == null || scram.credentialInfos().isEmpty()) {
            if (!silent) {
                System.out.println("No SCRAM credentials found for user. Skipping SCRAM deletion.");
            }
            return;
        }

        List<UserScramCredentialAlteration> deletions = scram.credentialInfos().stream()
                .map(info -> (UserScramCredentialAlteration)
                        new UserScramCredentialDeletion(username, info.mechanism()))
                .toList();

        if (!silent) {
            System.out.printf("Deleting SCRAM credentials for user %s (mechanisms: %s)...%n",
                    username, scram.credentialInfos());
        }

        AlterUserScramCredentialsResult result =
                admin.alterUserScramCredentials(deletions);
        result.all().get();

        if (!silent) {
            System.out.println("SCRAM credential deletion finished.");
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Printing / confirm helpers
    // ─────────────────────────────────────────────────────────────────────────

    private void printAclList(List<AclBinding> acls) {
        if (silent) {
            return;
        }

        System.out.println("ACLs:");
        if (acls.isEmpty()) {
            System.out.println("  (no ACLs found)");
            return;
        }
        for (AclBinding acl : acls) {
            System.out.println("  " + acl);
        }
    }

    private void printScramInfo(UserScramCredentialsDescription scram) {
        if (silent) {
            return;
        }

        System.out.println("SCRAM credentials:");
        if (scram == null || scram.credentialInfos().isEmpty()) {
            System.out.println("  (no SCRAM credentials)");
        } else {
            scram.credentialInfos().forEach(info ->
                    System.out.printf("  mechanism=%s, iterations=%d%n",
                            info.mechanism(), info.iterations()));
        }
    }

    private boolean confirmFromStdin(String prompt) {
        if (silent) {
            // silent 但沒有 --yes：還是應該問，只是少講話
            System.out.print(prompt);
        } else {
            System.out.print(prompt);
        }
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line != null && (line.equalsIgnoreCase("y") || line.equalsIgnoreCase("yes"));
    }

    private void error(String msg) {
        System.err.println("[ERROR] " + msg);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new DeleteScramUser()).execute(args);
        System.exit(exitCode);
    }
}
