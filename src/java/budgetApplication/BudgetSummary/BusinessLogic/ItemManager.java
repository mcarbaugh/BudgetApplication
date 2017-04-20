
package budgetApplication.BudgetSummary.BusinessLogic;

import budgetApplication.BudgetSummary.DataAccess.*;
import budgetApplication.dataContracts.*;
import java.util.List;

public class ItemManager implements AutoCloseable {
    
    public List<Item> getAllItemsByBudgetId(int budgetId) throws Exception {
        
        try(ItemDataAccess itemDataAccess = new ItemDataAccess()) {            
            return itemDataAccess.getAllItemsByBudgetId(budgetId);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public Item getItemById(int id) throws Exception {
        
        try(ItemDataAccess itemDataAccess = new ItemDataAccess()) {            
            return itemDataAccess.getItemById(id);
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    public void saveItem(Item item, int budgetId) throws Exception {
        try {
            try (ItemDataAccess dataAccess = new ItemDataAccess()) {
                
                if(item.getId() == 0) {
                    dataAccess.insertItemByBudgetId(item, budgetId);
                }
                else {
                    dataAccess.updateItem(item);
                }
            }
        }
        catch (Exception ex) {
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
    
    public int getLastIdByBudgetId(int budgetId) throws Exception {
        
        try(ItemDataAccess itemDataAccess = new ItemDataAccess()) {
            return itemDataAccess.getLastIdByBudgetId(budgetId);
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