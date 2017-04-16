
package budgetApplication.BudgetSummary.DataAccess;

import budgetApplication.dataContracts.*;
import static budgetApplication.baseClasses.Utilities.getCategoryAsEnum;
import budgetApplication.dataAccess.DatabaseFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDataAccess implements AutoCloseable {
    
    public List<Item> getAllItemsByBudgetId(int budgetId) throws Exception {
        try {
            String query = "SELECT i.id, i.name, i.category, i.amount, SUM(t.amount) as spent "
                         + "FROM item i, transaction t "
                         + "WHERE budgetId = ? "
                         + "GROUP BY i.id";
            
            Item item;
            List<Item> items;
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, budgetId);
                data = statement.executeQuery();
                
                items = new ArrayList();
                while(data.next()) {
                    item = new Item();
                    item.setId(data.getInt("id"));
                    item.setName(data.getString("name"));
                    item.setCategory(getCategoryAsEnum(data.getString("category")));
                    item.setAmount(data.getDouble("amount"));
                    item.setSpent(data.getDouble("spent"));
                    items.add(item);
                }
                
                mySqlConnection.close();
            }
            
            return items;
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void insertItemByBudgetId(Item item, int budgetId) throws Exception {
        try {
            String query = "INSERT INTO item (budgetId, category, name, amount) VALUES (?, ?, ?, ?)";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                //statement.setDouble(1, i.getAmount());
                statement.executeUpdate();
                mySqlConnection.close();
            }  
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void deletItemById(int id) throws Exception {
        try {
            
            String deleteFromItemQuery = "DELETE FROM item WHERE id = ?";
            
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
    
    @Override
    public void close() throws Exception {
        try {
            
        } 
        catch(Exception ex) {
            throw ex;
        }
    }
}
