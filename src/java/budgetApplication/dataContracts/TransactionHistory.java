
package budgetApplication.dataContracts;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TransactionHistory implements Serializable{
    private int id;
    private String item;
    private String name;
    private String vendor;
    private double amount;
    private Date date;
    private String category;
    
    public TransactionHistory() {
        id = 0;
        item = "";
        name = "";
        vendor = "";
        amount = 0.0;
        date = null;
        category = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String toString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String res = format.format(date);
        return res;
    }
    
    
}
