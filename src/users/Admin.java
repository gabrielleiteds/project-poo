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
}
