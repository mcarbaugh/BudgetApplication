
package budgetApplication.businessLogic;

import budgetApplication.baseClasses.MonthEnum;
import budgetApplication.dataContracts.Budget;
import java.util.Collections;
import java.util.List;

public class Utilities {
    
     public static void order(List<Budget> budgets) {
        
        Collections.sort(budgets, (Object b1, Object b2) -> {
            int x1 = ((Budget) b1).getYear();
            int x2 = ((Budget) b2).getYear();
            int sComp = x2 - x1;
            
            if (sComp != 0) {
                return sComp;
            } else {
                MonthEnum m1 = ((Budget) b1).getMonth();
                MonthEnum m2 = ((Budget) b2).getMonth();
                return m2.compareTo(m1);
            }
        });
    }
}
