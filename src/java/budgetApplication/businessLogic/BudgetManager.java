
package budgetApplication.businessLogic;

import budgetApplication.baseClasses.MonthEnum;
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
    
    private static void order(List<Budget> budgets) {
        
        Collections.sort(budgets, (Object b1, Object b2) -> {
            int x1 = ((Budget) b1).getYear();
            int x2 = ((Budget) b2).getYear();
            int sComp = x2 - x1;
            
            if (sComp != 0) {
                return sComp;
            } else {
                MonthEnum m1 = ((Budget) b1).getMonth();
                MonthEnum m2 = ((Budget) b2).getMonth();
                return m2.compareTo(m1);
            }
        });
    }
}
