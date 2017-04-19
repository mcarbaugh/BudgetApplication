
package budgetApplication.BudgetSummary.Controllers;

import budgetApplication.BudgetSummary.BusinessLogic.ItemManager;
import static budgetApplication.baseClasses.ConstantFields.USER;
import static budgetApplication.baseClasses.Utilities.isInteger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DeleteItemController", urlPatterns = {"/DeleteItem"})
public class DeleteItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            HttpSession currentSession;
            int itemId = 0;
            
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                
                if(request.getParameterMap().containsKey("itemId")) {
                    String input = request.getParameter("itemId");
                    itemId = isInteger(input) ? Integer.parseInt(input) : 0;
                }
                
                try (ItemManager manager = new ItemManager()) {
                    manager.deletItemById(itemId);
                }
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
