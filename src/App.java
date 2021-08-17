import java.util.Scanner;

import inventories.Stock;
import users.Client;

public class App {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.println("Bem vindo ao gerenciador de estoque!");
    int response;
    
    do {
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

    } while(response != 3);
    
    scan.close();
  }
}
