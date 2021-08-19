package users;

import java.util.ArrayList;
import java.util.Scanner;

import inventories.Stock;

public class Admin extends User {
    public static ArrayList<Admin> database = new ArrayList<>();
    
    public static Admin init() {
        Admin adm = new Admin();
        adm.name = "Admin 00";
        adm.email = "admin@admin.com";
        adm.setPassword("admin");

        database.add(adm);

        return adm;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> db = new ArrayList<>();

        ArrayList<Client> clients = Client.database;
        ArrayList<Inspector> inspectors = Inspector.database;

        for (Admin adm : database) {
            User user = (User) adm;
            db.add(user);
        }

        for (Client client : clients) {
            User user = (User) client;
            db.add(user);
        }

        for (Inspector inspector : inspectors) {
            User user = (User) inspector;
            db.add(user);
        }

        for (User user : db) {
            if (user != this) {
                System.out.println();
                System.out.println("------- " + user + " -------");
                System.out.println("Nome: " + user.name);
                System.out.println("Email: " + user.email);
                System.out.println("----------------------------");
            }
        }

        return db;
    }

    public void remove() {
        Scanner scanner = new Scanner(System.in);

        int response;

        System.out.println("---------------------------------------------------------------");
        System.out.println("1- Remover usuário");
        System.out.println("2- Remover estoque");
        System.out.println("---------------------------------------------------------------");
        response = scanner.nextInt();

        if (response == 1) {
            removeUser();
        }
        if (response == 2) {
            Stock.removeStock();
        }
    }

    public void removeUser() {
        Scanner scanner = new Scanner(System.in);

        ArrayList<User> users = getUsers();

        System.out.println("---------------------------------------------------------");
        System.out.println("Digite o email do usuário que deseja remover do sistema: ");
        String rmEmail = scanner.nextLine();

        for (User user : users) {
            if (user.email.equals(rmEmail)) {
                if (user instanceof Client) {
                    Client.database.remove((Client) user);
                }
                if (user instanceof Admin) {
                    Admin.database.remove((Admin) user);
                }
                if (user instanceof Inspector) {
                    Inspector.database.remove((Inspector) user);
                }
                System.out.println(user + " foi removido com sucesso!");
                break;
            } else {
                System.out.println("Este usuário não existe!");
            }
        }
    }

    public Inspector createInspector() {
        Inspector user = new Inspector();
        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o nome do inspetor: ");
        String name = scan.nextLine();
        System.out.println("Digite o email do inspetor: ");
        String email = scan.nextLine();
        System.out.println("Digite o nome da instituição do inspetor: ");
        String instituition = scan.nextLine();
        System.out.println("Digite uma senha prévia para o inspetor: ");
        String password = scan.nextLine();

        user = user.register(name, email, instituition, password);

        return user;
    }
}
