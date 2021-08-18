package users;

import java.util.ArrayList;

public class Admin extends User{
    public static ArrayList<Admin> database = new ArrayList<>();

    public static Admin init() {
        Admin adm = new Admin();
        adm.name = "Admin 00";
        adm.email = "admin@admin.com";
        adm.setPassword("admin");

        database.add(adm);

        return adm;
    }

    public void getUsers(Admin a) {
        ArrayList<User> db = new ArrayList<>();

        ArrayList<Client> clients = Client.database;
        for(Admin adm : database) {
            User user = (User) adm;
            db.add(user);
        }
        for(Client client : clients) {
            User user = (User) client;
            db.add(user);
        }

        for(User user : db) {
            if(user != a) {
                System.out.println();
                System.out.println("------- " + user + " -------");
                System.out.println("Nome: " + user.name);
                System.out.println("Email: " + user.email);
            }
        }
    }
}
