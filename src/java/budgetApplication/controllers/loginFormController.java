package budgetApplication.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import budgetApplication.businessLogic.LoginFormManager;
import budgetApplication.dataContracts.User;

@WebServlet(name = "loginFormController", urlPatterns = {"/loginForm"})
public class loginFormController extends HttpServlet {

    private static final String MESSAGE = "Username or password not recognized.";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try (LoginFormManager manager = new LoginFormManager()) {
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            User user;
            user = manager.getUserByUsernameAndPassword(username, password);
            
            int userId = user.getId();
            if(userId == 0) {
                request.getRequestDispatcher("index.jsp").include(request, response); 
            } 
            else {
                request.setAttribute("user", user);
                request.setAttribute("pageTitle", "Budget Application - Account Overview");
                request.getRequestDispatcher("pages/accountOverview.jsp").forward(request, response);
            }
        }
        catch(Exception ex) {
            throw new ServletException(ex);
        }
    }
}
