import java.io.Serializable;
import java.util.Scanner;

public class Address implements Serializable {
    public String fullAddress;
    public static Address addMenu(){
        Address address = new Address();
        Scanner scanner = new Scanner(System.in);
        String inp;
        System.out.println("Enter fullAddress");
        inp = scanner.nextLine();
        if(inp.equals("back")){
            return null;
        }
        address.fullAddress = inp;
        return address;
    }
    public void copy(Address address){
        this.fullAddress = address.fullAddress;
    }
}
