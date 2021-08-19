package users;

import java.util.ArrayList;

public class Inspector extends User {
    public static ArrayList<Inspector> database = new ArrayList<>();

    public String institution;


    public Inspector register(String name, String email, String instituition, String password) {
        this.setPassword(password);
        this.email = email;
        this.name = name;
        this.institution = instituition;

        database.add(this); 

        return this;
    }
}
