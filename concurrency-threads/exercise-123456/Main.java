public class Main {
  public static void main(String[] args) {
    BankAccount account = new BankAccount("123456-786",1000.0);

    Thread customer1 = new Thread(new Runnable() {
        public void run() {
        account.deposit(300);
        account.withdraw(50);
        }
    });

    Thread customer2 = new Thread(new Runnable() {
        public void run() {
        account.deposit(203.75);
        account.withdraw(100);
        }
    });

    customer1.start();
    customer2.start();
    }  
}
