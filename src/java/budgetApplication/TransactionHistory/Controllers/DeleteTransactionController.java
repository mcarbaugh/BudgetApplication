/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetApplication.TransactionHistory.Controllers;

import budgetApplication.TransactionHistory.BusinessLogic.TransactionHistoryManager;
import static budgetApplication.baseClasses.ConstantFields.USER;
import static budgetApplication.baseClasses.Utilities.isInteger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eclat
 */
@WebServlet(name = "DeleteTransactionController", urlPatterns = {"/DeleteTransaction"})
public class DeleteTransactionController extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            HttpSession currentSession;
            int transactionId = 0;
            
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                
                if(request.getParameterMap().containsKey("transactionId")) {
                    String input = request.getParameter("transactionId");
                    transactionId = isInteger(input) ? Integer.parseInt(input) : 0;
                }
                
                try (TransactionHistoryManager manager = new TransactionHistoryManager()) {
                    manager.deletTransactionById(transactionId);
                }
                
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<transactions></transactions>");
                
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
