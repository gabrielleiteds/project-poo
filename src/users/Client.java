package users;

import java.util.ArrayList;

import java.util.Scanner;

import inventories.Stock;

public class Client extends User {
    public static ArrayList<Client> database = new ArrayList<>();
    public Stock stock;

    public Client register() {
        Scanner scanner = new Scanner(System.in);

        Client user = new Client();

        System.out.println("Digite seu nome: ");
        user.name = scanner.nextLine();
        System.out.println("Digite seu email: ");
        user.email = scanner.nextLine();
        System.out.println("Digite sua senha: ");
        String password;
        password = scanner.nextLine();
        
        user.setPassword(password);
        user.stock = new Stock(user); 
        
        database.add(user);

        return user;
    }

    public Client getClient(String email) {
        Client user = new Client();
        
        for(Client client : database) {
            if(client.email == email) {
                user = client;
            }
        }

        return user;
    }
}
