
package budgetApplication.businessLogic;

import budgetApplication.dataAccess.BudgetDataAccess;
import budgetApplication.dataContracts.*;
import java.util.Collections;
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
            Collections.sort(budgets, (final Budget b1, final Budget b2) -> (b1.getYear() - b2.getYear()));
            Collections.sort(budgets, (final Budget b1, final Budget b2) -> (b1.getMonth().compareTo(b2.getMonth())));            
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
    
    public void insertBudgetByUserId(int userId, Budget budget) throws Exception {
        try(BudgetDataAccess budgetDataAccess = new BudgetDataAccess()) {
            budgetDataAccess.insertBudgetByUserId(userId, budget);
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
