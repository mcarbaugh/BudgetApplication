
package budgetApplication.dataAccess;

import budgetApplication.dataContracts.UserProfileInformation;

public class ProfileDataAccess implements AutoCloseable {   

    public UserProfileInformation getUserInformationById(int id) {
        
        return new UserProfileInformation();
    }
    
    public void updateUserInformation(UserProfileInformation user) {
        
    }
    
    public void deleteUserInformationById(int id) {
        
    }
    
    @Override
    public void close() throws Exception {
        
    }
}
