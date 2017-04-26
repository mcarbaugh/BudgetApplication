
package budgetApplication.BudgetSummary.Controllers;

import budgetApplication.BudgetSummary.BusinessLogic.ItemManager;
import static budgetApplication.BudgetSummary.Controllers.Utilities.convertItemsToXML;
import budgetApplication.dataContracts.Item;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetAllItemsController", urlPatterns = {"/GetAllItems"})
public class GetAllItemsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            int budgetId = 0;
            if(request.getParameterMap().containsKey("budgetId")) {
                budgetId = Integer.parseInt(request.getParameter("budgetId"));
            }
            else {
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/Login");
            }
            
            List<Item> items;
            try(ItemManager manager = new ItemManager()) {
                items = manager.getAllItemsByBudgetId(budgetId);
            }
            
            String xmlDocument = convertItemsToXML(items);

            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(xmlDocument);
        }
        catch(Exception ex) {
            throw new ServletException(ex);
        }
    }
}