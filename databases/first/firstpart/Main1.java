import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Main
 */
public class Main1 {

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:firstpart\\testjava.db");
            // conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            // statement.execute("CREATE TABLE IF NOT EXISTS contacts "
            //         + "(name TEXT, phone INTEGER, email TEXT)");

            // statement.execute("INSERT INTO contacts (name, phone, email) "
            //         + "VALUES('Brad', 433232, 'brad@email.com')");
            // statement.execute("INSERT INTO contacts (name, phone, email) "
            //         + "VALUES('Erick', 983484, 'erick@email.com')");
            // statement.execute("INSERT INTO contacts (name, phone, email) "
            //         + "VALUES('Davis', 23121, 'davis@email.com')");

            //statement.execute("UPDATE contacts SET phone=1491839 WHERE name='Erick'");

            // statement.execute("DELETE FROM contacts WHERE name='Joe'");

            // statement.execute("SELECT * FROM contacts");
            // ResultSet results = statement.getResultSet();

            ResultSet results = statement.executeQuery("SELECT * FROM contacts");
            while (results.next()) {
                System.out.println(results.getString("name") + " " +
                        results.getInt("phone") + " " +
                        results.getString("email"));
            }
            results.close();

            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}