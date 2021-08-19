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
        System.out.println("------------ Cadastro ------------");
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

    public void profile() {
        Scanner scanner = new Scanner(System.in);
        int response;

        do {
            System.out.println("-------------------------------");
            System.out.println("Nome: " + name);
            System.out.println("Email: " + email);
            System.out.println("Estoque: " + stock);
            System.out.println("----");
            System.out.println("Você deseja fazer o que? ");
            System.out.println("1- Editar dados");
            System.out.println("2- Editar senha");
            System.out.println("3- Excluir perfil");
            System.out.println("4- Voltar ");
            System.out.println("-------------------------------");

            response = scanner.nextInt();

            if(response == 1) {
                edit();
            }
            if(response == 2) {
                String pass, password = getPassword();

                System.out.println("Digite sua senha atual: ");
                pass = scanner.nextLine();

                if(pass.equals(password)) {
                    System.out.println("Digite sua nova senha: ");
                    pass = scanner.nextLine();
                    setPassword(pass);
                    System.out.println("Senha alterada com sucesso!");
                } else {
                    System.out.println("Senha inválida!");
                }
            }
            if(response == 3) {
                System.out.println("Tem certeza? ");
                System.out.println("1- Sim 2- Não");
                int nr = scanner.nextInt();
                if(nr == 1) {
                    database.remove(this);
                }
            }
        } while(response != 4);
    }

    public Client edit() {
        Scanner scanner = new Scanner(System.in);
        String newName, newEmail, pass;

        int count = 0;
        System.out.println("Digite seu novo nome: ");
        newName = scanner.nextLine();
        System.out.println("Digite seu novo email: ");
        newEmail = scanner.nextLine();

        do {
            System.out.println("Para confirmar as alterações digite sua senha: ");
            pass = scanner.nextLine();

            String password = getPassword();
            if(!pass.equals(password)) {
                count++;
            } else {
                break;
            }
        } while(count != 3);

        if(count < 3) {
            name = newName;
            email = newEmail;
            System.out.println("Dados alterados com sucesso!");
        } else {
            System.out.println("Senha incorreta, portanto os dados não foram alterados!");
        }

        return this;
    }
}
