
package budgetApplication.Login.DataAccess;

import budgetApplication.dataAccess.DatabaseFactory;
import budgetApplication.dataContracts.*;
import java.sql.*;

public class LoginFormDataAccess implements AutoCloseable {
    
    public void createNewUser(User user){
        //TODO: write code to create a new user record
    }
    
    public User getUserByUsernameAndPassword(String username, String password) throws Exception {
        try {
            String query = "SELECT id, username, firstName, lastName, email, phoneNumber "
                         + "FROM user "
                         + "WHERE username = ? AND BINARY password = ?";
            
            User user;
            ResultSet userData;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                userData = statement.executeQuery();
                
                user = new User();
                while(userData.next()) {
                    user.setId(userData.getInt("id"));
                    user.setUsername(userData.getString("username"));
                    user.setFirstName(userData.getString("firstName"));
                    user.setLastName(userData.getString("lastName"));
                    user.setEmail(userData.getString("email"));
                    user.setPhoneNumber(userData.getString("phoneNumber"));
                }
                
                mySqlConnection.close();
            }
            
            return user;
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
