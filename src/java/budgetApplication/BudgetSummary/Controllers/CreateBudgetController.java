
package budgetApplication.BudgetSummary.Controllers;

import static budgetApplication.baseClasses.ConstantFields.*;
import static budgetApplication.baseClasses.ConstantFields.USER;
import budgetApplication.baseClasses.MonthEnum;
import budgetApplication.businessLogic.BudgetSummaryManager;
import budgetApplication.dataContracts.*;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.SQLException;
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
        catch (SQLException ex) {
            // navigate to the selected budget, even though it was already created
        }
        catch(Exception ex) {
            
        }
    }
    
    private MonthEnum getMonthAsEnum(String monthInput) {
        
        MonthEnum month = MonthEnum.NONE;
        for(MonthEnum monthEnum : MonthEnum.values()) {
            if(monthInput.equals(monthEnum.name())) {
                month = monthEnum;
                break;
            }
        }
        
        return month;
    }
    
    private boolean isInteger(String value) {
        int size = value.length();
        
        for(int i = 0; i < size; i++) {
            if(!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        
        return size > 0;
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