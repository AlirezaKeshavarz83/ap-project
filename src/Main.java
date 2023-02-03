public class Main {
    public static DataBase db;
    public static void main(String[] args) {
        String filename = "file.txt";

        db = new DataBase(filename);

        Shop shop = db.readData();
        db.start();

        Menu.shopMenu(shop);

        db.close();
        db.writeData();
    }
}