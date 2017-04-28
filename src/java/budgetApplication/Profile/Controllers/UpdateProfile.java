
package budgetApplication.Profile.Controllers;

import static budgetApplication.baseClasses.ConstantFields.USER;
import budgetApplication.Profile.BusinessLogic.ProfileManager;
 import budgetApplication.dataContracts.*;
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
 
            currentSession = request.getSession();
            user = (User) currentSession.getAttribute(USER);
            //update profile
            int userId = user.getId();
            
            // get parameters
            String firstname = request.getParameter("firstName");
            String lastname = request.getParameter("lastName");
            String username = request.getParameter("username"); 
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            user = new User();
            user.setId(userId);
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setUsername(username);
            user.setEmail(email);
            user.setPhoneNumber(phone);
            
            // update user info
            updateProfile(user);
            
            // update for the UI
            request.setAttribute(USER, user);
            
            // update session information
            currentSession.removeAttribute("user");
            currentSession.setAttribute("user", user);
            
            // redirect back to profile page
            response.sendRedirect(request.getContextPath() + "/Profile");
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
