
package budgetApplication.businessLogic;

import static budgetApplication.baseClasses.Utilities.order;
import budgetApplication.dataAccess.BudgetDataAccess;
import budgetApplication.dataContracts.*;
import java.util.List;

public class BudgetManager implements AutoCloseable {
    
    public User getUserByUserId(int userId) throws Exception {
        
        try(BudgetDataAccess budgetDataAccess = new BudgetDataAccess()) {
            return budgetDataAccess.getUserByUserId(userId);
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public List<Budget> getAllBudgetsByUserId(int userId) throws Exception {
        
        try(BudgetDataAccess budgetDataAccess = new BudgetDataAccess()) {            
            List<Budget> budgets =budgetDataAccess.getAllBudgetsByUserId(userId);         
            order(budgets);
            return budgets;
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public Double getTotalAmountById(int id) throws Exception {
        
        try(BudgetDataAccess budgetDataAccess = new BudgetDataAccess()) {
            return budgetDataAccess.getTotalAmountById(id);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public Double getTotalSpentById(int id) throws Exception {
        
        try(BudgetDataAccess budgetDataAccess = new BudgetDataAccess()) {
            return budgetDataAccess.getTotalSpentById(id);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public void deleteBudgetById(int id) throws Exception {
        
        try(BudgetDataAccess budgetDataAccess = new BudgetDataAccess()) {
            budgetDataAccess.deleteBudgetById(id);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public void saveBudgetByUserId(int userId, Budget budget) throws Exception {
        try(BudgetDataAccess budgetDataAccess = new BudgetDataAccess()) {
            int budgetId = budget.getId();
            
            if(budgetId == 0) {
                budgetDataAccess.insertBudgetByUserId(userId, budget);
            }
            else {
                // update the existing record
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public int getLastIdByUserId(int userId) throws Exception {
        try(BudgetDataAccess budgetDataAccess = new BudgetDataAccess()) {
            return budgetDataAccess.getLastIdByUserId(userId);
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
