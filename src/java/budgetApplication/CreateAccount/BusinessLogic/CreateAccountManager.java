
package budgetApplication.CreateAccount.BusinessLogic;

import budgetApplication.CreateAccount.DataAccess.CreateAccountDataAccess;
import budgetApplication.dataContracts.UserAccount;

public class CreateAccountManager implements AutoCloseable {

    public void insertUser(UserAccount user) throws Exception {
        
        try {
            
            try (CreateAccountDataAccess dataAccess = new CreateAccountDataAccess()) {
                dataAccess.insertUser(user);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public void close() throws Exception {

    }
}
