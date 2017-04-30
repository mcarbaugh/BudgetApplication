
package budgetApplication.CreateAccount.Controllers;

import budgetApplication.CreateAccount.BusinessLogic.CreateAccountManager;
import budgetApplication.dataContracts.UserAccount;
import com.mysql.jdbc.StringUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateAccountController", urlPatterns = {"/CreateAccount"})
public class CreateAccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("/pages/CreateAccountPage.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try
        {
            String username = "";
            String firstName = "";
            String lastName = "";
            String password = "";
            String confirmPassword = "";
            String username_message = "";
            String firstName_message = "";
            String lastName_message = "";
            String password_message = "";
            Boolean errorFound = false;
            
            if(request.getParameterMap().containsKey("username")) {
                username = request.getParameter("username");
            }

            if(request.getParameterMap().containsKey("firstName")) {
                firstName = request.getParameter("firstName");
            }

            if(request.getParameterMap().containsKey("lastName")) {
                lastName = request.getParameter("lastName");
            }

            if(request.getParameterMap().containsKey("password")) {
                password = request.getParameter("password");
            }

            if(request.getParameterMap().containsKey("confirmPassword")) {
                confirmPassword = request.getParameter("confirmPassword");
            }
            
            
            String usernameCheck;
            try (CreateAccountManager accountManager = new CreateAccountManager()) {
                usernameCheck = accountManager.getUsername(username);
            }
            
            if(!StringUtils.isEmptyOrWhitespaceOnly(usernameCheck)) {
                username_message = "Username already exists.";
                username = "";
                firstName = "";
                lastName = "";
                errorFound = true;
            }
            else if(StringUtils.isNullOrEmpty(username) || StringUtils.isEmptyOrWhitespaceOnly(username)) {
                username_message = "Enter a username.";
                errorFound = true;
                username = "";
            }
            else if(StringUtils.isNullOrEmpty(firstName) || StringUtils.isEmptyOrWhitespaceOnly(firstName)) {
                firstName_message = "Enter your first name.";
                errorFound = true;
                firstName = "";
            }
            else if(StringUtils.isNullOrEmpty(lastName) || StringUtils.isEmptyOrWhitespaceOnly(lastName)) {
                lastName_message = "Enter your last name.";
                errorFound = true;
                lastName = "";
            }
            else if(StringUtils.isNullOrEmpty(password) || StringUtils.isEmptyOrWhitespaceOnly(password)) {
                password_message = "Enter a password.";
                errorFound = true;
            }
            else if(StringUtils.isNullOrEmpty(confirmPassword) || StringUtils.isEmptyOrWhitespaceOnly(confirmPassword)) {
                password_message = "Confirm your password.";
                errorFound = true;
            }
            else if(!password.equals(confirmPassword)) {
                password_message = "Passwords don't match.";
                errorFound = true;
            }

            if(errorFound) {
                request.setAttribute("username", username);
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("username_message", username_message);
                request.setAttribute("firstName_message", firstName_message);
                request.setAttribute("lastName_message", lastName_message);
                request.setAttribute("password_message", password_message);
                request.getRequestDispatcher("pages/CreateAccountPage.jsp").forward(request, response);
            }
            else {
                UserAccount newUser = new UserAccount();
                newUser.setUsername(username);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setPassword(password);
                insertUser(newUser);
                
                request.setAttribute("username", "");
                request.setAttribute("firstName", "");
                request.setAttribute("lastName", "");
                request.setAttribute("username_message", "");
                request.setAttribute("firstName_message", "");
                request.setAttribute("lastName_message", "");
                request.setAttribute("password_message", "");
                request.setAttribute("message", "Account created! Login to continue.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        catch(Exception ex) {
            throw new ServletException(ex);
        }
    }
    
    private void insertUser(UserAccount user) throws Exception {
        
        try {
            
            try(CreateAccountManager manager = new CreateAccountManager()) {
                manager.insertUser(user);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
