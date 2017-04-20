
package budgetApplication.BudgetSummary.Controllers;

import budgetApplication.BudgetSummary.BusinessLogic.ItemManager;
import budgetApplication.BudgetSummary.BusinessLogic.TransactionManager;
import static budgetApplication.BudgetSummary.Controllers.Utilities.convertItemToXML;
import static budgetApplication.baseClasses.ConstantFields.*;
import static budgetApplication.baseClasses.Utilities.*;
import budgetApplication.dataContracts.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CreateTransactionController", urlPatterns = {"/CreateTransaction"})
public class CreateTransactionController extends HttpServlet {

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
            String transactionName = null;
            String transactionVendor = null;
            Double transactionAmount = 0.0;
            String transactionDate = null;
            
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                
                if(request.getParameterMap().containsKey("itemId")) {
                    String itemIdInput = request.getParameter("itemId");
                    itemId = isInteger(itemIdInput) ? Integer.parseInt(itemIdInput) : 0;
                }
                
                if(request.getParameterMap().containsKey("transactionName")) {
                    transactionName = request.getParameter("transactionName");
                    transactionName = transactionName.replaceAll("'", ""); //apostrophe messes up UI
                }
                
                if(request.getParameterMap().containsKey("transactionVendor")) {
                    transactionVendor = request.getParameter("transactionVendor");
                    transactionVendor = transactionVendor.replaceAll("'", ""); //apostrophe messes up UI
                }
                
                if(request.getParameterMap().containsKey("transactionAmount")) {
                    String transactionAmountInput = request.getParameter("transactionAmount");
                    
                    if(isDouble(transactionAmountInput)) {
                        transactionAmount = Double.parseDouble(transactionAmountInput);
                    }
                }
                
                if(request.getParameterMap().containsKey("transactionDate")) {
                    transactionDate = request.getParameter("transactionDate");
                }
                
                Transaction transaction = new Transaction();
                transaction.setItemId(itemId);
                transaction.setName(transactionName);
                transaction.setVendor(transactionVendor);
                transaction.setAmount(transactionAmount);
                transaction.setDate(transactionDate);
                saveTransaction(transaction);
                
                Item item = getItemById(itemId);
                String xmlDocument = convertItemToXML(item);
                
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write(xmlDocument);
            }
            else {
                currentSession.invalidate();
            }
        }
        catch(Exception ex) {
            throw new ServletException(ex);
        }
    }
    
    private void saveTransaction(Transaction transaction) throws Exception {
        try {
            try(TransactionManager transactionManager = new TransactionManager()) {
                transactionManager.saveTransaction(transaction);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    private Item getItemById(int itemId) throws Exception {
        
        try {
            Item item;
            try(ItemManager itemManager = new ItemManager()) {
                item = itemManager.getItemById(itemId);
            }
            
            return item;
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
