
package budgetApplication.BudgetSummary.Controllers;

import budgetApplication.BudgetSummary.BusinessLogic.ItemManager;
import budgetApplication.baseClasses.CategoryEnum;
import static budgetApplication.baseClasses.ConstantFields.USER;
import static budgetApplication.baseClasses.Utilities.*;
import static budgetApplication.BudgetSummary.Controllers.Utilities.*;
import budgetApplication.dataContracts.*;
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
        
        try {
            
            HttpSession currentSession;
            double itemAmount = 0;
            String itemName = null;
            CategoryEnum category = CategoryEnum.NONE;
            int budgetId = 0;
            
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                
                if(request.getParameterMap().containsKey("budgetId")) {
                    String input = request.getParameter("budgetId");
                    budgetId = isInteger(input) ? Integer.parseInt(input) : 0;
                }
                
                if(request.getParameterMap().containsKey("itemName")) {
                    itemName = request.getParameter("itemName");
                    itemName = itemName.replaceAll("'", ""); //apostrophe messes up UI
                }
                
                if(request.getParameterMap().containsKey("itemCategory")) {
                    String categoryInput = request.getParameter("itemCategory");
                    category = getCategoryAsEnum(categoryInput);
                }
                
                if(request.getParameterMap().containsKey("itemAmount")) {
                    String itemInput = request.getParameter("itemAmount");
                    
                    if(isDouble(itemInput)) {
                        itemAmount = Double.parseDouble(itemInput);
                    }
                }
                
                Item newItem = new Item();
                newItem.setName(itemName);
                newItem.setCategory(category);
                newItem.setAmount(itemAmount);
                
                saveItem(newItem, budgetId);
                newItem.setId(getLastIdByBudgetId(budgetId));
                
                String xmlDocument = convertItemToXML(newItem);
                
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write(xmlDocument);
            }
            else {
                currentSession.invalidate();
            }
        }
        catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
    
    private void saveItem(Item newItem, int budgetId) throws Exception {
        
        try {
            try(ItemManager manager = new ItemManager()) {
                manager.saveItem(newItem, budgetId);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    private int getLastIdByBudgetId(int budgetId) throws Exception {
        
        try {
            try(ItemManager manager = new ItemManager()) {
                return manager.getLastIdByBudgetId(budgetId);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}