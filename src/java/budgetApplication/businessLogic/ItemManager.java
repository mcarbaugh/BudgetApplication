/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetApplication.businessLogic;

import budgetApplication.dataAccess.ItemDataAccess;
import budgetApplication.dataContracts.*;
import java.util.List;
/**
 *
 * @author Eclat
 */
public class ItemManager implements AutoCloseable {
    public List<Item> getAllItemsByBudgetId(int budgetId) throws Exception {
        
        try(ItemDataAccess itemDataAccess = new ItemDataAccess()) {            
            return itemDataAccess.getAllItemsByBudgetId(budgetId);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public void deletItemById(int id) throws Exception {
        
        try(ItemDataAccess itemDataAccess = new ItemDataAccess()) {
            itemDataAccess.deletItemById(id);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public void updateItemById(Item i) throws Exception {
        
        try(ItemDataAccess itemDataAccess = new ItemDataAccess()) {
            itemDataAccess.updateItemById(i);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public void addItem(Item i) throws Exception {
        
        try(ItemDataAccess itemDataAccess = new ItemDataAccess()) {
            itemDataAccess.addItem(i);
        }
        catch(Exception ex) {
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
