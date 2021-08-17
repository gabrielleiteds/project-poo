package inventories;

import java.util.ArrayList;
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
  
  public static void getProducts(Client owner) {
    UUID userId = owner.stock.id;
    Stock stockfind = new Stock();
    
    for(Stock stock : database) {
      if(stock.id == userId) stockfind = stock;
    }

    for (int i = 0; i < 2; i++) {
      // Food food = stockfind.food.get(i);
      System.out.println("sashahsahsah");
    }
  }
}
