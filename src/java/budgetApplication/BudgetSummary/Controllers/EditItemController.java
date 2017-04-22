
package budgetApplication.BudgetSummary.Controllers;

import budgetApplication.BudgetSummary.BusinessLogic.ItemManager;
import static budgetApplication.BudgetSummary.Controllers.Utilities.convertItemToXML;
import budgetApplication.baseClasses.CategoryEnum;
import static budgetApplication.baseClasses.ConstantFields.USER;
import static budgetApplication.baseClasses.Utilities.getCategoryAsEnum;
import static budgetApplication.baseClasses.Utilities.isDouble;
import static budgetApplication.baseClasses.Utilities.isInteger;
import budgetApplication.dataContracts.Item;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EditItemController", urlPatterns = {"/EditItem"})
public class EditItemController extends HttpServlet {

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
            double itemSpent = 0;
            String itemName = null;
            CategoryEnum category = CategoryEnum.NONE;
            int itemId = 0;
            
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                
                if(request.getParameterMap().containsKey("itemId")) {
                    String itemIdInput = request.getParameter("itemId");
                    itemId = isInteger(itemIdInput) ? Integer.parseInt(itemIdInput) : 0;
                }
                
                if(request.getParameterMap().containsKey("itemName")) {
                    itemName = request.getParameter("itemName");
                    itemName = itemName.replaceAll("'", ""); //apostrophe messes up UI
                }
                
                if(request.getParameterMap().containsKey("itemAmount")) {
                    String itemAmountInput = request.getParameter("itemAmount");
                    
                    if(isDouble(itemAmountInput)) {
                        itemAmount = Double.parseDouble(itemAmountInput);
                    }
                }
                
                if(request.getParameterMap().containsKey("itemCategory")) {
                    String categoryInput = request.getParameter("itemCategory");
                    category = getCategoryAsEnum(categoryInput);
                }

                Item item = new Item();
                item.setId(itemId);
                item.setName(itemName);
                item.setCategory(category);
                item.setAmount(itemAmount);
                item.setSpent(itemSpent);
                saveItem(item, 0);
                
                String xmlDocument = convertItemToXML(item);
                
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
}
