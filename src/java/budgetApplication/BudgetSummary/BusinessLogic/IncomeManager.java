
package budgetApplication.BudgetSummary.BusinessLogic;

import budgetApplication.BudgetSummary.DataAccess.*;
import budgetApplication.dataContracts.*;
import java.util.List;

public class IncomeManager implements AutoCloseable {
    
    public List<Income> getAllIncomesByBudgetId(int budgetId) throws Exception {
        
        try(IncomeDataAccess dataAccess = new IncomeDataAccess()) {            
            return dataAccess.getAllIncomesByBudgetId(budgetId);
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    public void saveIncome(Income income, int budgetId) throws Exception {
        try {
            try (IncomeDataAccess dataAccess = new IncomeDataAccess()) {
                
                if(income.getId() == 0) {
                    dataAccess.insertIncomeByBudgetId(income, budgetId);
                }
                else {
                    dataAccess.updateIncome(income);
                }
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void deleteIncomeById(int id) throws Exception {
        
        try(IncomeDataAccess dataAccess = new IncomeDataAccess()) {
            dataAccess.deleteIncomeById(id);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public int getLastIdByBudgetId(int budgetId) throws Exception {
        
        try (IncomeDataAccess dataAccess = new IncomeDataAccess()) {
            return dataAccess.getLastIdByBudgetId(budgetId);
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