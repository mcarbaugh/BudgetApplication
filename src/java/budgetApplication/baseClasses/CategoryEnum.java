/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetApplication.baseClasses;

/**
 *
 * @author Eclat
 */
public enum CategoryEnum {
    NONE (""),
    GIVING ("Giving"),
    SAVINGS ("Savings"),
    HOUSING ("Housing"),
    TRANSPORTATION ("Transportation"),
    FOOD ("Food"),
    LIFESTYLE ("Lifestyle"),
    INSURANCE_TAX ("Insurance and Tax");
    
    private final String name;
    
    private CategoryEnum(String value) {
        name = value;
    }
    
    public final String toString() {
        return this.name;
    }
}
