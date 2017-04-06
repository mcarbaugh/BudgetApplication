/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetApplication.dataContracts;

import java.io.Serializable;
/**
 *
 * @author Eclat
 */
public class Category implements Serializable {
    private int id;
    private String name;
    private String color;
    
    public Category() {
        id = 0;
        name = "savings";
        color = "red";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
