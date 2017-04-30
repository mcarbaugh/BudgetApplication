
package budgetApplication.dataContracts;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    
    public UserAccount() {
        id = 0;
        username = null;
        firstName = null;
        lastName = null;
    }
    
    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setId(int value) {
        this.id = value;
    }
    
    public void setUsername(String value) {
        this.username = value;
    }
    
    public void setFirstName(String value) {
        this.firstName = value;
    }
    
    public void setLastName(String value) {
        this.lastName = value;
    }
    
    public void setPassword(String value) {
        this.password = value;
    }
}
