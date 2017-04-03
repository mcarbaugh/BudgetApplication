
package budgetApplication.dataContracts;

import java.io.Serializable;

public class Transaction implements Serializable {
    private int id;
    private int itemId;
    private double amount;
    private String description;
    private String dateTime;
    
    public Transaction() {
        
    }
    
    public int getId() {
        return id;
    }
    
    public int getItemId() {
        return itemId;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getDateTime() {
        return dateTime;
    }
}
