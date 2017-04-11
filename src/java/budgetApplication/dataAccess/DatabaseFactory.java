
package budgetApplication.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseFactory implements AutoCloseable {
    private static final String url_local = "jdbc:mysql://localhost:3306/BudgetDB";
    private static final String url_remote = "jdbc:mysql://acadmysql.duc.auburn.edu/BudgetDB";
    private static final String dbUser = "comp6000";
    private static final String dbPassword = "BudgetDB";
    
    public static Connection getMySqlConnection() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url_local, dbUser, dbPassword);
        }
        catch(ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void close() throws Exception {
        
    }
}
