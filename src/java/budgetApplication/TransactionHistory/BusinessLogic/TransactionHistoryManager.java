
package budgetApplication.TransactionHistory.BusinessLogic;

import budgetApplication.TransactionHistory.DataAccess.TransactionHistoryDataAccess;
import static budgetApplication.baseClasses.Utilities.order;
import budgetApplication.dataContracts.TransactionHistory;
import java.util.List;


public class TransactionHistoryManager implements AutoCloseable {
    
    public List<TransactionHistory> getAllTransactionsByUserId(int userId) throws Exception {
        
        try(TransactionHistoryDataAccess transactionDataAccess = new TransactionHistoryDataAccess()) {            
            List<TransactionHistory> transactions =transactionDataAccess.getAllTransactionsByUserId(userId);         
            //order(transactions);
            return transactions;
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
