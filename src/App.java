import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import foods.Food;
import inventories.Stock;
import users.*;

public class App {
  public static ArrayList<User> users = new ArrayList<>();

  public static void main(String[] args) {
    Client lucas = new Client();
    lucas.name = "Lucas";
    lucas.email = "lucasprimon@gmail.com";
    lucas.setPassword("lucas");
    lucas.stock = new Stock(lucas);

    Client.database.add(lucas);

    Food arroz = new Food();
    arroz.name = "Arroz";
    arroz.description = "Saco de arroz branco integral de 1kg";
    arroz.price = "21,79";
    arroz.quantity = 21;
    arroz.validaty = new Date();

    Food feijao = new Food();
    feijao.name = "feijão";
    feijao.description = "Saco de feijao de 1kg";
    feijao.price = "18,55";
    feijao.quantity = 14;
    feijao.validaty = new Date();
    feijao.validaty.setDate(22);


    lucas.stock.food.add(arroz);
    lucas.stock.food.add(feijao);

    Inspector init = new Inspector();
    init.register("Gabriel", "gabriell@gmail.com", "Anvisa", "leiteg");

    Scanner scan = new Scanner(System.in);

    int response;
    
    do {
      users.clear();
      getUsers();

      System.out.println("-- Bem vindo ao gerenciador de estoque! --");
      System.out.println("1- Cadastrar");
      System.out.println("2- Logar");
      System.out.println("3- Sair");
      System.out.println("-- O que deseja fazer? ");

      response = scan.nextInt();
      scan.nextLine();

      if(response == 1) {
        Client client = new Client();
        client = client.register();

        menu(client);
      }

      if(response == 2) {
        System.out.println("----------- Login -----------");        
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

    ArrayList<Admin> admins = Admin.database;
    for(Admin admin : admins) {
      User user = (User) admin;
      if(user != adm) {
        users.add(user);
      }
    }
    
    ArrayList<Client> clients = Client.database;
    for(Client client : clients) {
      User user = (User) client;
      users.add(user);
    }

    ArrayList<Inspector> inspectors = Inspector.database;
    for(Inspector inspector : inspectors) {
      User user = (User) inspector;
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
        if(user instanceof Inspector) {
          Inspector logged = (Inspector) user;
          menu(logged);
        }
      }
    }
  }

  public static void menu(Client user) {
    Scanner scan = new Scanner(System.in);

    int res;

    System.out.println(" --- Bem vindo " + user.name + "! ---");

    do {
      System.out.println("---------------------------------------------------------------");
      System.out.println("1- Ver Estoque");
      System.out.println("2- Adicionar produto ");
      System.out.println("3- Remover produto");
      System.out.println("4- Ver perfil");
      System.out.println("5- Logout");
      System.out.println("---------------------------------------------------------------");

      res = scan.nextInt();

      if(res == 1) {
        Stock.getProducts(user);
      }
      if(res == 2) {
        Stock.addProducts(user);
      }
      if(res == 3) {
        Stock.removeProduct(user);
      }
      if(res == 4) {
        user.profile();
      }
    } while(res != 5);
  }

  public static void menu(Admin user) {
    Scanner scan = new Scanner(System.in);

    int res;

    System.out.println("Bem vindo " + user.name + "!");

    do {
      System.out.println("---------------------------------------------------------------");
      System.out.println("1- Listar");
      System.out.println("2- Cadastrar");
      System.out.println("3- Remover");
      System.out.println("4- Logout");
      System.out.println("---------------------------------------------------------------");
      res = scan.nextInt();

      if(res == 1) {
        Scanner a = new Scanner(System.in);

        int response;

        System.out.println("---------------------------------------------------------------");
        System.out.println("1- Listar usuários");
        System.out.println("2- Listar estoques");
        System.out.println("---------------------------------------------------------------");
        response = a.nextInt();

        if(response == 1) {
          user.getUsers();
        }
        if(response == 2) {
          Stock.getStocks();
        }
      }
      if(res == 2) {
        Scanner a = new Scanner(System.in);

        int response;

        System.out.println("---------------------------------------------------------------");
        System.out.println("1- Cadastrar inspetor");
        System.out.println("2- Cadastrar admin");
        System.out.println("---------------------------------------------------------------");

        response = a.nextInt();

        if(response == 1) {
          user.createInspector();
        }
        if(response == 2) {
          user.createAdmin();
        }
      }
      if(res == 3) {
        user.remove();
      }
    } while(res != 4);
  }

  public static void menu(Inspector user) {
    Scanner scan = new Scanner(System.in);

    int res;

    System.out.println(" --- Bem vindo " + user.name + "! ---");

    do {
      System.out.println("---------------------------------------------------------------");
      System.out.println("1- Listar clientes");
      System.out.println("2- Ver estoque");
      System.out.println("3- Logout");
      System.out.println("---------------------------------------------------------------");
      res = scan.nextInt();

      if(res == 1) {
        user.getClients();
      }
      if(res == 2) {
        user.viewStock();
      }

    } while(res != 3);
  }
}
