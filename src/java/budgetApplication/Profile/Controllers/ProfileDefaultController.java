

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

@WebServlet(urlPatterns = {"/Profile"})
public class ProfileDefaultController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
             HttpSession currentSession;
             User user;
             List<Budget> budgets;
 
             currentSession = request.getSession();
             if(currentSession.getAttribute(USER) != null) {
                 // get data from session
                 user = (User) currentSession.getAttribute(USER);
                 
                 // get data from URL (a.k.a GET parameters)
                 // for example:
                 // String blah = request.getParamter("parameterName");
                 int userId= user.getId();
                 user = getUserProfileById(userId);
                 
                 // setup your data (outgoing)
                 request.setAttribute(USER, user);
                 
                 // navigate to the page
                 request.getRequestDispatcher("/pages/ProfilePage.jsp").forward(request, response);
             }
             else {
                 currentSession.invalidate();    // this is the only thing you need to do (I've already written the sign out logic)
             }
         }
         catch (Exception ex) {
             throw new ServletException(ex);
         }      
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        
    }
    
    private User getUserProfileById(int userId) throws Exception {
        try (ProfileManager manager = new ProfileManager()) {
            return manager.getUserProfileById(userId);
        }
    }
}
