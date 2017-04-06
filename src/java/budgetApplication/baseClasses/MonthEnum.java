
package budgetApplication.baseClasses;

public enum MonthEnum {
    JANUARY ("January"),
    FEBRUARY ("February"),
    MARCH ("March"),
    APRIL ("April"),
    MAY ("May"),
    JUNE ("June"),
    JULY ("July"),
    AUGUST ("August"),
    SEPTEMBER ("September"),
    OCTOBER ("October"),
    NOVEMBER ("November"),
    DECEMBER ("December");
    
    private final String monthValue;
    
    MonthEnum(String value) {
        monthValue = value;
    }
    
    @Override
    public String toString() {
        return monthValue;
    }
}
