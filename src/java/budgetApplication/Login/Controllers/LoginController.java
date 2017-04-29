
package budgetApplication.Login.Controllers;

import budgetApplication.Login.BusinessLogic.LoginFormManager;
import static budgetApplication.baseClasses.ConstantFields.*;
import budgetApplication.dataContracts.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/Login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession currentSession = request.getSession();
        currentSession.invalidate();
        request.getRequestDispatcher("index.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {          
            User user;
            String username = request.getParameter(USERNAME);
            String password = request.getParameter(PASSWORD);
            HttpSession currentSession;
            
            // check for username and password
            try (LoginFormManager manager = new LoginFormManager()) {
                user = manager.getUserByUsernameAndPassword(username, password);
            }
            
            // setup the session if username and password combination exists
            if(user.getId() > 0) {
                currentSession = request.getSession();
                currentSession.setAttribute(USER, user);
                response.sendRedirect(request.getContextPath() + "/DefaultBudget");
            }
            else {
                //if user is not valid, respond with 
                request.setAttribute(MESSAGE, LOGIN_ERROR_MESSAGE);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        catch(Exception ex) {
            throw new ServletException(ex);
        }
    }
}
