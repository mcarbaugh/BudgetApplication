
package budgetApplication.BudgetSummary.Controllers;

import budgetApplication.BudgetSummary.BusinessLogic.IncomeManager;
import static budgetApplication.baseClasses.ConstantFields.USER;
import static budgetApplication.baseClasses.Utilities.isInteger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DeleteIncomeController", urlPatterns = {"/DeleteIncome"})
public class DeleteIncomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            HttpSession currentSession;
            int incomeId = 0;
            
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                
                if(request.getParameterMap().containsKey("incomeId")) {
                    String input = request.getParameter("incomeId");
                    incomeId = isInteger(input) ? Integer.parseInt(input) : 0;
                }
                
                try (IncomeManager manager = new IncomeManager()) {
                    manager.deleteIncomeById(incomeId);
                }
                
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<items></items>");
                
            }
            else {
                currentSession.invalidate();
            }
        }
        catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
