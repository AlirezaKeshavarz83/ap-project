import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Item implements Serializable {
    private static int lastItemId = 0;
    public static int nextItemId(){
        return ++lastItemId;
    }
    public Item(int itemId){
        this.itemId = itemId;
    }
    public static class SubItem implements Serializable{
        private Item item;
        private String title;
        private int price;

        private int soldCnt;
        SubItem(Item item) {
            this.item = item;
        }
        SubItem(Item item, String title, int price) {
            this.item = item;
            this.title = title;
            this.price = price;
        }
        public void setItem(Item item){
            this.item = item;
        }
        public Item getItem(){
            return this.item;
        }
        public void setPrice(int price){
            this.price = price;
        }
        public int getPrice(){
            return this.price;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public int getSoldCnt(){
            return this.soldCnt;
        }
        public void incSoldCnt(int x){
            this.soldCnt += x;
        }
        public static SubItem chooseSubItem(ArrayList<SubItem> items){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose the SubItem:");
            for(int i = 0; i < items.size(); i++){
                System.out.println("    " + (i + 1) + ": " + items.get(i).getTitle());
            }
            System.out.println("  0 : cancel");
            int index = scanner.nextInt() - 1;
            if(index == -1){
                return null;
            }
            while(!(0 <= index && index < items.size())){
                System.out.println("Index out of range! Please try again:");
                index = scanner.nextInt() - 1;
                if(index == -1){
                    return null;
                }
            }
            return items.get(index);
        }
        public static SubItem addMenu(Item item){
            SubItem subItem = new SubItem(item);
            Scanner scanner = new Scanner(System.in);
            String inp;
            System.out.println("Enter SubItem title:");
            inp = scanner.nextLine();
            if(inp.equals("back")){
                return null;
            }
            subItem.title = inp;
            System.out.println("Enter SubItem price:");
            inp = scanner.nextLine();
            if(inp.equals("back")){
                return null;
            }
            subItem.price = Integer.parseInt(inp);
            return subItem;
        }
    }
    private int itemId;
    private String title;
    private Shop shop;
    private ArrayList<SubItem> packages = new ArrayList<>();
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setPackages(ArrayList<SubItem> packages){
        this.packages = packages;
    }
    public void addPackage(SubItem subItem){
        packages.add(subItem);
    }
    public ArrayList<SubItem> getPackages(){
        return this.packages;
    }
    public void setShop(Shop shop){
        this.shop = shop;
    }
    public Shop getShop(){
        return this.shop;
    }
    public static Item addMenu(){
        Scanner scanner = new Scanner(System.in);
        String inp;
        var item = new Item(nextItemId());
        System.out.println("Enter Item title:");
        inp = scanner.nextLine();
        if(inp.equals("back")){
            return null;
        }
        item.setTitle(inp);
        System.out.println("Enter number of packages");
        inp = scanner.nextLine();
        if(inp.equals("back")){
            return null;
        }
        int n = Integer.parseInt(inp);
        for(int i = 0; i < n; i++){
            var subItem = SubItem.addMenu(item);
            if(subItem == null){
                return null;
            }
            item.addPackage(subItem);
        }
        return item;
    }
    public static Item chooseItem(ArrayList<Item> items){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the Item:");
        for(int i = 0; i < items.size(); i++){
            System.out.println("    " + (i + 1) + ": " + items.get(i).getTitle() + " " + items.get(i).itemId);
        }
        System.out.println("  0 : cancel");
        int index = scanner.nextInt() - 1;
        if(index == -1){
            return null;
        }
        while(!(0 <= index && index < items.size())){
            System.out.println("Index out of range! Please try again:");
            index = scanner.nextInt() - 1;
            if(index == -1){
                return null;
            }
        }
        return items.get(index);
    }
}
