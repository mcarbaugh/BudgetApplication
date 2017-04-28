
package budgetApplication.Profile.BusinessLogic;
import budgetApplication.dataContracts.User;
import budgetApplication.Profile.DataAccess.ProfileDataAccess;


public class ProfileManager implements AutoCloseable {

    //read
    public User getUserProfileById(int userId) throws Exception{
        try(ProfileDataAccess profileDataAccess = new ProfileDataAccess()){
            return profileDataAccess.getUserProfileById(userId);
        }
        catch (Exception ex){
            throw ex;
        }
    } 
    //update
    public void updateProfile(User user) throws Exception{
        try(ProfileDataAccess profileDataAccess = new ProfileDataAccess()){
            profileDataAccess.updateUserProfile(user);
        }
        catch (Exception ex){
            throw ex;
        }
    }
    
    //delete
    public void deleteUserProfileById(int userId) throws Exception{
        try(ProfileDataAccess profileDataAccess = new ProfileDataAccess()){
            profileDataAccess.deleteUserProfileById(userId);
        }
        catch (Exception ex){
            throw ex;
        }
    }
    
    @Override
    public void close() throws Exception {
        
    }
}
