
package budgetApplication.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BudgetDataAccess implements AutoCloseable {

    public void getAllBudgetsByUserId(int userId) {
        //TODO: write sql to get all budgets for a specific user
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
