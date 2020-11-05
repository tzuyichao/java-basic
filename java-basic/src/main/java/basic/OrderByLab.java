package basic;

public class OrderByLab {
    public static void main(String[] args) {
        try {
            OrderBy.valueOf("desc");
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println(OrderBy.getEnumByName("desc"));
    }
}
