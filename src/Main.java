/**
 * @author Keshi
 */
import BusinessDomain.Shop;
import Database.*;
import UserInterface.*;

public class Main {
    public static Database db;
    public static void main(String[] args) {
        String filename = "file.txt";

        db = new Database(filename);

        Shop shop = db.readData();
        db.start();

        UI.start();
        GUI.init();

        Menu.shopMenu(shop);

        db.close();
        db.writeData();
        GUI.close();
        System.exit(0);
    }
}