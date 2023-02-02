import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Address implements Serializable {

    private String name;
    private String fullAddress;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setFullAddress(String fullAddress){
        this.fullAddress = fullAddress;
    }
    public String getFullAddress(){
        return this.fullAddress;
    }
    public void copy(Address address){
        this.name = address.name;
        this.fullAddress = address.fullAddress;
    }
    public static Address addMenu(){
        Address address = new Address();
        Scanner scanner = new Scanner(System.in);
        String inp;
        System.out.println("Enter Address name");
        inp = scanner.nextLine();
        if(inp.equals("back")){
            return null;
        }
        address.setName(inp);
        System.out.println("Enter fullAddress");
        inp = scanner.nextLine();
        if(inp.equals("back")){
            return null;
        }
        address.setFullAddress(inp);
        return address;
    }
    public static Address chooseAddress(ArrayList<Address> addresses){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an address:");
        for(int i = 0; i < addresses.size(); i++){
            System.out.println("    " + (i + 1) + ": " + addresses.get(i).getName());
        }
        System.out.println("  0 : cancel");
        int index = scanner.nextInt() - 1;
        if(index == -1){
            return null;
        }
        while(!(0 <= index && index < addresses.size())){
            System.out.println("Index out of range! Please try again:");
            index = scanner.nextInt() - 1;
            if(index == -1){
                return null;
            }
        }
        return addresses.get(index);
    }
}
