
package budgetApplication.Profile.Controllers;

import static budgetApplication.baseClasses.ConstantFields.USER;
import budgetApplication.Profile.BusinessLogic.ProfileManager;
import budgetApplication.dataContracts.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/DeleteProfile"})
public class DeleteProfile extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            int userId = Integer.parseInt(request.getParameter("userId"));
            deleteProfileByUserId(userId);
            response.sendRedirect(request.getContextPath() + "/Login");
        }
        catch (Exception ex){
            throw new ServletException(ex);
        }  
    }
    
    private void deleteProfileByUserId(int userId) throws Exception {
        try (ProfileManager manager = new ProfileManager()) {
                manager.deleteUserProfileById(userId);
        }
    }
    
}
