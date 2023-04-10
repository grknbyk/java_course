import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Main
 */
public class Main2 {

    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:firstpart\\" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    " (" + COLUMN_NAME + " text, " +
                    COLUMN_PHONE + " integer, " +
                    COLUMN_EMAIL + " text" +
                    ")");

            // statement.execute("INSERT INTO " + TABLE_CONTACTS +
            //         " (" + COLUMN_NAME + ", " +
            //         COLUMN_PHONE + ", " +
            //         COLUMN_EMAIL +
            //         ") " +
            //         "VALUES('Brad', 433232, 'brad@email.com')");
            // statement.execute("INSERT INTO " + TABLE_CONTACTS +
            //         " (" + COLUMN_NAME + ", " +
            //         COLUMN_PHONE + ", " +
            //         COLUMN_EMAIL +
            //         ") " +
            //         "VALUES('Joe', 7366374, 'joe@gmail.com')");
            // statement.execute("INSERT INTO " + TABLE_CONTACTS +
            //         " (" + COLUMN_NAME + ", " +
            //         COLUMN_PHONE + ", " +
            //         COLUMN_EMAIL +
            //         ") " +
            //         "VALUES('David', 293842, 'david@iclud.com')");
            // statement.execute("INSERT INTO " + TABLE_CONTACTS +
            //         " (" + COLUMN_NAME + ", " +
            //         COLUMN_PHONE + ", " +
            //         COLUMN_EMAIL +
            //         ") " +
            //         "VALUES('Jane', 43142342, 'jane@iclud.com')");

            insertContact(statement, "Brad" , 43323221, "brad@email.com" );
            insertContact(statement, "Joe"  , 73663742, "joe@email.com"  );
            insertContact(statement, "David", 29384213, "david@iclod.com");
            insertContact(statement, "Jane" , 43142342, "jane@iclud.com" );

            statement.execute("UPDATE " + TABLE_CONTACTS
                    + " SET " + COLUMN_PHONE + "=8373834 "
                    + "WHERE " + COLUMN_NAME + "='Jane'");

            statement.execute("DELETE FROM " + TABLE_CONTACTS
                    + " WHERE " + COLUMN_NAME + "='Joe'");

            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            while (results.next()) {
                System.out.println(results.getString(COLUMN_NAME) + " " +
                        results.getInt(COLUMN_PHONE) + " " +
                        results.getString(COLUMN_EMAIL));
            }
            results.close();

            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL +
                ") " +
                "VALUES('" + name + "', " + phone + ", '" + email + "')");
    }
}