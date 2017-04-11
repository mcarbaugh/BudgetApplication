
package budgetApplication.controllers;

import static budgetApplication.baseClasses.ConstantFields.*;
import budgetApplication.baseClasses.MonthEnum;
import budgetApplication.businessLogic.*;
import static budgetApplication.controllers.Utilities.*;
import budgetApplication.dataContracts.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "BudgetDetailsController", urlPatterns = {"/" + BUDGET_SUMMARY_PAGE})
public class BudgetSummaryController extends HttpServlet {

    HttpSession currentSession;
    private User user = null;
    private String operation;
    private int budgetId = 0;
    private Budget activeBudget = null;
    private List<Budget> budgets = null;
    private List<Item> items = null;
    private List<Item> givingItems = null;
    private List<Item> foodItems = null;
    private List<Item> housingItems = null;
    private List<Item> insuranceItems = null;
    private List<Item> lifestyleItems = null;
    private List<Item> transportationItems = null;
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            // get user parameter from HTTPSession
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                user = (User) currentSession.getAttribute(USER);
            }
            
            // get budgetOperation and budgetId parameters from URL
            if(request.getParameterMap().containsKey(OPERATION)) {
                operation = request.getParameter(OPERATION);
            }
            else {
                operation = OPERATION_DEFAULT;
            }
            
            if(request.getParameterMap().containsKey(BUDGET_ID)) {
                budgetId = Integer.parseInt(request.getParameter(BUDGET_ID));
            }
            
            // process query
            switch(operation) {
                case OPERATION_CREATE:
                    
                    break;
                case OPERATION_READ:
                    processReadOperation();
                    break;
                case OPERATION_UPDATE:
                    
                    break;
                case OPERATION_DELETE:
                
                    break;
                default:
                    processDefaultOperation(user.getId());
                    break;
            }
            
            // get new data for page
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
            
            // navigate to page
            request.getRequestDispatcher("pages/BudgetSummaryPage.jsp").forward(request, response);
        }
        catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private void processReadOperation() throws Exception {
        try {
            try (BudgetManager budgetManager = new BudgetManager()) {
                budgets = budgetManager.getAllBudgetsByUserId(user.getId());

                if(budgetId > 0) {
                    activeBudget = budgets.stream()
                        .filter(x -> x.getId() == budgetId)
                        .collect(Collectors.toList()).get(0);
                }

                populateItems();
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    private void processDefaultOperation(int userId) throws Exception {

        int currentYear = getCurrentYear();
        MonthEnum currentMonth = getCurrentMonth();

        try (BudgetManager budgetManager = new BudgetManager()) {
            budgets = budgetManager.getAllBudgetsByUserId(userId);
            
            List<Budget> possibleBudgets = budgets.stream()
                .filter(x -> x.getYear() == currentYear)
                .filter(x -> x.getMonth() == currentMonth)
                .collect(Collectors.toList());

            if(possibleBudgets.size() > 0) {                
                activeBudget = possibleBudgets.get(0);
                populateItems();
            }
            else {
                Budget newBudget = new Budget();
                newBudget.setMonth(currentMonth);
                newBudget.setYear(currentYear);
                budgetManager.insertBudgetByUserId(userId, newBudget);
            }
        }
    }
    
    private void processDeleteOperation(int itemId) throws Exception {
        try {
            try (ItemManager itemManager = new ItemManager()) {
                itemManager.deletItemById(itemId);
            }    
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    private void populateItems() {
        try {
            try(ItemManager itemManager = new ItemManager()) {
                items = itemManager.getAllItemsByBudgetId(activeBudget.getId());
            }

            givingItems = selectGivingIems(items);
            foodItems = selectFoodItems(items);
            housingItems = selectHousingItems(items);
            insuranceItems = selectInsuranceItems(items);
            lifestyleItems = selectLifestyleItems(items);
            transportationItems = selectTransportationItems(items);
        }
        catch(Exception ex) {
            
        }
    }
}