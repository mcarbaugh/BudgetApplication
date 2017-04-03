
package budgetApplication.controllers;

import budgetApplication.businessLogic.*;
import budgetApplication.dataContracts.*;
import static budgetApplication.baseClasses.ConstantFields.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AccountSummaryController", urlPatterns = {"/AccountSummary"})
public class AccountSummaryController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int userId = 0;
            int budgetId = 0;
            User user;
            List<Budget> budgets;
            String operation;
            HttpSession currentSession;
            
            // get userId parameter from HTTPSession
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER_ID_FIELD) != null) {
                userId = (int) currentSession.getAttribute(USER_ID_FIELD);
            }
            
            // get budgetOperation and budgetId parameters from URL
            operation = request.getParameter(OPERATION_FIELD);
            
            // check if budgetId parameter was passed in URL (false if operation is a read)
            if(request.getParameterMap().containsKey(BUDGET_ID_FIELD)) {
                budgetId = Integer.parseInt(request.getParameter(BUDGET_ID_FIELD));
            }
            
            // process the query
            switch(operation) {
                case OPERATION_CREATE:
                    
                    break;
                case OPERATION_READ:
                    
                    break;
                case OPERATION_UPDATE:
                    
                    break;
                case OPERATION_DELETE:
                    processDeleteOperation(budgetId);
                    break;
                default:
                    break;
            }
            
            // get new data for page
            user = getUser(userId);
            budgets = getBudgets(userId);
                    
            // refresh the page
            request.setAttribute(USER_FIELD, user);
            request.setAttribute(BUDGETS_FIELD, budgets);
            request.getRequestDispatcher("pages/accountSummary.jsp").forward(request, response);
        }
        catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private User getUser(int userId) throws Exception {
        try {
            try (BudgetManager budgetManager = new BudgetManager()) {
                User user;
                user = budgetManager.getUserByUserId(userId);
                return user;
            }    
        }
        catch (Exception ex) {
            throw ex;
        } 
    }
    
    private List<Budget> getBudgets(int userId) throws Exception {
        try {
            try (BudgetManager budgetManager = new BudgetManager()) {
                int budgetId;
                Double amount;
                Double spent;
                List<Budget> budgets;
                budgets = budgetManager.getAllBudgetsByUserId(userId);
                
                // get totals for each budget
                for (Budget budget : budgets) {
                    budgetId = budget.getId();
                    amount = budgetManager.getTotalAmountById(budgetId);
                    spent = budgetManager.getTotalSpentById(budgetId);
                    budget.setTotalSpent(spent);
                    budget.setTotalAmount(amount);
                }
                
                return budgets;
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    private void processDeleteOperation(int budgetId) throws Exception {
        try {
            try (BudgetManager budgetManager = new BudgetManager()) {
                budgetManager.deleteBudgetById(budgetId);
            }    
        }
        catch (Exception ex) {
            throw ex;
        }
    }
}