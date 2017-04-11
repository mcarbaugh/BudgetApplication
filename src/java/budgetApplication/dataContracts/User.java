
package budgetApplication.dataContracts;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    
    public User() {
        id = 0;
        username = null;
        firstName = null;
        lastName = null;
        email = null;
        phoneNumber = null;
    }
    
    public User(int myId, String myUsername, String myFirstName, String myLastName, String myEmail, String myPhoneNumber) {
        id = myId;
        username = myUsername;
        firstName = myFirstName;
        lastName = myLastName;
        email = myEmail;
        phoneNumber = myPhoneNumber;
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
    
    public String getEmail() {
        return email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setId(int value) {
        id = value;
    }
    
    public void setUsername(String value) {
        username = value;
    }
    
    public void setFirstName(String value) {
        firstName = value;
    }
    
    public void setLastName(String value) {
        lastName = value;
    }
    
    public void setPhoneNumber(String value) {
        phoneNumber = value;
    }
    
    public void setEmail(String value) {
        email = value;
    }
}
