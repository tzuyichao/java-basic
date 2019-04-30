package basic;

import java.math.BigDecimal;
import java.util.Objects;

public class Node {
    private int id;
    private BigDecimal weight = BigDecimal.ZERO;

    public Node() {}

    public Node(int id, BigDecimal weight) {
        this.id = id;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id &&
                weight.equals(node.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weight);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", weight=" + weight +
                '}';
    }
}
