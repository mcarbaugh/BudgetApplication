
package budgetApplication.baseClasses;

import budgetApplication.dataContracts.Budget;

public class BudgetApplicationFault extends Exception {
    
    private final DataTypeGeneric data;
    private final String message;
    
    public BudgetApplicationFault (Exception ex, Budget budget, String propertyName) {
        super(ex);
        data = new DataTypeGeneric(budget, propertyName);
        message = ex.getMessage();
    }
    
    public DataTypeGeneric getData() {
        return data;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
