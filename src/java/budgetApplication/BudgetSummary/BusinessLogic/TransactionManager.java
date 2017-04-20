
package budgetApplication.BudgetSummary.BusinessLogic;

import budgetApplication.BudgetSummary.DataAccess.TransactionDataAccess;
import budgetApplication.dataContracts.*;

public class TransactionManager implements AutoCloseable {
    
    public void saveTransaction(Transaction transaction) throws Exception {
        try {
            try (TransactionDataAccess dataAccess = new TransactionDataAccess()) {
                
                if(transaction.getId() == 0) {
                    dataAccess.insertTransaction(transaction);
                }
                else {
                    
                }
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void close() throws Exception {
        
    }
}
