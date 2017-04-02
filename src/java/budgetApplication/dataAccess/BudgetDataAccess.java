package budgetApplication.dataAccess;

import budgetApplication.dataContracts.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BudgetDataAccess implements AutoCloseable {

    public List<Budget> getAllBudgetsByUserId(int userId) throws Exception {
        try {
            String query = "SELECT * FROM budget WHERE userId = ?";
            
            Budget budget;
            List<Budget> budgets;
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, userId);
                data = statement.executeQuery();
                
                budgets = new ArrayList();
                while(data.next()) {
                    budget = new Budget();
                    budget.setId(data.getInt("id"));
                    budget.setMonth(data.getString("month"));
                    budget.setYear(data.getString("year"));
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
            
            String deleteFromTransactionQuery = "DELETE FROM transaction "
                                              + "WHERE id IN (SELECT i.id"
                                                           + "FROM item i"
                                                           + "WHERE i.budgetId = ?)";
            String deleteFromItemQuery = "DELETE FROM item WHERE id = ?";
            String deleteFromIncomeQuery = "DELETE FROM income WHERE budgetId = ?";
            String deleteFromBudgetQuery = "DELETE FROM budget WHERE id = ?";
            
            ResultSet userData;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(deleteFromTransactionQuery);
                statement.setInt(1, id);
                userData = statement.executeQuery();
            }
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(deleteFromItemQuery);
                statement.setInt(1, id);
                userData = statement.executeQuery();
            }
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(deleteFromIncomeQuery);
                statement.setInt(1, id);
                userData = statement.executeQuery();
            }
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(deleteFromBudgetQuery);
                statement.setInt(1, id);
                userData = statement.executeQuery();
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
