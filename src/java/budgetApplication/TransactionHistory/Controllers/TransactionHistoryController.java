
package budgetApplication.TransactionHistory.Controllers;
import static budgetApplication.baseClasses.TransactionUtilities.*;
import budgetApplication.TransactionHistory.BusinessLogic.TransactionHistoryManager;
import static budgetApplication.baseClasses.ConstantFields.USER;
import budgetApplication.dataContracts.*;
import java.io.IOException;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/TransactionHistory"})
public class TransactionHistoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            HttpSession currentSession;
            User user;
            int userId;
            List<TransactionHistory> transactions;
            List<TransactionHistory> weekTransactions;
            List<TransactionHistory> monthTransactions;
            Date[] week;
            Date[] month;
            
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                user = (User) currentSession.getAttribute(USER);
                userId = user.getId();
                
                transactions = getTransactionsByUserId(userId);
                LocalDate localDate = LocalDate.now();
                Date current = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                
                week = getCurrentWeekPeroid(current);
                month = getCurrentMonthPeroid(current);
                
                weekTransactions = getTransactionsInPeriod(transactions, week);
                monthTransactions = getTransactionsInPeriod(transactions, month);
         
                request.setAttribute("transactions", transactions);
                request.setAttribute("weekTransactions", weekTransactions);
                request.setAttribute("monthTransactions", monthTransactions);
                //navigate to the page
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
 
            return transactionManager.getAllTransactionsByUserId(userId);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
}
