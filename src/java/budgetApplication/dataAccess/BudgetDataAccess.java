
package budgetApplication.dataAccess;

import budgetApplication.baseClasses.MonthEnum;
import budgetApplication.dataContracts.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BudgetDataAccess implements AutoCloseable {

    public User getUserByUserId(int userId) throws Exception {
        try {
            String query = "SELECT id, username, firstName, lastName, email, phoneNumber "
                         + "FROM user "
                         + "WHERE id = ?";
            
            User user;
            ResultSet userData;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setInt(1, userId);
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
            }
            
            return user;
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public List<Budget> getAllBudgetsByUserId(int userId) throws Exception {
        try {
            String query = "SELECT * FROM budget WHERE userId = ?";
            
            Budget budget;
            List<Budget> budgets;
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                MonthEnum month;
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, userId);
                data = statement.executeQuery();
                
                budgets = new ArrayList();
                while(data.next()) {
                    budget = new Budget();
                    budget.setId(data.getInt("id"));
                    month = MonthEnum.valueOf(data.getString("month"));
                    budget.setMonth(month);                    
                    budget.setYear(data.getInt("year"));
                    budgets.add(budget);
                }
                
                return budgets;
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public Double getTotalAmountById(int id) throws Exception {
        try {
            String query = "SELECT SUM(amount) AS total FROM item WHERE budgetId = ?";
            
            Double total = 0.0;
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, id);
                data = statement.executeQuery();
                
                while(data.next()) {
                    total = data.getDouble("total");
                }
                
                return total;
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public Double getTotalSpentById(int id) throws Exception {   
        try {
            String query = "SELECT SUM(amount) as total "
                         + "FROM transaction "
                         + "WHERE itemId IN (SELECT id "
                                          + "FROM item "
                                          + "WHERE budgetId = ?)";
            
            Double total = 0.0;
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, id);
                data = statement.executeQuery();
                
                while(data.next()) {
                    total = data.getDouble("total");
                }
                
                return total;
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void deleteBudgetById(int id) throws Exception {
        try {
            String deleteFromTransactionQuery = "DELETE FROM `transaction` "
                                              + "WHERE itemId IN (SELECT i.id "
                                                               + "FROM item i "
                                                               + "WHERE i.budgetId = ?)";
            
            String deleteFromItemQuery = "DELETE FROM item WHERE budgetId = ?";
            String deleteFromIncomeQuery = "DELETE FROM income WHERE budgetId = ?";
            String deleteFromBudgetQuery = "DELETE FROM budget WHERE id = ?";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(deleteFromTransactionQuery);
                statement.setInt(1, id);
                statement.executeUpdate();
            }
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(deleteFromItemQuery);
                statement.setInt(1, id);
                statement.executeUpdate();
            }
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(deleteFromIncomeQuery);
                statement.setInt(1, id);
                statement.executeUpdate();
            }
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(deleteFromBudgetQuery);
                statement.setInt(1, id);
                statement.executeUpdate();
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