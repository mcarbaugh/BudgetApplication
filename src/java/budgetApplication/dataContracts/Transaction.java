
package budgetApplication.dataContracts;

import java.io.Serializable;

public class Transaction implements Serializable {
    private int id;
    private int itemId;
    private double amount;
    private String description;
    private String dateTime;
    
    public Transaction() {
        id = 0;
        itemId = 0;
        amount = 0;
        description = "";
        dateTime = "";
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
    
    public void setId(int value) {
        id = value;
    }
    
    public void setItemId(int value) {
        itemId = value;
    }
    
    public void setAmount(Double value) {
        amount = value;
    }
    
    public void setDescription(String value) {
        description = value;
    }
    
    public void setDateTime(String value) {
        dateTime = value;
    }
}
