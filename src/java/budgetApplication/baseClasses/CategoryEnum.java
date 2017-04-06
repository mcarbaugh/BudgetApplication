
package budgetApplication.baseClasses;

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
    
    @Override
    public final String toString() {
        return this.name;
    }
}
