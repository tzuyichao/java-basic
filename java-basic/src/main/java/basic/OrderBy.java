package basic;

public enum OrderBy {
    DESC, ASC;

    public static OrderBy getEnumByName(String name) {
        for(OrderBy orderBy: OrderBy.values()) {
            if(orderBy.name().equalsIgnoreCase(name)) {
                return orderBy;
            }
        }
        throw new IllegalArgumentException("no enum found");
    }
}
