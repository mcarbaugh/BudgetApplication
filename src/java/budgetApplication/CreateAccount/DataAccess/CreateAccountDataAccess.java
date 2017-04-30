
package budgetApplication.CreateAccount.DataAccess;

import budgetApplication.baseClasses.DatabaseFactory;
import budgetApplication.dataContracts.UserAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreateAccountDataAccess implements AutoCloseable {

    public String getUsername(String username) throws Exception {
        
        try {
            
            String query = "SELECT username "
                         + "FROM user "
                         + "WHERE username = ?";
            
            ResultSet userData;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setString(1, username);
                userData = statement.executeQuery();
                
                username = "";
                while(userData.next()) {
                    username = userData.getString("username");
                }
                
                mySqlConnection.close();
            }
            
            return username;
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void insertUser(UserAccount user) throws Exception {
        try {
            String query = "INSERT INTO user (username, firstName, lastName, password) VALUES (?, ?, ?, ?)";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getFirstName());
                statement.setString(3, user.getLastName());
                statement.setString(4, user.getPassword());
                statement.executeUpdate();
                mySqlConnection.close();
            }  
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public void close() throws Exception {
        try {

        } 
        catch(Exception ex) {
            throw ex;
        }
    }
}
