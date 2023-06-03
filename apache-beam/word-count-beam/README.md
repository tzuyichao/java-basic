
```
mvn compile exec:java -D exec.mainClass=org.apache.beam.examples.WordCount `
     -D exec.args="--inputFile=pg13549.txt --output=counts" -P direct-runner
```

* [WordCount quickstart for Java](https://beam.apache.org/get-started/quickstart-java/)