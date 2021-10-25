package invoice.domain;

public class Customer {
    private double total = 0.0;
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    public void addToTotal(double amount) {
        this.total += amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "total=" + total +
                ", name='" + name + '\'' +
                '}';
    }
}
