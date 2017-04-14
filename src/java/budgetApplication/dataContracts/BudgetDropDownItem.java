
package budgetApplication.dataContracts;

import budgetApplication.baseClasses.MonthEnum;
import java.io.Serializable;

public class BudgetDropDownItem implements Serializable {
    
    private int id;
    private MonthEnum month;
    private int year;
    
    public BudgetDropDownItem() {
        this.id = 0;
        this.month = MonthEnum.NONE;
        this.year = 0;
    }
    
    public int getId() {
        return this.id;
    }
    
    public MonthEnum getMonth() {
        return this.month;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public void setId(int value) {
        this.id = value;
    }
    
    public void setMonth(MonthEnum value) {
        this.month = value;
    }
    
    public void setYear(int value) {
        this.year = value;
    }
}
