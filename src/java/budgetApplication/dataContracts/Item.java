
package budgetApplication.dataContracts;

import budgetApplication.baseClasses.*;
import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name;
    private CategoryEnum category;
    private double amount;
    private double spent;
    
    public Item() {
        id = 0;
        amount = 0;
        spent = 0;
        name = "";
        category = CategoryEnum.FOOD;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public CategoryEnum getCategory() {
        return category;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public double getSpent() {
        return spent;
    }

    public double getRemaining() {
        return (amount - spent);
    }
    
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String value) {
        this.name = value;
    }
    
    public void setCategory(CategoryEnum value) {
        this.category = value;
    }

    public void setAmount(double value) {
        this.amount = value;
    }

    public void setSpent(double value) {
        spent = value;
    }
}
