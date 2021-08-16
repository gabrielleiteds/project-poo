import java.util.Scanner;

import users.Client;

public class App {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("Bem vindo ao gerenciador de estoque!");
    
    System.out.println("1- Cadastrar 2- Logar");
    int response;
    response = scan.nextInt();

    if(response == 1) {
      Client client = new Client();

      client = client.register();

      System.out.println("Bem vindo " + client.name + "!");

      System.out.println("1- Ver Estoque 2- Adicionar produto 3-Remover produto");
      response = scan.nextInt();
    }
    
    scan.close();
  }
}
