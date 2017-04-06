/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetApplication.dataAccess;

import budgetApplication.dataContracts.*;
import budgetApplication.baseClasses.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Eclat
 */
public class ItemDataAccess implements AutoCloseable {
    
    public List<Item> getAllItemsByBudgetId(int budgetId) throws Exception {
        try {
            String query = "SELECT i.id, c.name, i.amount, i.description "
                         + "FROM item i, category c "
                         + "WHERE budgetId = ? AND i.categoryId = c.id";
            
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
                    String cName = data.getString("name");
                    CategoryEnum category = CategoryEnum.valueOf(cName);
                    item.setCategory(category);
                    item.setAmount(data.getDouble("amount"));
                    item.setDescription(data.getString("description"));
                    items.add(item);
                }
                
                return items;
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
            }  
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void updateItemById(Item i) throws Exception {
        try {
            String updateFromItemQuery = "UPDATE FROM item SET amount = ?, description = ? WHERE id = ?";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(updateFromItemQuery);
                statement.setDouble(1, i.getAmount());
                statement.setString(2, i.getDescription());
                statement.setInt(3, i.getId());
                statement.executeUpdate();
            }  
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void addItem(Item i) throws Exception {
        try {
            String updateFromItemQuery = "INSERT INTO item values (?,?,?)";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(updateFromItemQuery);
                statement.setInt(1, i.getId());
                statement.setDouble(2, i.getAmount());
                statement.setString(3, i.getDescription());
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
