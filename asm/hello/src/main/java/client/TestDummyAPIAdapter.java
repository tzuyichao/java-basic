package client;

import hello.APIResponse;
import hello.DummyAPIAdapter;

public class TestDummyAPIAdapter {
    public static void main(String[] args) {
        DummyAPIAdapter apiAdapter = new DummyAPIAdapter();
        APIResponse response = apiAdapter.createTerm("test");
        System.out.println("Response is: " + response.getMessage());
    }
}
