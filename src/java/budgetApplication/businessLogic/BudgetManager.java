
package budgetApplication.businessLogic;

import budgetApplication.dataAccess.BudgetDataAccess;

public class BudgetManager implements AutoCloseable {
    
    public void deleteBudgetById(int id) throws Exception {
        
        try(BudgetDataAccess budgetDataAccess = new BudgetDataAccess()) {
            budgetDataAccess.deleteBudgetById(id);
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
