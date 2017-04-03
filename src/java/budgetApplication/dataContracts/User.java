
package budgetApplication.dataContracts;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    
    public User() {
        id = 0;
        userName = null;
        firstName = null;
        lastName = null;
        email = null;
        phoneNumber = null;
    }
    
    public User(int myId, String myUsername, String myFirstName, String myLastName, String myEmail, String myPhoneNumber) {
        id = myId;
        userName = myUsername;
        firstName = myFirstName;
        lastName = myLastName;
        email = myEmail;
        phoneNumber = myPhoneNumber;
    }
    
    public int getId() {
        return id;
    }
    
    public String getUserName() {
        return userName;
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
        userName = value;
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
