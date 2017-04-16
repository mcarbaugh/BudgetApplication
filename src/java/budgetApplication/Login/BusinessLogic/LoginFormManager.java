
package budgetApplication.businessLogic;

import budgetApplication.Login.DataAccess.LoginFormDataAccess;
import budgetApplication.dataAccess.*;
import budgetApplication.dataContracts.*;

public class LoginFormManager implements AutoCloseable {
    
    public User getUserByUsernameAndPassword(String username, String password) throws Exception {
        
        try (LoginFormDataAccess dataAccess = new LoginFormDataAccess()) {
            return dataAccess.getUserByUsernameAndPassword(username, password);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void close() throws Exception {
        try {
            
        } 
        catch(Exception ex) {
            throw ex;
        }
    }
}
