package users;

public class User {
   public String name;
   public String email;
   private String password;
   
   User(String name, String email, String password) {
       this.name = name; 
       this.email = email;
       this.setPassword(password);
   }

   private String setPassword(String password) {
       return this.password = password;
   }
}