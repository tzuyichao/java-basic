package com.example.rest.beanparam;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public class ParamBean {
    private int id;

    @QueryParam("id")
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @HeaderParam("X-SomeHeader")
    private String someHeaderValue;

    public String getSomeHeaderValue() {
        return someHeaderValue;
    }

    public void setSomeHeaderValue(String someHeaderValue) {
        this.someHeaderValue = someHeaderValue;
    }

    @PathParam("path")
    public String pathParamValue;

    @Override
    public String toString() {
        return "ID: " + id + " X-SomeHeader: " + someHeaderValue + " path: " + pathParamValue;
    }
}
