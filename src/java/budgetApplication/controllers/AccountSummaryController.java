
package budgetApplication.controllers;

import budgetApplication.businessLogic.*;
import budgetApplication.dataContracts.Budget;
import budgetApplication.dataContracts.User;
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
            int userId;
            int budgetId;
            Double amount;
            Double spent;
            User user;
            List<Budget> budgets;
            HttpSession currentSession;
            
            try (BudgetManager budgetManager = new BudgetManager()) {
                budgetId = Integer.parseInt(request.getParameter(BUDGET_ID_FIELD));
                budgetManager.deleteBudgetById(budgetId);
                
                //retrieve new set of data and refresh page
                currentSession = request.getSession();
                userId = (int) currentSession.getAttribute(USER_ID_FIELD);
                user = budgetManager.getUserByUserId(userId);
                budgets = budgetManager.getAllBudgetsByUserId(userId);
                
                // get totals for each budget
                for (Budget budget : budgets) {
                    budgetId = budget.getId();
                    amount = budgetManager.getTotalAmountById(budgetId);
                    spent = budgetManager.getTotalSpentById(budgetId);
                    budget.setTotalSpent(spent);
                    budget.setTotalAmount(amount);
                }
                
                request.setAttribute(USER_FIELD, user);
                request.setAttribute(BUDGETS_FIELD, budgets);
                request.getRequestDispatcher("pages/accountSummary.jsp").forward(request, response);
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
}
