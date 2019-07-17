package org.greenrivers.model;

public class Term {
    private String content;

    public Term(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Term{" +
                "content='" + content + '\'' +
                '}';
    }
}
