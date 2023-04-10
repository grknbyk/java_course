public enum Topping {
    MUSTARD,
    PICKLES,
    BACON,
    CHEDDAR,
    TOMATO;

    public double getPrice() {

        return switch (this) {
            case BACON -> 1.50;
            case CHEDDAR -> 1.00;
            case TOMATO -> 0.50;
            default -> 0.00;
        };
    }
}
