import java.util.ArrayList;
import java.util.Scanner;

import inventories.Stock;
import users.*;

public class App {
  public static ArrayList<User> users = new ArrayList<>();

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.println("Bem vindo ao gerenciador de estoque!");
    int response;
    
    do {
      users.clear();
      getUsers();

      System.out.println("1- Cadastrar 2- Logar 3- Sair");
      response = scan.nextInt();

      if(response == 1) {
        int res;

        Client client = new Client();
        client = client.register();
  
        System.out.println("Bem vindo " + client.name + "!");

        do {
          System.out.println("1- Ver Estoque 2- Adicionar produto 3-Remover produto 4- Sair");
          res = scan.nextInt();
    
          if(res == 1) {
            Stock.getProducts(client);
          }
          if(res == 2) {
            Stock.addProducts(client);
          }
        } while(res != 4);
      }

      if(response == 2) {
        String email, password;
        
        System.out.println("Digite seu email: ");
        email = scan.nextLine();
        System.out.println("Digite sua senha: ");
        password = scan.nextLine();

        authenticate(email, password);
      }

    } while(response != 3);
    
    scan.close();
  }

  public static void getUsers() {
    Admin adm = Admin.init();
    users.add((User) adm);
    
    ArrayList<Client> clients = Client.database;
    for(Client client : clients) {
      users.add((User) client);
    }
  }

  public static void authenticate(String email, String password) {
    for(User user : users) {
      if(user.email == email && user.getPassword() == password) {
        if(user instanceof Client) {
          menu((Client) user);
        }
        if(user instanceof Admin) {
          menu((Admin) user);
        }
      }
    }
  }

  public static void menu(Client user) {
    Scanner scan = new Scanner(System.in);

    int res;

    do {
      System.out.println("1- Ver Estoque 2- Adicionar produto 3-Remover produto 4- Sair");
      res = scan.nextInt();

      if(res == 1) {
        Stock.getProducts(user);
      }
      if(res == 2) {
        Stock.addProducts(user);
      }
    } while(res != 4);
  }

  public static void menu(Admin user) {

  }
}
