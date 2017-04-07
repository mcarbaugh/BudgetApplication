
package budgetApplication.controllers;

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
            ItemManager manager;
            List<Item> items = null;
            
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
                    manager = new ItemManager();
                    items = manager.getAllItemsByBudgetId(budgetId);
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
    
    private void processDeleteOperation(int itemId) {
        try {
            try (ItemManager itemManager = new ItemManager()) {
                itemManager.deletItemById(itemId);
            }    
        }
        catch (Exception ex) {
            
        }
    }
}
