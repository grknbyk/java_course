import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class BankAccountTestParameterized {

    private BankAccount account;
    private double amount;
    private boolean branch;
    private double expected;

    public BankAccountTestParameterized(double amount, boolean branch, double expected) {
        this.amount = amount;
        this.branch = branch;
        this.expected = expected;
    }

    // parameters annotation is used to pass parameters to the test
    @Parameterized.Parameters
    public static Collection<Object[]> testConditions() {
        return Arrays.asList(new Object[][] {
                // amount, branch, expected
                { 100.00, true, 1100.00 },
                { 200.00, true, 1200.00 },
                { 325.14, true, 1325.14 },
                { 489.33, true, 1489.33 },
                { 1000.00, true, 2000.00 }
        });

    }

    @org.junit.Before
    public void setup() {
        account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        System.out.println("Running a test...");
    }

    @org.junit.Test
    public void deposit() throws Exception {
        account.deposit(amount, branch);
        assertEquals(expected, account.getBalance(), .01);
    }

}
