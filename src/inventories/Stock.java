package inventories;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import foods.Food;
import users.Client;

public class Stock {
  public UUID id;
  public static ArrayList<Stock> database = new ArrayList<>();
  
  public ArrayList<Food> food = new ArrayList<>();
  public Client owner = new Client(); 
  
  public Stock(Client owner) {
    this.id = UUID.randomUUID(); 
    this.owner = owner;

    database.add(this); 
  }
  public Stock() {}
  
  public static void addProducts(Client owner) {
    Scanner input = new Scanner(System.in);
    Food foodCreate = new Food();

    System.out.println("Digite o nome do produto: ");
    foodCreate.name = input.nextLine();
    System.out.println("Digite uma descrição para o produto: ");
    foodCreate.description = input.nextLine();
    System.out.println("Defina o preço do produto: ");
    foodCreate.price = input.nextLine();
    System.out.println("Defina a quantidade desse produto: ");
    foodCreate.quantity = input.nextInt();

    foodCreate.validaty = new Date();

    UUID userId = owner.stock.id;
    Stock stockfind = new Stock();
    
    for(Stock stock : database) {
      if(stock.id == userId) stockfind = stock;
    }

    stockfind.food.add(foodCreate);

    System.out.println("Produto adicionado!");
  }

  public static void getProducts(Client owner) {
    UUID userId = owner.stock.id;
    Stock stockfind = new Stock();
    
    for(Stock stock : database) {
      if(stock.id == userId) stockfind = stock;
    }

    for (int i = 0; i < stockfind.food.size(); i++) {
      Food foodFind = stockfind.food.get(i);
      System.out.println("----------------------------------------------------");
      System.out.println("-- " + foodFind.name);
      System.out.println("Descrição: \n" + foodFind.description);
      System.out.println("Quantidade: " + foodFind.quantity);
      System.out.println("Data de validade: " + foodFind.validaty);
      System.out.println("------------------------------------- " + foodFind.price);
    }
  }

  public static void removeProduct(Client owner) {
      Scanner scanner = new Scanner(System.in);

      UUID userId = owner.stock.id;
      Stock stockfind = new Stock();
      
      for(Stock stock : database) {
        if(stock.id == userId) stockfind = stock;
      }
      
      System.out.println("Qual produto deseja remover? ");
      String productName = scanner.nextLine();
      System.out.println("Qual a quantidade que deseja remover? ");
      int productQtd = scanner.nextInt();

      for(Food food : stockfind.food) {
        if(food.name.toLowerCase().equals(productName.toLowerCase())) {
          food.quantity -= productQtd;
          if(food.quantity <= 0) {
            stockfind.food.remove(food);
          }
          break;
        }
      }
  }

  public static void getStocks() {
    System.out.println("--------------------------------------");
    for(Stock stock : database) {
      System.out.println();
      System.out.println("--- " + stock);
      System.out.println("ID: " + stock.id);
      System.out.println("--- Propietário ---");
      System.out.println("Nome: " + stock.owner.name);
      System.out.println("Email: " + stock.owner.email);
    }
  }

  public static void removeStock() {
    Scanner scanner = new Scanner(System.in);

    getStocks();

    System.out.println("Qual estoque deseja excluir? (Digite o email do proprietário)");

    String res = scanner.nextLine();

    for(Stock inventory : database) {
      if(inventory.owner.email.equals(res)) {
        database.remove(inventory);
        System.out.println("Estoque removido, não sei o que pode acontecer!");
        break;
      } else {
        System.out.println("Estoque não encontrado");
      }
    }
  }
}
