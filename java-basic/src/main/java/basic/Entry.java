package basic;

import java.io.Serializable;

/**
 * https://www.baeldung.com/java-generic-constructors
 */
public class Entry {
    private String data;
    private int rank;

    public <E extends Rankable & Serializable> Entry(E element) {
        this.data = element.toString();
        this.rank = element.getRank();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
