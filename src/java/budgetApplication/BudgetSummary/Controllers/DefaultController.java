
package budgetApplication.BudgetSummary.Controllers;

import static budgetApplication.baseClasses.ConstantFields.*;
import static budgetApplication.baseClasses.Utilities.*;
import budgetApplication.businessLogic.*;
import budgetApplication.dataContracts.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet(urlPatterns = {"/DefaultBudget"})
public class DefaultController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            HttpSession currentSession;
            User user;
            List<Budget> budgets;
            Budget activeBudget;
            
            int userId;
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                user = (User) currentSession.getAttribute(USER);
                userId = user.getId();
                
                                
                budgets = getBudgetsByUserId(userId);
                activeBudget = getActiveBudgetFromBudgets(budgets);
                
                if(activeBudget == null) {
                    activeBudget = createNewBudget(userId);
                    activeBudget.setId(getActiveBudgetId(userId));
                }
                
                String url = String.format("/Budget?budgetId=%s", activeBudget.getId());
                response.sendRedirect(request.getContextPath() + url);
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
    
    private Budget getActiveBudgetFromBudgets(List<Budget> budgets) {
 
        Budget activeBudget;
        activeBudget = budgets
                .stream()
                .filter(x -> x.getYear() == getCurrentYear())
                .filter(x -> x.getMonth() == getCurrentMonth())
                .findFirst()
                .get();
        
        return activeBudget;
    }
    
    private Budget createNewBudget(int userId) throws Exception {
        
        try {
            Budget newBudget = new Budget();
            
            try (BudgetSummaryManager manager = new BudgetSummaryManager()) {
                newBudget.setYear(getCurrentYear());
                newBudget.setMonth(getCurrentMonth());
                manager.saveBudgetByUserId(userId, newBudget);
            }
            
            return newBudget;
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    private int getActiveBudgetId(int userId) throws Exception {
        
        try {
            try (BudgetSummaryManager manager = new BudgetSummaryManager()) {
                Budget newBudget = new Budget();
                newBudget.setYear(getCurrentYear());
                newBudget.setMonth(getCurrentMonth());
                return manager.getLastIdByUserId(userId);
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }
}