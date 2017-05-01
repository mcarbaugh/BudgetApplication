
package budgetApplication.BudgetSummary.Controllers;

import budgetApplication.BudgetSummary.BusinessLogic.IncomeManager;
import static budgetApplication.BudgetSummary.Controllers.Utilities.convertIncomesToXML;
import budgetApplication.dataContracts.Income;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetAllIncomesController", urlPatterns = {"/GetAllIncomes"})
public class GetAllIncomesController extends HttpServlet {

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
            
            List<Income> incomes;
            try(IncomeManager manager = new IncomeManager()) {
                incomes = manager.getAllIncomesByBudgetId(budgetId);
            }
            
            String xmlDocument = convertIncomesToXML(incomes);

            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(xmlDocument);
        }
        catch(Exception ex) {
            throw new ServletException(ex);
        }
    }
}