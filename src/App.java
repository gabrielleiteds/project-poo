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
      scan.nextLine();
      if(response == 1) {
        int res;

        Client client = new Client();
        client = client.register();
  
        System.out.println("Bem vindo " + client.name + "!");

        do {
          System.out.println("1- Ver Estoque 2- Adicionar produto 3-Remover produto 4- Logout");
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
        System.out.println("Digite seu email: ");
        String email = scan.nextLine();
        System.out.println("Digite sua senha: ");
        String password = scan.nextLine();

        authenticate(email, password);
      }

    } while(response != 3);
    
    scan.close();
  }

  public static void getUsers() {
    User adm = (User) Admin.init();
    users.add(adm);
    
    ArrayList<Client> clients = Client.database;
    for(Client client : clients) {
      User user = (User) client;
      users.add(user);
    }
  }

  public static void authenticate(String email, String password) {
    for(User user : users) {
      String pass = user.getPassword();
      if(user.email.equals(email) && pass.equals(password)) {
        if(user instanceof Client) {
          Client logged = (Client) user;
          menu(logged);
        }
        if(user instanceof Admin) {
          Admin logged = (Admin) user;
          menu(logged);
        }
      }
    }
  }

  public static void menu(Client user) {
    Scanner scan = new Scanner(System.in);

    int res;

    System.out.println("Bem vindo " + user.name + "!");

    do {
      System.out.println("1- Ver Estoque 2- Adicionar produto 3-Remover produto 4- Logout");
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
    Scanner scan = new Scanner(System.in);

    int res;

    System.out.println("Bem vindo " + user.name + "!");

    do {
      System.out.println("1- Listar usuários 2- Cadastrar Inspetor 3- Listar estoques 4- Logout");
      res = scan.nextInt();

      if(res == 1) {
        user.getUsers(user);
      }
    } while(res != 4);
  }
}
