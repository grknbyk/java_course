public class challenge1 {
    public static void main(String[] args) {

        // without lambda
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String myString = " Let's split this up into an array ";
                String[] parts = myString.split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };

        // with lambda
        Runnable runnable2 = () -> {
            String myString = " Let's split this up into an array ";
            String[] parts = myString.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };
    }
}
