import java.io.Serializable;
import java.util.ArrayList;

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
}
