
package budgetApplication.BudgetSummary.Controllers;

import budgetApplication.baseClasses.BudgetApplicationFault;
import static budgetApplication.baseClasses.ConstantFields.*;
import static budgetApplication.baseClasses.Utilities.*;
import budgetApplication.baseClasses.MonthEnum;
import budgetApplication.businessLogic.BudgetSummaryManager;
import budgetApplication.dataContracts.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/CreateBudget"})
public class CreateBudgetController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            HttpSession currentSession;
            User user;
            String monthInput;
            String yearInput;
            MonthEnum month = MonthEnum.NONE;
            int year = 0;
            
            int userId;
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                user = (User) currentSession.getAttribute(USER);
                userId = user.getId();
                
                if(request.getParameterMap().containsKey(MONTH_DROP_DOWN)) {
                    monthInput = request.getParameter(MONTH_DROP_DOWN).toUpperCase();
                    month = getMonthAsEnum(monthInput);
                }
                
                if(request.getParameterMap().containsKey(YEAR_DROP_DOWN)) {
                    yearInput = request.getParameter(YEAR_DROP_DOWN);
                    year = isInteger(yearInput) ? Integer.parseInt(yearInput) : 0;
                }
                
                if(month == MonthEnum.NONE || year == 0) {
                    response.sendRedirect(request.getContextPath() + "/DefaultBudget");
                }
                else {
                    Budget newBudget = new Budget();
                    newBudget.setMonth(month);
                    newBudget.setYear(year);
                    insertNewBudget(userId, newBudget);
                    newBudget.setId(getMaxId(userId));
                    
                    String url = String.format("/Budget?budgetId=%s", newBudget.getId());
                    response.sendRedirect(request.getContextPath() + url);
                }
            }
            else {
                currentSession.invalidate();
            }          
            
        }
        catch (BudgetApplicationFault ex) {
            try (BudgetSummaryManager manager = new BudgetSummaryManager()) {
                // If the insert failed, the budget probably already exists.
                // Navigate to the existing budget for better user experience.
                User user;
                int userId;
                Budget budget;
                int budgetId;
                
                user = (User)(request.getSession().getAttribute(USER));
                userId = user.getId();
                budget = (Budget) ex.getData().getProperty();
                budgetId = manager.getIdByMonthYear(userId, budget.getMonth(), budget.getYear());
                
                String url = String.format("/Budget?budgetId=%s", budgetId);
                response.sendRedirect(request.getContextPath() + url);
            }
            catch(Exception exception) {
                // If all else fails, go to default page
                response.sendRedirect(request.getContextPath() + "/DefaultBudget");
            }
        }
        catch(Exception ex) {
            
        }
    }

    private void insertNewBudget(int userId, Budget newBudget) throws Exception {
        
        try{
            
            try (BudgetSummaryManager manager = new BudgetSummaryManager()) {
                manager.saveBudgetByUserId(userId, newBudget);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    private int getMaxId(int userId) throws Exception {
        try{
            
            try (BudgetSummaryManager manager = new BudgetSummaryManager()) {
                return manager.getLastIdByUserId(userId);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}