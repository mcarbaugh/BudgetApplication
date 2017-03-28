package budgetApplication.dataAccess;

import budgetApplication.dataContracts.*;
import java.sql.*;

public class LoginFormDataAccess implements AutoCloseable {
    
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String dbUser = "root";
    private static final String dbPassword = "sesame";
    
    public void createNewUser(User user){
        //TODO: write code to create a new user record
    }
    
    public User getUserByUsernameAndPassword(String username, String password) throws Exception {
         
        try {
            String query = "SELECT * "
                     + "FROM user "
                     + "WHERE username = ? AND password = ?";
            
            User user;
            ResultSet userData;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            try (Connection connection = DriverManager.getConnection(
                    url, dbUser, dbPassword)) {
                
                PreparedStatement statement;
                statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                userData = statement.executeQuery();
                
                user = new User();
                while(userData.next()) {
                    user.setId(userData.getInt("id"));
                    user.setUsername(userData.getString("username"));
                    user.setFirstName(userData.getString("firstName"));
                    user.setLastName(userData.getString("lastName"));
                    user.setPhoneNumber(userData.getString("phoneNumber"));
                    user.setPassword(userData.getString("password"));
                }
            }
            
            return user;
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println(ex);
            throw ex;
        }
    }

    @Override
    public void close() throws Exception {
        try {
            
        } 
        catch(Exception ex) {
            throw new Exception (ex);
        }
    }
}
