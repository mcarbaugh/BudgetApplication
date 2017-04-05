/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetApplication.dataContracts;

import budgetApplication.baseClasses.*;
import java.io.Serializable;

/**
 *
 * @author Eclat
 */
public class Item implements Serializable {
    private int id;
    private double amount;
    private String description;
    private CategoryEnum category;
    
    public Item() {
        id = 0;
        amount = 0;
        description = "";
        category = CategoryEnum.FOOD;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
