import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public boolean matches(Contact contact){
        if(this.getPhoneNumber().equals(contact.getPhoneNumber())) return true;
        if(this.getName().equals(contact.getName())) return true;
        if(this.getEmailAddress().equals(contact.getEmailAddress())) return true;
        return false;
    }
    public void copy(Contact contact){
        this.setName(contact.getName());
        this.setPhoneNumber(contact.getPhoneNumber());
        this.setEmailAddress(contact.getEmailAddress());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
