
package budgetApplication.controllers;

import static budgetApplication.baseClasses.ConstantFields.*;
import budgetApplication.baseClasses.MonthEnum;
import static budgetApplication.baseClasses.Utilities.order;
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
    private List<String> months;
    private List<Integer> years;
    MonthEnum newMonth;
    int newYear;
            
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

            // perform the operation as intructed
            switch(operation) {
                case OPERATION_READ:
                    processReadOperation();
                    break;
                default:
                    processDefaultOperation(user.getId());
                    break;
            }
            
            // setup outgoing data
            populateItems();
            populateMonthDropDowns();
            populateYearDropDowns();
            
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
            request.setAttribute(MONTHS, months);
            request.setAttribute(YEARS, years);
            
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
        
        try {
            // get user parameter from HTTPSession
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                user = (User) currentSession.getAttribute(USER);
            }
            
            // get operation from the hidden field in the form
            if(request.getParameterMap().containsKey(OPERATION)) {
                operation = request.getParameter(OPERATION);
            }
            
            // get values from fields in the form
            if(request.getParameterMap().containsKey(MONTH_DROP_DOWN)) {
                newMonth = MonthEnum.valueOf(request.getParameter(MONTH_DROP_DOWN));
            }

            if(request.getParameterMap().containsKey(YEAR_DROP_DOWN)) {
                newYear = Integer.parseInt(request.getParameter(YEAR_DROP_DOWN));
            }
            
            // perform the operation as instructed
            switch (operation){
                case OPERATION_CREATE_BUDGET:
                    processCreateBudgetOperation();
                    break;
            }
            
            // setup outgoing data
            populateItems();
            populateMonthDropDowns();
            populateYearDropDowns();
            
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
            request.setAttribute(MONTHS, months);
            request.setAttribute(YEARS, years);
            
            request.getRequestDispatcher("pages/BudgetSummaryPage.jsp").forward(request, response);
        }
        catch(Exception ex) {
            throw new ServletException(ex);
        }
    }
    
    private void processCreateBudgetOperation() throws Exception {
        try {
            try (BudgetManager budgetManager = new BudgetManager()) {
                Budget newBudget = new Budget(newMonth, newYear);
                budgetManager.saveBudgetByUserId(user.getId(), newBudget);
                int newId = budgetManager.getLastIdByUserId(user.getId());
                newBudget.setId(newId);
                activeBudget = newBudget;
                budgets = budgetManager.getAllBudgetsByUserId(user.getId());
            }
        }
        catch(Exception ex) {
            throw ex;
        }
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
                budgetManager.saveBudgetByUserId(userId, newBudget);
                newBudget.setId(budgetManager.getLastIdByUserId(userId));
                activeBudget = newBudget;
                budgets.add(newBudget);
                order(budgets);
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
    
    private void populateItems() throws Exception {
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
            throw ex;
        }
    }
    
    private void populateMonthDropDowns() {
        months = new ArrayList<>();
        months.add(MonthEnum.JANUARY.name());
        months.add(MonthEnum.FEBRUARY.name());
        months.add(MonthEnum.MARCH.name());
        months.add(MonthEnum.APRIL.name());
        months.add(MonthEnum.MAY.name());
        months.add(MonthEnum.JUNE.name());
        months.add(MonthEnum.JULY.name());
        months.add(MonthEnum.AUGUST.name());
        months.add(MonthEnum.SEPTEMBER.name());
        months.add(MonthEnum.OCTOBER.name());
        months.add(MonthEnum.NOVEMBER.name());
        months.add(MonthEnum.DECEMBER.name());
    }
    
    private void populateYearDropDowns() {
        int currentYear = getCurrentYear();
        int upperBound = currentYear + 20;
        
        years = new ArrayList();
        for(int i = currentYear; i < upperBound; i++) {
            years.add(i);
        }
    }
}