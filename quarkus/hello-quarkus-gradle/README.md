# Memo

<code>
mvn io.quarkus:quarkus-maven-plugin:2.3.0.Final:create -DprojectGroupId=org.acme -DprojectArtifactId=getting-started -DclassName="org.acme.quickstart.GreetingResource" -Dpath="/hello" -DbuildTool=gradle
</code>

跟Quarkus Cookbook不同的是新版plugin預設會以artifactId建立目錄

<code>
gradlew quarkusDev -Dquarkus.http.port=8081
</code>

![Alt text](/curl.png "curl example")