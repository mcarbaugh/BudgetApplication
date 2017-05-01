
package budgetApplication.BudgetSummary.DataAccess;

import budgetApplication.dataContracts.*;
import static budgetApplication.baseClasses.Utilities.getCategoryAsEnum;
import budgetApplication.baseClasses.DatabaseFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDataAccess implements AutoCloseable {
    
    public List<Item> getAllItemsByBudgetId(int budgetId) throws Exception {
        try {
            String query = "SELECT i.id, i.name, i.category, i.amount, SUM(t.amount) as spent "
                         + "FROM item i "
                         + "LEFT JOIN transaction t ON i.id = t.itemId "
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
    
    public Item getItemById(int id) throws Exception {
        try {
            String query = "SELECT i.id as itemId, i.name, i.category, i.amount as planned, SUM(t.amount) as spent "
                         + "FROM item i "
                         + "LEFT JOIN transaction t ON i.id = t.itemId "
                         + "WHERE i.id = ? "
                         + "GROUP BY i.id";
            
            Item item = new Item();
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, id);
                data = statement.executeQuery();
                
                while(data.next()) {
                    item.setId(data.getInt("itemId"));
                    item.setName(data.getString("name"));
                    item.setCategory(getCategoryAsEnum(data.getString("category")));
                    item.setAmount(data.getDouble("planned"));
                    item.setSpent(data.getDouble("spent"));
                }
                
                mySqlConnection.close();
            }
            
            return item;
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public int getItemIdByName(String name) throws Exception {
        try {
            String query = "SELECT id "
                         + "FROM item "
                         + "WHERE name = ?";
            int id = 0;
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setString(1, name);
                data = statement.executeQuery();
                
                while(data.next()) {
                    id = data.getInt("id");
                }
                
                mySqlConnection.close();
            }
            
            return id;
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    
    public void insertItemByBudgetId(Item item, int budgetId) throws Exception {
        try {
            String query = "INSERT INTO item (budgetId, name, category, amount) VALUES (?, ?, ?, ?)";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setInt(1, budgetId);
                statement.setString(2, item.getName());
                statement.setString(3, item.getCategory().name());
                statement.setDouble(4, item.getAmount());
                statement.executeUpdate();
                mySqlConnection.close();
            }  
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void updateItem(Item item) throws Exception {
        try {
            String query = "UPDATE item SET name = ?, amount = ? WHERE id = ?";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setString(1, item.getName());
                statement.setDouble(2, item.getAmount());
                statement.setInt(3, item.getId());
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
    
    public int getLastIdByBudgetId(int budgetId) throws Exception {
        
        try {
            String query = "SELECT MAX(i.id) as maxId "
                         + "FROM item i "
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
        try {
            
        } 
        catch(Exception ex) {
            throw ex;
        }
    }
}
