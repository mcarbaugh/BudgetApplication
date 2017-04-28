
package budgetApplication.Profile.DataAccess;

import budgetApplication.baseClasses.DatabaseFactory;
import budgetApplication.dataContracts.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfileDataAccess implements AutoCloseable {   

    public User getUserProfileById(int userId) throws Exception {
        try{
            String query = "SELECT username, email, firstName, lastName, phoneNumber "
                    + "FROM user "
                    + "WHERE id =?";
            User user;
            ResultSet userProfileData;
            try(Connection mySqlConnection = DatabaseFactory.getMySqlConnection()){
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setInt(1, userId);
                userProfileData = statement.executeQuery();
                
                user = new User();
                while(userProfileData.next()){
                    user.setId(userId);
                    user.setUsername(userProfileData.getString("username"));
                    user.setFirstName(userProfileData.getString("firstName"));
                    user.setLastName(userProfileData.getString("lastName"));
                    user.setEmail(userProfileData.getString("email"));
                    user.setPhoneNumber(userProfileData.getString("phoneNumber"));
                }
                
                mySqlConnection.close();
            }
            
            return user;
        }
        catch (Exception ex){
            throw ex;
        } 
    }
    
    public void updateUserProfile(User user) throws Exception {
        try {
            String query = "UPDATE user "
                    + "SET username=?, email=?, firstName=?, lastName=?, phoneNumber=? "
                    + "WHERE id=?";
            try(Connection mySqlConnection = DatabaseFactory.getMySqlConnection()){
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getFirstName());
                statement.setString(4, user.getLastName());
                statement.setString(5, user.getPhoneNumber());
                statement.setInt(6, user.getId());
                statement.executeUpdate();       
                mySqlConnection.close();
            }
        }
        catch (Exception ex){
            throw ex;
        }
    }
    
    public void deleteUserProfileById(int userId) throws Exception{
        try {
            String query = "DELETE FROM user "
                    + "WHERE id =?";
            try(Connection mySqlConnection = DatabaseFactory.getMySqlConnection()){
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setInt(1, userId);
                statement.execute();
                mySqlConnection.close();
            }
        }
        catch (Exception ex){
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
