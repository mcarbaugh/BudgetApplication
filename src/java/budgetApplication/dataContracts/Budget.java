package budgetApplication.dataContracts;

import java.io.Serializable;

public class Budget implements Serializable {
    private int id;
    private String month;
    private String year;
    private Double totalSpent;
    private Double totalAmount;
    
    public Budget() {
        
    }
    
    public int getId() {
        return id;
    }
    
    public String getMonth() {
        return month;
    }
    
    public String getYear() {
        return year;
    }
    
    public Double getTotalSpent() {
        return totalSpent;
    }
    
    public Double getTotalAmount() {
        return totalAmount;
    }
    
    public void setId(int value) {
        id = value;
    }
    
    public void setMonth(String value) {
        month = value;
    }
    
    public void setYear(String value) {
        year = value;
    }
    
    public void setTotalSpent(Double value) {
        totalSpent = value;
    }
    
    public void setTotalAmount(Double value) {
        totalAmount = value;
    }
}
