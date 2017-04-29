
package budgetApplication.Profile.Controllers;

import static budgetApplication.baseClasses.ConstantFields.USER;
import budgetApplication.Profile.BusinessLogic.ProfileManager;
 import budgetApplication.dataContracts.*;
import com.mysql.jdbc.StringUtils;
 import java.io.IOException;
 import javax.servlet.ServletException;
 import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/UpdateProfile"})
public class UpdateProfile extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            HttpSession currentSession;
            User user;
            int userId;
            String firstName = "";
            String lastName = "";
            String username = "";
            String email = "";
            String phone = "";
            Boolean errorFound = false;
 
            currentSession = request.getSession();
            user = (User) currentSession.getAttribute(USER);
            
            //update profile
            userId = user.getId();

            if(request.getParameterMap().containsKey("firstName")) {
                firstName = request.getParameter("firstName");
            }
            
            if(request.getParameterMap().containsKey("lastName")) {
                lastName = request.getParameter("lastName");
            }
            
            if(request.getParameterMap().containsKey("username")) {
                username = request.getParameter("username"); 
            }
            
            if(request.getParameterMap().containsKey("email")) {
                email = request.getParameter("email");
            }
            
            if(request.getParameterMap().containsKey("phone")) {
                phone = request.getParameter("phone");
            }
            
            if(StringUtils.isEmptyOrWhitespaceOnly(username)) {
                errorFound = true;
            }
            else if(StringUtils.isEmptyOrWhitespaceOnly(firstName)) {
                errorFound = true;
            }
            else if(StringUtils.isEmptyOrWhitespaceOnly(lastName)) {
                errorFound = true;
            }
            
            user = new User();
            user.setId(userId);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setEmail(email);
            user.setPhoneNumber(phone);
            
            if(errorFound) {
                // redirect without saving changes
                response.sendRedirect(request.getContextPath() + "/Profile");
            }
            else {
                updateProfile(user);
                currentSession.removeAttribute("user");
                currentSession.setAttribute("user", user);
                request.setAttribute(USER, user);
                response.sendRedirect(request.getContextPath() + "/Profile");
            }
        }
        catch (Exception ex){
            throw new ServletException(ex);
        }  
    }
    
    private void updateProfile(User user) throws Exception {
        try (ProfileManager manager = new ProfileManager()){
            manager.updateProfile(user);
        }
    }
    
}
