import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by timbuchalka on 20/11/16.
 */
public class BankAccountTest2 {

    private BankAccount account;
    private static int count;

    // Beforeclass annotation is used to execute a method before any test cases
    @org.junit.BeforeClass
    public static void beforeClass() {
        System.out.println("This executes before any test cases. Count = " + count++);
    }

    // Before annotation is used to execute a method before each test cases
    @org.junit.Before
    public void setup() {
        account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        System.out.println("Running a test...");
    }

    @org.junit.Test
    public void deposit() throws Exception {
        double balance = account.deposit(200.00, true);
        assertEquals(1200.00, balance, 0);
    }

    @org.junit.Test
    public void withdraw_branch() throws Exception {
        double balance = account.withdraw(600.00, true);
        assertEquals(400.00, balance, 0);
    }

    // if this method throws illegal argument exception, the test will pass
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void withdraw_notBranch() throws Exception {
        account.withdraw(600.00, false);

        // dont need this because the test will fail if the exception is not thrown
        // double balance = account.withdraw(600.00, false);
        // assertEquals(400.00, balance, 0);
    }

    // it is same as above method
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void withdraw_notBranch2() throws Exception {
        try {
            account.withdraw(600.00, false);
        } catch (IllegalArgumentException e) {
            
        }

    }

    @org.junit.Test
    public void getBalance_deposit() throws Exception {
        account.deposit(200.00, true);
        assertEquals(1200.00, account.getBalance(), 0);
    }

    @org.junit.Test
    public void getBalance_withdraw() throws Exception {
        account.withdraw(200.00, true);
        assertEquals(800.00, account.getBalance(), 0);
    }

    @org.junit.Test
    public void isChecking_true() {
        assertTrue("The account is NOT a checking account", account.isChecking());
    }

    // Afterclass annotation is used to execute a method after test cases are
    // executed
    @org.junit.AfterClass
    public static void afterClass() {
        System.out.println("This executes after any test cases. Count = " + count++);
    }

    // After annotation is used to execute a method after each test cases
    @org.junit.After
    public void teardown() {
        System.out.println("Count = " + count++);
    }

}