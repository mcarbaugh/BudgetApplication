
package budgetApplication.BudgetSummary.Controllers;

import static budgetApplication.baseClasses.ConstantFields.*;
import budgetApplication.baseClasses.MonthEnum;
import static budgetApplication.baseClasses.Utilities.*;
import budgetApplication.businessLogic.BudgetSummaryManager;
import budgetApplication.businessLogic.ItemManager;
import budgetApplication.dataContracts.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Budget"})
public class ReadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            HttpSession currentSession;
            User user;
            int budgetId;
            List<Budget> budgets;
            Budget activeBudget;
            List<Item> items;
            List<Item> housingItems;
            List<Item> insuranceItems;
            List<Item> lifestyleItems;
            List<Item> foodItems;
            List<Item> transportationItems;
            List<Item> givingItems;
            
            List<String> monthDropDownItems;
            List<Integer> yearDropDownItems;
            
            int userId;
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                user = (User) currentSession.getAttribute(USER);
                userId = user.getId();
                
                if(request.getParameterMap().containsKey(BUDGET_ID)) {
                    budgetId = Integer.parseInt(request.getParameter(BUDGET_ID));
                }
                else {
                    budgetId = 1;
                }
                
                budgets = getBudgetsByUserId(userId);
                activeBudget = getActiveBudgetFromBudgetsById(budgets, budgetId);
                
                items = getItemsByBudgetId(activeBudget.getId());
                housingItems = selectHousingItems(items);
                insuranceItems = selectInsuranceItems(items);
                lifestyleItems = selectLifestyleItems(items);
                foodItems = selectFoodItems(items);
                transportationItems = selectTransportationItems(items);
                givingItems = selectGivingItems(items);
                monthDropDownItems = getMonthDropDownItems();
                yearDropDownItems = getYearDropDownItems();
                
                request.setAttribute(USER, user);
                request.setAttribute(BUDGET, activeBudget);
                request.setAttribute(BUDGETS, budgets);
                request.setAttribute(ITEMS, items);
                request.setAttribute(GIVING_ITEMS, givingItems);
                request.setAttribute(FOOD_ITEMS, foodItems);
                request.setAttribute(HOUSING_ITEMS, housingItems);
                request.setAttribute(INSURANCE_ITEMS, insuranceItems);
                request.setAttribute(LIFESTYLE_ITEMS, lifestyleItems);
                request.setAttribute(TRANSPORTATION_ITEMS, transportationItems);
                request.setAttribute(MONTHS, monthDropDownItems);
                request.setAttribute(YEARS, yearDropDownItems);
                
                request.getRequestDispatcher("/pages/BudgetSummaryPage.jsp").forward(request, response);
            }
            else {
                currentSession.invalidate();
            }            
        }
        catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
    
    private List<Budget> getBudgetsByUserId(int userId) throws Exception {
        try (BudgetSummaryManager budgetManager = new BudgetSummaryManager()) {
 
            return budgetManager.getAllBudgetsByUserId(userId);
        }
    }
    
    private Budget getActiveBudgetFromBudgetsById(List<Budget> budgets, int budgetId) {
 
        Budget activeBudget;
        activeBudget = budgets
                .stream()
                .filter(x -> x.getId() == budgetId)
                .findFirst()
                .get();
        
        return activeBudget;
    }
    
    private List<Item> getItemsByBudgetId(int budgetId) throws Exception {
        try {
            try(ItemManager itemManager = new ItemManager()) {
                
                return itemManager.getAllItemsByBudgetId(budgetId);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    private List<String> getMonthDropDownItems() {
        
        List<String> months = new ArrayList();
        for(MonthEnum month : MonthEnum.values()) {
            months.add(month.name());
        }
        
        months.remove(MonthEnum.NONE.name());
        return months;
    }
    
    private List<Integer> getYearDropDownItems() {
        int currentYear = getCurrentYear();
        int upperBound = currentYear + 20;
        List<Integer> years = new ArrayList();
        
        for(int i = currentYear; i < upperBound; i++) {
            years.add(i);
        }
        
        return years;
    }
}
