
package budgetApplication.TransactionHistory.Controllers;
import budgetApplication.BudgetSummary.BusinessLogic.BudgetManager;
import static budgetApplication.baseClasses.ConstantFields.*;
import budgetApplication.dataContracts.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Transaction"})
public class ReadTransactionsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            HttpSession currentSession;
            User user;
            List<Budget> budgets;
            Budget activeBudget;
            int budgetId;
            
            int userId;
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                user = (User) currentSession.getAttribute(USER);
                userId = user.getId();
                
                if(request.getParameterMap().containsKey(BUDGET_ID)){
                    budgetId = Integer.parseInt(request.getParameter(BUDGET_ID));
                }
                else {
                    budgetId = 0;
                }
                
                budgets = getBudgetsByUserId(userId);
                activeBudget = getActiveBudgetFromBudgetsById(budgets, budgetId);
                
                request.setAttribute(USER, user);
                request.setAttribute(BUDGETS, budgets);
                request.setAttribute(BUDGET, activeBudget);
                request.setAttribute("budgetId", budgetId);
                request.getRequestDispatcher("/pages/TransactionHistory.jsp").forward(request, response);
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
        try (BudgetManager budgetManager = new BudgetManager()) {
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
}
