package org.acme.quickstart.interceptor;

public class Event {
    private String methodName;
    private String parameters;

    public Event(String methodName, String parameters) {
        this.methodName = methodName;
        this.parameters = parameters;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "Event{" +
                "methodName='" + methodName + '\'' +
                ", parameters='" + parameters + '\'' +
                '}';
    }
}
