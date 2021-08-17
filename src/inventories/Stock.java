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
  public Stock () {}
  
  public static void addProducts(Client owner) {
    Scanner input = new Scanner(System.in);
    Food foodCreate = new Food();

    System.out.println("Digite o nome do produto: ");
    foodCreate.name = input.nextLine();
    System.out.println("Digite uma descrição para o produto: ");
    foodCreate.description = input.nextLine();
    System.out.println("Defina o preço do produto: ");
    foodCreate.price = input.nextLine();

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
      System.out.println("Nome: " + foodFind.name + "           preço: " +  foodFind.price);
      System.out.println("Descrição: " + foodFind.description);
    }
  }
}
