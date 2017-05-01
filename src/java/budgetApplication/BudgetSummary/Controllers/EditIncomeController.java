
package budgetApplication.BudgetSummary.Controllers;

import budgetApplication.BudgetSummary.BusinessLogic.IncomeManager;
import static budgetApplication.BudgetSummary.Controllers.Utilities.convertIncomeToXML;
import static budgetApplication.baseClasses.ConstantFields.USER;
import static budgetApplication.baseClasses.Utilities.isDouble;
import static budgetApplication.baseClasses.Utilities.isInteger;
import budgetApplication.dataContracts.Income;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EditIncomeController", urlPatterns = {"/EditIncome"})
public class EditIncomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try {
            
            HttpSession currentSession;
            double incomeAmount = 0;
            String incomeName = null;
            int incomeId = 0;
            
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                
                if(request.getParameterMap().containsKey("incomeId")) {
                    String itemIdInput = request.getParameter("incomeId");
                    incomeId = isInteger(itemIdInput) ? Integer.parseInt(itemIdInput) : 0;
                }
                
                if(request.getParameterMap().containsKey("incomeName")) {
                    incomeName = request.getParameter("incomeName");
                    incomeName = incomeName.replaceAll("'", ""); //apostrophe messes up UI
                }
                
                if(request.getParameterMap().containsKey("incomeAmount")) {
                    String itemAmountInput = request.getParameter("incomeAmount");
                    
                    if(isDouble(itemAmountInput)) {
                        incomeAmount = Double.parseDouble(itemAmountInput);
                    }
                }

                Income income = new Income();
                income.setId(incomeId);
                income.setName(incomeName);
                income.setAmount(incomeAmount);
                saveIncome(income, 0);
                income = getIncomeById(income.getId());
                String xmlDocument = convertIncomeToXML(income);
                
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
    
    private void saveIncome(Income newIncome, int budgetId) throws Exception {
        
        try {
            try(IncomeManager manager = new IncomeManager()) {
                manager.saveIncome(newIncome, budgetId);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    private Income getIncomeById(int id) throws Exception {
        
        try {
            try(IncomeManager manager = new IncomeManager()) {
                return manager.getIncomeById(id);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
