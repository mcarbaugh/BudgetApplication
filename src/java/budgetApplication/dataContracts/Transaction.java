
package budgetApplication.dataContracts;

import java.io.Serializable;

public class Transaction implements Serializable {
    private int id;
    private int itemId;
    private String name;
    private String vendor;
    private double amount;
    private String date;
    
    public Transaction() {
        id = 0;
        itemId = 0;
        amount = 0;
        name = "";
        date = "";
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
    
    public String getName() {
        return name;
    }
    
    public String getVendor() {
        return vendor;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setId(int value) {
        id = value;
    }
    
    public void setItemId(int value) {
        itemId = value;
    }
    
    public void setName(String value) {
        name = value;
    }
    
    public void setVendor(String value) {
        this.vendor = value;
    }
    
    public void setAmount(Double value) {
        amount = value;
    }
    
    public void setDate(String value) {
        date = value;
    }
}