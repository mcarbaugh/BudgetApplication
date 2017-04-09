
package budgetApplication.controllers;

import static budgetApplication.baseClasses.ConstantFields.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import budgetApplication.businessLogic.LoginFormManager;
import budgetApplication.dataContracts.*;
import javax.servlet.http.HttpSession;

@WebServlet(name = "loginFormController", urlPatterns = {"/LoginForm"})
public class LoginFormController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String operation = "";
        HttpSession currentSession;
        
        if(request.getParameterMap().containsKey(OPERATION)) {
            operation = request.getParameter(OPERATION);
        }
        
        switch (operation) {
            case (OPERATION_SIGN_OUT):
                currentSession = request.getSession();
                currentSession.invalidate();
                break;
        }
        
        request.getRequestDispatcher("index.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int userId;           
            User user;
            String username = request.getParameter(USERNAME);
            String password = request.getParameter(PASSWORD);
            HttpSession currentSession;
            
            // check for username and password
            try (LoginFormManager manager = new LoginFormManager()) {
                user = manager.getUserByUsernameAndPassword(username, password);
                userId = user.getId();
            }
            
            // setup the session if username and password combination exists
            if(userId != 0) {
                currentSession = request.getSession();
                currentSession.setAttribute(USER_ID, user.getId());
            }
            
            if(userId != 0) {
                // if user is valid, tell client to load AccountSummaryController
                response.sendRedirect("AccountSummary?" + OPERATION + "=" + OPERATION_READ);
            }
            else {
                //if user is not valid, respond with 
                request.setAttribute(MESSAGE, LOGIN_ERROR_MESSAGE);
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
        }
        catch(Exception ex) {
            throw new ServletException(ex);
        }
    }
}
