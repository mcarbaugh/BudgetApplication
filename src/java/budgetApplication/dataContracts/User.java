package budgetApplication.dataContracts;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    
    public User() {
        id = 0;
        userName = null;
        firstName = null;
        lastName = null;
        phoneNumber = null;
        password = null;
    }
    
    public User(int myId, String myUsername, String myFirstName, String myLastName, String myPhoneNumber, String myPassword){
        id = myId;
        userName = myUsername;
        firstName = myFirstName;
        lastName = myLastName;
        phoneNumber = myPhoneNumber;
        password = myPassword;
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
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getPassword() {
        return password;
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
    
    public void setPassword(String value) {
        password = value;
    }
}
