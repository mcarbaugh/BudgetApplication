
package budgetApplication.dataContracts;

import java.io.Serializable;
import java.util.Date;


public class TransactionHistory implements Serializable{
    private int id;
    private int itemId;
    private String name;
    private String vendor;
    private double amount;
    private Date date;
    
    public TransactionHistory() {
        id = 0;
        itemId = 0;
        name = "";
        vendor = "";
        amount = 0.0;
        date = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
