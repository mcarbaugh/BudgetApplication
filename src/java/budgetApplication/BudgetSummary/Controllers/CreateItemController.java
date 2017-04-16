
package budgetApplication.BudgetSummary.Controllers;

import budgetApplication.baseClasses.CategoryEnum;
import static budgetApplication.baseClasses.ConstantFields.USER;
import static budgetApplication.baseClasses.Utilities.*;
import budgetApplication.dataContracts.*;
import budgetApplication.dataContracts.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CreateItemController", urlPatterns = {"/CreateItem"})
public class CreateItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        //response.setContentType("text/plain");
        //response.setCharacterEncoding("UTF-8");
        //response.getWriter().write("blah");
        //response.getWriter().close();
        
        try {
            
            HttpSession currentSession;
            User user;
            double amount = 0;
            String description = null;
            CategoryEnum category = CategoryEnum.NONE;
            int budgetId = 0;
            
            int userId;
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                user = (User) currentSession.getAttribute(USER);
                userId = user.getId();
                
                if(request.getParameterMap().containsKey("budgetId")) {
                    String input = request.getParameter("budgetId");
                    budgetId = isInteger(input) ? Integer.parseInt(input) : 0;
                }
                
                if(request.getParameterMap().containsKey("itemAmount")) {
                    String itemInput = request.getParameter("itemAmount");
                    
                    if(isDouble(itemInput)) {
                        amount = Double.parseDouble(itemInput);
                    }
                }
                
                if(request.getParameterMap().containsKey("itemDescription")) {
                    description = request.getParameter("itemDescription");
                }
                
                if(request.getParameterMap().containsKey("itemCategory")) {
                    String categoryInput = request.getParameter("itemCategory");
                    category = getCategoryAsEnum(categoryInput);
                }
                
                Item newItem = new Item();
                newItem.setAmount(amount);
                newItem.setDescription(description);
                newItem.setCategory(CategoryEnum.NONE);
                
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