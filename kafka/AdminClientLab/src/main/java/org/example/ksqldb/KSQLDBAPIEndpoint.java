package org.example.ksqldb;

public enum KSQLDBAPIEndpoint {
    INFO("/info"),
    HEALTH_CHECK("/healthcheck"),
    KSQL("/ksql");

    private final String endpoint;
    private KSQLDBAPIEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
