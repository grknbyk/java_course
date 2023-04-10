import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class BankAccountTest1 {
    @org.junit.Test
    public void dummyTest1() {
        // This test will always pass
    }

    @org.junit.Test
    public void dummyTest2() {
        fail();
        // This test will always fail
    }

    // NOTE: assertEquals is uses equals method to compare two objects
    // so, consider pass by value or pass by reference
    @org.junit.Test
    public void dummyTest3() {
        // This test will always pass

        // param1 is the expected value
        // param2 is the actual value
        assertEquals(20, 20);
    }

    @org.junit.Test
    public void dummyTest4() {
        // This test will always fail
        assertEquals(20, 21);
    }

    // NOTE: delta is the acceptable difference between the expected and actual
    // values. if the difference is less than delta, the test will pass
    // for example, if the expected value is 20.00 and the actual value is 20.01 and
    // delta is 0.1
    // the difference is 0.01 which is less than delta, so the test will pass
    // expected value difference should be less than delta or equal to delta
    @org.junit.Test
    public void deposit() throws Exception {
        BankAccount account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        double balance = account.deposit(200.00, true);
        assertEquals(1200.00, balance, 0);
    }

    @org.junit.Test
    public void withdraw() throws Exception {
        fail("This test has yet to be implemented");
    }

    @org.junit.Test
    public void getBalance_deposit() throws Exception {
        BankAccount account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        account.deposit(200.00, true);
        assertEquals(1200.00, account.getBalance(), 0);
        // delta 0 means the expected and actual values should be equal
    }
    
    @org.junit.Test
    public void getBalance_withdraw() throws Exception {
        BankAccount account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        account.withdraw(200.00, true);
        assertEquals(800.00, account.getBalance(), 0);
        // delta 0 means the expected and actual values should be equal
    }

    @org.junit.Test
    public void isChecking_true() {
        BankAccount account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        assertEquals(true, account.isChecking());
        // or use assertTrue
        // assertTrue(account.isChecking());
        // assertTrue("isChecking account: fail message", account.isChecking());
        // print fail message if the test fails
    }

}
