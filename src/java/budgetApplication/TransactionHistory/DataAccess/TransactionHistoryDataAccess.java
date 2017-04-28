
package budgetApplication.TransactionHistory.DataAccess;

import budgetApplication.baseClasses.DatabaseFactory;
import budgetApplication.dataContracts.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryDataAccess implements AutoCloseable {
    
    public List<TransactionHistory> getAllTransactionsByUserId(int userId) throws Exception {
        try {
            String query;
            query = "SELECT t.id, t.date, t.name, t.vendor, t.amount "
                    + "FROM budget b, item i, transaction t "
                    + "WHERE b.userId = ? AND i.budgetId = b.id AND t.itemId = i.id";
                            
            TransactionHistory transaction;
            List<TransactionHistory> transactions;
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {

                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, userId);
                data = statement.executeQuery();
                
                transactions = new ArrayList();
                while(data.next()) {
                    transaction = new TransactionHistory();
                    transaction.setId(data.getInt("id"));
                    transaction.setDate(data.getDate("date"));                    
                    transaction.setName(data.getString("name"));
                    transaction.setVendor(data.getString("vendor"));
                    transaction.setAmount(data.getDouble("amount"));
                    transactions.add(transaction);
                }
                
                mySqlConnection.close();
            }
            
            return transactions;
        }
        catch (Exception ex) {
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
