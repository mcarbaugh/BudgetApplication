
package budgetApplication.dataAccess;

import budgetApplication.baseClasses.MonthEnum;
import budgetApplication.dataContracts.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BudgetSummaryDataAccess implements AutoCloseable {

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
                
                mySqlConnection.close();
            }
            
            return user;
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public List<Budget> getAllBudgetsByUserId(int userId) throws Exception {
        try {
            String query;
            query = "SELECT b.id, b.month, b.year, SUM(itemResult.planned) as planned, spent "
                  + "FROM budget b "
                  + "LEFT JOIN (SELECT i.id, i.budgetId, i.amount as planned "
                             + "FROM item i "
                             + "GROUP BY i.id) itemResult "
                             + "ON b.id = itemResult.budgetId "
                  + "LEFT JOIN (SELECT SUM(t.amount) as spent, b2.id as budgetID "
                             + "FROM transaction t, item i2, budget b2 "
                             + "WHERE t.itemId = i2.id AND i2.budgetId = b2.id "
                             + "GROUP BY b2.id) tResult "
                             + "ON tResult.budgetId = itemResult.budgetId "
                  + "WHERE b.userId = ? "
                  + "GROUP BY b.id";
            
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
                    budget.setTotalAmount(data.getDouble("planned"));
                    budget.setTotalSpent(data.getDouble("spent"));
                    budgets.add(budget);
                }
                
                mySqlConnection.close();
            }
            
            return budgets;
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
                
                 mySqlConnection.close();
            }
            
            return total;
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
                
                
                 mySqlConnection.close();
            }
            
            return total;
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
                statement = mySqlConnection.prepareStatement(deleteFromBudgetQuery);
                statement.setInt(1, id);
                statement.executeUpdate();
                mySqlConnection.close();
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void insertBudgetByUserId(int userId, Budget budget) throws Exception {
        try {
            String query = "INSERT INTO budget (userId, month, year) "
                         + "VALUES (?, ?, ?)";
                       
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, userId);
                
                statement.setString(2, budget.getMonth().name());
                statement.setInt(3, budget.getYear());
                statement.executeUpdate();
                mySqlConnection.close();
            }
        }
        catch (Exception ex) {
            throw new SQLException(ex);
        }
    }
    
    public int getLastIdByUserId(int userId) throws Exception {
        try {
            String query = "SELECT MAX(id) as maxId FROM budget b WHERE b.userId = ?";
            
            int maxId = 0;
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, userId);
                
                data = statement.executeQuery();
                
                while(data.next()) {
                    maxId = data.getInt("maxId");
                }
                
                
                mySqlConnection.close();
            }
            
            return maxId;
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