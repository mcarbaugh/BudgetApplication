
package budgetApplication.baseClasses;

public class ConstantFields {
    
    // pages in the application
    public static final String LOGIN_PAGE = "Login";
    public static final String BUDGET_SUMMARY_PAGE = "BudgetSummary";
    
    // URL parameters
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String USER_ID = "userId";
    public static final String BUDGET_ID = "budgetId";
    public static final String MESSAGE = "message";
    public static final String LOGIN_ERROR_MESSAGE = "Username or password invalid.";
    public static final String OPERATION_SIGN_OUT = "signout";
    
    // C.R.U.D. operation parameters for Budget, Item, Update, and Delete
    public static final String OPERATION = "operation";
    public static final String OPERATION_DEFAULT = "default";
    public static final String OPERATION_READ = "read";
    public static final String OPERATION_CREATE_BUDGET = "create";
    public static final String OPERATION_CREATE_ITEM = "createItem";
    public static final String OPERATION_CREATE_TRANSACTION = "createTransaction";
    public static final String OPERATION_UPDATE_BUDGET = "update";
    public static final String OPERATION_UPDATE_ITEM = "updateItem";
    public static final String OPERATION_UPDATE_TRANSACTION = "updateTransaction";
    public static final String OPERATION_DELETE_BUDGET = "delete";
    public static final String OPERATION_DELETE_ITEM = "deleteItem";
    public static final String OPERATION_DELETE_TRANSACTION = "deleteTransaction";
    
    // Parameters for form input names
    public static final String MONTH_DROP_DOWN = "monthDropdown";
    public static final String YEAR_DROP_DOWN = "yearDropdown";
    
    // Parameters for dropdown data
    public static final String MONTHS = "months";
    public static final String YEARS = "years";
    
    // application data (what is actually seen on the screen)
    public static final String USER = "user";
    public static final String BUDGET = "budget";
    public static final String BUDGETS = "budgets";
    public static final String ITEMS = "items";
    public static final String GIVING_ITEMS = "givingItems";
    public static final String FOOD_ITEMS = "foodItems";
    public static final String HOUSING_ITEMS = "housingItems";
    public static final String INSURANCE_ITEMS = "insuranceItems";
    public static final String LIFESTYLE_ITEMS = "lifestyleItems";
    public static final String TRANSPORTATION_ITEMS = "transportationItems";
}
