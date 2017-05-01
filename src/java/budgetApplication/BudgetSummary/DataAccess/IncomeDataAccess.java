
package budgetApplication.BudgetSummary.DataAccess;

import budgetApplication.baseClasses.DatabaseFactory;
import budgetApplication.dataContracts.Income;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IncomeDataAccess implements AutoCloseable {
    
    public void insertIncomeByBudgetId(Income income, int budgetId) throws Exception {
        try {
            String query = "INSERT INTO income (budgetId, name, amount) VALUES (?, ?, ?)";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setInt(1, budgetId);
                statement.setString(2, income.getName());
                statement.setDouble(3, income.getAmount());
                statement.executeUpdate();
                mySqlConnection.close();
            }  
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public List<Income> getAllIncomesByBudgetId(int budgetId) throws Exception {
        try {
            String query = "SELECT i.id, i.name, i.amount "
                         + "FROM income i "
                         + "WHERE budgetId = ?";
            
            Income income;
            List<Income> incomes;
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, budgetId);
                data = statement.executeQuery();
                
                incomes = new ArrayList();
                while(data.next()) {
                    income = new Income();
                    income.setId(data.getInt("id"));
                    income.setName(data.getString("name"));
                    income.setAmount(data.getDouble("amount"));
                    incomes.add(income);
                }
                
                mySqlConnection.close();
            }
            
            return incomes;
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void updateIncome(Income income) throws Exception {
        try {
            String query = "UPDATE income SET name = ?, amount = ? WHERE id = ?";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setString(1, income.getName());
                statement.setDouble(2, income.getAmount());
                statement.setInt(3, income.getId());
                statement.executeUpdate();
                mySqlConnection.close();
            }  
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void deleteIncomeById(int id) throws Exception {
        try {
            
            String deleteFromItemQuery = "DELETE FROM income WHERE id = ?";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(deleteFromItemQuery);
                statement.setInt(1, id);
                statement.executeUpdate();
                mySqlConnection.close();
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public int getLastIdByBudgetId(int budgetId) throws Exception {
        
        try {
            String query = "SELECT MAX(i.id) as maxId "
                         + "FROM income i "
                         + "WHERE i.budgetId = ?";
            
            int maxId = 0;
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, budgetId);
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
        
    }
}
