package users;

import java.util.ArrayList;
import java.util.Scanner;

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

    public ArrayList<User> getUsers(Admin a) {
        ArrayList<User> db = new ArrayList<>();

        ArrayList<Client> clients = Client.database;
        ArrayList<Inspector> inspectors = Inspector.database;

        for(Admin adm : database) {
            User user = (User) adm;
            db.add(user);
        }

        for(Client client : clients) {
            User user = (User) client;
            db.add(user);
        }

        for(Inspector inspector : inspectors) {
            User user = (User) inspector;
            db.add(user);
        }

        for(User user : db) {
            if(user != a) {
                System.out.println();
                System.out.println("------- " + user + " -------");
                System.out.println("Nome: " + user.name);
                System.out.println("Email: " + user.email);
                System.out.println("----------------------------");
            }
        }

        return db;
    }

    public void removeUser() {
        Scanner scanner = new Scanner(System.in);

        ArrayList<User> users = getUsers(this);

        System.out.println("---------------------------------------------------------");
        System.out.println("Digite o email do usuário que deseja remover do sistema: ");
        String rmEmail = scanner.nextLine();
        
        for(User user : users) {
            if(user.email.equals(rmEmail)) {
                if(user instanceof Client) {
                    Client.database.remove((Client) user);
                }
                if(user instanceof Admin) {
                    Admin.database.remove((Admin) user);
                }
                if(user instanceof Inspector) {
                    Inspector.database.remove((Inspector) user);
                }
                System.out.println(user + " foi removido com sucesso!");
                break;
            } else {
                System.out.println("Este usuário não existe!");
            }
        }
    }
}
