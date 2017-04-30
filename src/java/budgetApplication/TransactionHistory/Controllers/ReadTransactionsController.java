
package budgetApplication.TransactionHistory.Controllers;
import budgetApplication.TransactionHistory.BusinessLogic.TransactionHistoryManager;
import static budgetApplication.baseClasses.ConstantFields.BUDGET_ID;
import static budgetApplication.baseClasses.ConstantFields.USER;
import budgetApplication.dataContracts.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Transaction"})
public class ReadTransactionsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            HttpSession currentSession;
            User user;
            int budgetId;
            
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                user = (User) currentSession.getAttribute(USER);
                if(request.getParameterMap().containsKey(BUDGET_ID)){
                    budgetId = Integer.parseInt(request.getParameter(BUDGET_ID));
                }
                else {
                    budgetId = 0;
                }
                //navigate to the page
                request.setAttribute("budgetId", budgetId);
                request.getRequestDispatcher("/pages/TransactionHistory.jsp").forward(request, response);
            }
            else {
                currentSession.invalidate();
            }            
        }
        catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
    
    private List<TransactionHistory> getTransactionsByUserId(int userId) throws Exception {
        try (TransactionHistoryManager transactionManager = new TransactionHistoryManager()) {
 
            return transactionManager.getAllTransactionsByBudgetId(userId);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
}
