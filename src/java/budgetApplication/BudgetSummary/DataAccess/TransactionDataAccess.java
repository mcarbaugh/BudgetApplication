
package budgetApplication.BudgetSummary.DataAccess;

import budgetApplication.dataAccess.DatabaseFactory;
import budgetApplication.dataContracts.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class TransactionDataAccess implements AutoCloseable {
    
    public void insertTransaction(Transaction transaction) throws Exception {
        try {
            String query = "INSERT INTO transaction (itemId, name, vendor, amount, date) VALUES (?, ?, ?, ?, ?)";
            
            try (Connection mySqlConnection = DatabaseFactory.getMySqlConnection()) {
                
                PreparedStatement statement;
                statement = mySqlConnection.prepareStatement(query);
                statement.setInt(1, transaction.getItemId());
                statement.setString(2, transaction.getName());
                statement.setString(3, transaction.getVendor());
                statement.setDouble(4, transaction.getAmount());
                statement.setString(5, LocalDate.now().toString());
                statement.executeUpdate();
                mySqlConnection.close();
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
