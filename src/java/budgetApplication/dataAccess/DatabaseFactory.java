
package budgetApplication.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseFactory implements AutoCloseable {
    private static final String url = "jdbc:mysql://localhost:3306/BudgetDB";
    private static final String dbUser = "root";
    private static final String dbPassword = "sesame";
    
    public static Connection getMySqlConnection() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, dbUser, dbPassword);
        }
        catch(ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void close() throws Exception {
        
    }
}
