/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetApplication.TransactionHistory.Controllers;

import budgetApplication.BudgetSummary.BusinessLogic.ItemManager;
import budgetApplication.TransactionHistory.BusinessLogic.TransactionHistoryManager;
import static budgetApplication.baseClasses.ConstantFields.USER;
import static budgetApplication.baseClasses.TransactionUtilities.convertTransactionToXML;
import static budgetApplication.baseClasses.Utilities.isDouble;
import static budgetApplication.baseClasses.Utilities.isInteger;
import budgetApplication.dataContracts.TransactionHistory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EditTransactionController", urlPatterns = {"/EditTransaction"})
public class EditTransactionController extends HttpServlet {

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
            String transactionVendor = null;
            String transactionItem = null;
            String transactionCategory = null;
            double transactionAmount = 0;            
            String transactionDate = null;
            
            currentSession = request.getSession();
            if(currentSession.getAttribute(USER) != null) {
                
                if(request.getParameterMap().containsKey("transactionId")) {
                    String transactionIdInput = request.getParameter("transactionId");
                    transactionId = isInteger(transactionIdInput) ? Integer.parseInt(transactionIdInput) : 0;
                }
                
                if(request.getParameterMap().containsKey("transactionVendor")) {
                    transactionVendor = request.getParameter("transactionVendor");
                    transactionVendor = transactionVendor.replaceAll("'", ""); //apostrophe messes up UI
                }
                
                if(request.getParameterMap().containsKey("transactionItem")) {
                    transactionItem = request.getParameter("transactionItem");
                    transactionItem = transactionItem.replaceAll("'", ""); //apostrophe messes up UI
                }
                
                if(request.getParameterMap().containsKey("transactionCategory")) {
                    transactionCategory = request.getParameter("transactionCategory");
                    transactionCategory = transactionCategory.replaceAll("'", ""); //apostrophe messes up UI
                }
                
                if(request.getParameterMap().containsKey("transactionAmount")) {
                    String transactionAmountInput = request.getParameter("transactionAmount");
                    
                    if(isDouble(transactionAmountInput)) {
                        transactionAmount = Double.parseDouble(transactionAmountInput);
                    }
                }

                TransactionHistory transaction = new TransactionHistory();
                transaction.setId(transactionId);
                transaction.setVendor(transactionVendor);
                transaction.setItem(transactionItem);
                transaction.setCategory(transactionCategory);
                transaction.setAmount(transactionAmount);
                saveTransaction(transaction);
                transaction = getTransactionById(transaction.getId());
                String xmlDocument = convertTransactionToXML(transaction);
                
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
    
    private void saveTransaction(TransactionHistory newTransaction) throws Exception {
        
        try {
            try(TransactionHistoryManager manager = new TransactionHistoryManager()) {
                try(ItemManager iManager = new ItemManager()) {
                    int itemId = iManager.getItemIdByName(newTransaction.getItem());
                    manager.saveTransaction(newTransaction, itemId);
                }
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    private TransactionHistory getTransactionById(int id) throws Exception {
        
        try {
            try(TransactionHistoryManager manager = new TransactionHistoryManager()) {
                return manager.getTransactionById(id);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
