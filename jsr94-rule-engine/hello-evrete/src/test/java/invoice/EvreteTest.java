package invoice;

import invoice.domain.Customer;
import invoice.domain.Invoice;
import org.evrete.KnowledgeService;
import org.evrete.api.Knowledge;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * https://www.baeldung.com/java-evrete-rule-engine part 1
 */
public class EvreteTest {
    private List<Customer> makeCustomers() {
        List<Customer> customers = Arrays.asList(
                new Customer("Customer A"),
                new Customer("Customer B"),
                new Customer("Customer C")
        );
        return customers;
    }

    private Collection<Object> makeSessionData(List<Customer> customers) {
        Random random = new Random();
        Collection<Object> sessionData = new LinkedList<>(customers);
        for(int i=0; i<100_000; i++) {
            Customer randomCustomer = customers.get(random.nextInt(customers.size()));
            Invoice invoice = new Invoice(randomCustomer, 100*random.nextDouble());
            sessionData.add(invoice);
        }
        return sessionData;
    }

    @Test
    public void simple() {
        KnowledgeService service = new KnowledgeService();
        Knowledge knowledge = service.newKnowledge()
                .newRule("Clear total sales")
                .forEach("$c", Customer.class)
                .execute(ctx -> {
                    Customer c = ctx.get("$c");
                    c.setTotal(0.0);
                })
                .newRule("Compute totals")
                .forEach("$c", Customer.class, "$i", Invoice.class)
                .where("$i.customer == $c")
                .execute(ctx -> {
                    Customer c = ctx.get("$c");
                    Invoice i = ctx.get("$i");
                    c.addToTotal(i.getAmount());
                });

        List<Customer> customers = makeCustomers();
        Collection<Object> sessionData = makeSessionData(customers);

        knowledge
                .newStatelessSession()
                .insert(sessionData)
                .fire();

        for(Customer c: customers) {
            System.out.printf("%s:\t$%,.2f%n", c.getName(), c.getTotal());
        }
    }

    @Test
    public void test_dsl_java() throws IOException {
        ClassLoader classLoader = EvreteTest.class.getClassLoader();
        KnowledgeService service = new KnowledgeService();
        URL rulesetUrl = classLoader.getResource("rules/SalesRuleset.java");
        Knowledge knowledge = service.newKnowledge("JAVA-SOURCE", rulesetUrl);

        List<Customer> customers = makeCustomers();
        Collection<Object> sessionData = makeSessionData(customers);

        knowledge
                .newStatelessSession()
                .insert(sessionData)
                .fire();

        for(Customer c: customers) {
            System.out.printf("%s:\t$%,.2f%n", c.getName(), c.getTotal());
        }
    }
}
