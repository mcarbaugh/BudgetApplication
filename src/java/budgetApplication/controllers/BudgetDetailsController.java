
package budgetApplication.controllers;

import static budgetApplication.baseClasses.ConstantFields.*;
import budgetApplication.businessLogic.*;
import static budgetApplication.controllers.Utilities.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import budgetApplication.dataContracts.Item;

@WebServlet(name = "BudgetDetailsController", urlPatterns = {"/BudgetDetails"})
public class BudgetDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            HttpSession currentSession;
            int userId = 0;
            int budgetId = 0;
            String operation;
            List<Item> items = null;
            List<Item> givingItems = null;
            List<Item> foodItems = null;
            List<Item> housingItems = null;
            List<Item> insuranceItems = null;
            List<Item> lifestyleItems = null;
            List<Item> transportationItems = null;
            
            // get userId parameter from HTTPSession
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER_ID) != null) {
                userId = (int) currentSession.getAttribute(USER_ID);
            }
            
            // get budgetOperation and budgetId parameters from URL
            operation = request.getParameter(OPERATION);
            if(request.getParameterMap().containsKey(BUDGET_ID)) {
                budgetId = Integer.parseInt(request.getParameter(BUDGET_ID));
            }
            
            // process query
            switch(operation) {
                case OPERATION_CREATE:
                    
                    break;
                case OPERATION_READ:
                    items = processReadOperation(budgetId);
                    givingItems = selectGivingIems(items);
                    foodItems = selectFoodItems(items);
                    housingItems = selectHousingItems(items);
                    insuranceItems = selectInsuranceItems(items);
                    lifestyleItems = selectLifestyleItems(items);
                    transportationItems = selectTransportationItems(items);
                    break;
                case OPERATION_UPDATE:
                    
                    break;
                case OPERATION_DELETE:
                
                    break;
                default:
                    break;
            }
            
            // get new data for page
            request.setAttribute(BUDGET_ID, budgetId);
            request.setAttribute(ITEMS, items);
            request.setAttribute(GIVING_ITEMS, givingItems);
            request.setAttribute(FOOD_ITEMS, foodItems);
            request.setAttribute(HOUSING_ITEMS, housingItems);
            request.setAttribute(INSURANCE_ITEMS, insuranceItems);
            request.setAttribute(LIFESTYLE_ITEMS, lifestyleItems);
            request.setAttribute(TRANSPORTATION_ITEMS, transportationItems);
            
            // navigate to page
            request.getRequestDispatcher("pages/budgetDetailsPage.jsp").forward(request, response);
        }
        catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private List<Item> processReadOperation(int budgetId) throws Exception {
        try(ItemManager itemManager = new ItemManager()) {
            List<Item> items;
            items = itemManager.getAllItemsByBudgetId(budgetId);
            return items;
        }
        catch(Exception ex) {
            throw ex;
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
}