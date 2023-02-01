import java.io.Serializable;
import java.lang.constant.Constable;
import java.util.Scanner;

public class Contact implements Serializable {
    public String name;
    public String phoneNumber;
    public String emailAddress;

    public boolean matches(Contact contact){
        if(this.phoneNumber.equals(contact.phoneNumber)) return true;
        if(this.name.equals(contact.name)) return true;
        if(this.emailAddress.equals(contact.emailAddress)) return true;
        return false;
    }
    public static Contact addMenu(){
        Contact contact = new Contact();
        Scanner scanner = new Scanner(System.in);
        String inp;
        System.out.println("Enter Contact name");
        inp = scanner.nextLine();
        if(inp.equals("back")){
            return null;
        }
        contact.name = inp;
        System.out.println("Enter Contact phoneNumber");
        inp = scanner.nextLine();
        if(inp.equals("back")){
            return null;
        }
        contact.phoneNumber = inp;
        System.out.println("Enter Contact emailAddress");
        inp = scanner.nextLine();
        if(inp.equals("back")){
            return null;
        }
        contact.emailAddress = inp;
        return contact;
    }
    public void copy(Contact contact){
        this.name = contact.name;
        this.phoneNumber = contact.phoneNumber;
        this.emailAddress = contact.emailAddress;
    }
}
