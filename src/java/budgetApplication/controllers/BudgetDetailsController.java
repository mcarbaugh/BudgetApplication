
package budgetApplication.controllers;

import budgetApplication.baseClasses.CategoryEnum;
import static budgetApplication.baseClasses.ConstantFields.*;
import budgetApplication.businessLogic.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import budgetApplication.dataContracts.Item;
import java.util.stream.Collectors;

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
            if(currentSession.getAttribute(USER_ID_FIELD) != null) {
                userId = (int) currentSession.getAttribute(USER_ID_FIELD);
            }
            
            // get budgetOperation and budgetId parameters from URL
            operation = request.getParameter(OPERATION_FIELD);
            if(request.getParameterMap().containsKey(BUDGET_ID_FIELD)) {
                budgetId = Integer.parseInt(request.getParameter(BUDGET_ID_FIELD));
            }
            
            // process query
            switch(operation) {
                case OPERATION_CREATE:
                    
                    break;
                case OPERATION_READ:
                    items = processReadOperation(budgetId);
                    
                    givingItems = items.stream()
                            .filter(x -> x.getCategory() == CategoryEnum.GIVING)
                            .collect(Collectors.toList());
                    
                    foodItems = items.stream()
                            .filter(x -> x.getCategory() == CategoryEnum.FOOD)
                            .collect(Collectors.toList());
                    
                    housingItems = items.stream()
                            .filter(x -> x.getCategory() == CategoryEnum.HOUSING)
                            .collect(Collectors.toList());
                    
                    insuranceItems = items.stream()
                            .filter(x -> x.getCategory() == CategoryEnum.INSURANCE_TAX)
                            .collect(Collectors.toList());
                    
                    lifestyleItems = items.stream()
                            .filter(x -> x.getCategory() == CategoryEnum.LIFESTYLE)
                            .collect(Collectors.toList());
                    
                    transportationItems = items.stream()
                            .filter(x -> x.getCategory() == CategoryEnum.TRANSPORTATION)
                            .collect(Collectors.toList());
                    break;
                case OPERATION_UPDATE:
                    
                    break;
                case OPERATION_DELETE:
                
                    break;
                default:
                    break;
            }
            
            // get new data for page
            request.setAttribute(BUDGET_ID_FIELD, budgetId);
            request.setAttribute(ITEMS_FIELD, items);
            request.setAttribute(HOUSING_ITEMS, housingItems);
            
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