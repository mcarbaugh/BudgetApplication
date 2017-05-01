
package budgetApplication.TransactionHistory.DataAccess;

import budgetApplication.baseClasses.DatabaseFactory;
import budgetApplication.dataContracts.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryDataAccess implements AutoCloseable {
    
    public List<TransactionHistory> getAllTransactionsByBudgetId(int budgetId) throws Exception {
        try {
            String query;
            query = "SELECT t.id, t.date, t.name, t.vendor, t.amount, i.name as item, i.category "
                    + "FROM budget b, item i, transaction t "
                    + "WHERE b.id = ? AND i.budgetId = b.id AND t.itemId = i.id";
                            
            TransactionHistory transaction;
            List<TransactionHistory> transactions;
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {

                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, budgetId);
                data = statement.executeQuery();
                
                transactions = new ArrayList();
                while(data.next()) {
                    transaction = new TransactionHistory();
                    transaction.setId(data.getInt("id"));
                    transaction.setDate(data.getDate("date"));                    
                    transaction.setName(data.getString("name"));
                    transaction.setVendor(data.getString("vendor"));
                    transaction.setAmount(data.getDouble("amount"));
                    transaction.setItem(data.getString("item"));
                    transaction.setCategory(data.getString("category"));
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
    
    public void deletTransactionById(int id) throws Exception {
        try {
            
            String deleteFromTransactionQuery = "DELETE FROM transaction WHERE id = ?";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(deleteFromTransactionQuery);
                statement.setInt(1, id);
                statement.executeUpdate();
                mySqlConnection.close();
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void updateTransaction(TransactionHistory transaction, int itemId) throws Exception {
        try {
            String query = "UPDATE transaction SET vendor = ?, itemId = ?, category = ?, amount = ? date = ? WHERE id = ?";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setString(1, transaction.getVendor());
                statement.setInt(2, itemId);
                statement.setString(3, transaction.getCategory());
                statement.setDouble(4, transaction.getAmount());
                statement.setString(5, transaction.toString(transaction.getDate()));
                statement.setInt(6, transaction.getId());
                statement.executeUpdate();
                mySqlConnection.close();
            }  
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public TransactionHistory getTransactionById(int id) throws Exception {
        try {
            String query = "SELECT t.id, t.vendor, i.name as item, t.category, t.amount, t.date "
                         + "FROM transaction t AND item i"
                         + "WHERE t.itemId = i.id AND id = ?";
            
            TransactionHistory transaction = new TransactionHistory();
            ResultSet data;
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query); 
                statement.setInt(1, id);
                data = statement.executeQuery();
                
                while(data.next()) {
                    transaction.setId(data.getInt("id"));
                    transaction.setVendor(data.getString("vendor"));
                    transaction.setItem(data.getString("item"));
                    transaction.setCategory(data.getString("category"));
                    transaction.setAmount(data.getDouble("amount"));
                    transaction.setDate(data.getDate("date"));
                }
                
                mySqlConnection.close();
            }
            
            return transaction;
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
