
package budgetApplication.TransactionHistory.BusinessLogic;

import budgetApplication.TransactionHistory.DataAccess.TransactionHistoryDataAccess;
import static budgetApplication.baseClasses.TransactionUtilities.order;
import budgetApplication.dataContracts.TransactionHistory;
import java.util.List;


public class TransactionHistoryManager implements AutoCloseable {
    
    public List<TransactionHistory> getAllTransactionsByBudgetId(int BudgetId) throws Exception {
        
        try(TransactionHistoryDataAccess transactionDataAccess = new TransactionHistoryDataAccess()) {            
            List<TransactionHistory> transactions =transactionDataAccess.getAllTransactionsByBudgetId(BudgetId);         
            // sort transactions by date
            // then by category
            // then by item
            // then by vendor
            order(transactions);
            return transactions;
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public void deletTransactionById(int id) throws Exception {
        
        try(TransactionHistoryDataAccess transactionDataAccess = new TransactionHistoryDataAccess()) {
            transactionDataAccess.deletTransactionById(id);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public void saveTransaction(TransactionHistory transaction) throws Exception {
        try {
            try (TransactionHistoryDataAccess dataAccess = new TransactionHistoryDataAccess()) {
                
                
                dataAccess.updateTransaction(transaction);
                
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public TransactionHistory getTransactionById(int id) throws Exception {
        
        try(TransactionHistoryDataAccess dataAccess = new TransactionHistoryDataAccess()) {            
            return dataAccess.getTransactionById(id);
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
