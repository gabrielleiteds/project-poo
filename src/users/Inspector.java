package users;

import java.util.ArrayList;
import java.util.Scanner;

import inventories.Stock;

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

    public void getClients() {
        ArrayList<Client> clients = Client.database;

        for(Client client : clients) {
            System.out.println();
            System.out.println("Nome: " + client.name);
            System.out.println("Email: " + client.email);
            System.out.println("Estoque: " + client.stock);
            System.out.println("Quantidade de produtos: " + client.stock.food.size());
        }
    }

    public void viewStock() {
        Scanner scanner = new Scanner(System.in);
        getClients();
        String temail;

        System.out.println("Digite o email do dono do estoque que deseja ver: ");
        temail = scanner.nextLine();

        ArrayList<Client> clients = Client.database;

        for(Client client : clients) {
            if(temail.equals(client.email)) {
                Stock.getProducts(client);
            }
        }
    }
}
