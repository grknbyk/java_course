/**
 * lambda
 */
public class thread_lambda {

    public static void main(String[] args) {

        // without lambda
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" Printing from the Runnable ");
            }
        }).start();

        // with lambda
        new Thread(() -> System.out.println(" Printing from the Runnable ")).start();

        // with lambda multiple lines
        new Thread(() -> {
            System.out.println(" Printing from Runnable");
            System.out.println(" Line 2");
            System.out.format(" This is line &d\n", 3);
        }).start();

    }
}