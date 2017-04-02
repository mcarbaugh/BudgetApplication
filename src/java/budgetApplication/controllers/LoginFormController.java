package budgetApplication.controllers;

import budgetApplication.businessLogic.BudgetManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import budgetApplication.businessLogic.LoginFormManager;
import budgetApplication.dataContracts.*;
import java.util.List;

@WebServlet(name = "loginFormController", urlPatterns = {"/loginForm"})
public class LoginFormController extends HttpServlet {

    private static final String MESSAGE = "Username or password not recognized.";
    private static final String USERNAME_FIELD = "username";
    private static final String PASSWORD_FIELD = "password";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int userId;
            int budgetId;            
            Double amount;
            Double spent;
            User user;
            List<Budget> budgets;
            String username = request.getParameter(USERNAME_FIELD);
            String password = request.getParameter(PASSWORD_FIELD);
            
            try (LoginFormManager manager = new LoginFormManager()) {
                user = manager.getUserByUsernameAndPassword(username, password);
                userId = user.getId();
            }
            
            try (BudgetManager budgetManager = new BudgetManager()) {
                budgets = budgetManager.getAllBudgetsByUserId(userId);
                
                // get totals for each budget
                for (Budget budget : budgets) {
                    budgetId = budget.getId();
                    amount = budgetManager.getTotalAmountById(budgetId);
                    spent = budgetManager.getTotalSpentById(budgetId);
                    budget.setTotalSpent(spent);
                    budget.setTotalAmount(amount);
                }
            }
            
            if(userId != 0) {
                request.setAttribute("user", user);
                request.setAttribute("budgets", budgets);
                request.getRequestDispatcher("pages/accountOverview.jsp").forward(request, response);
            }
            else {
                request.setAttribute("message", "Username or password invalid.");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
        }
        catch(Exception ex) {
            throw new ServletException(ex);
        }
    }
}
