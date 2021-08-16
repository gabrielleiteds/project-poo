package users;

public class User {
   public String name;
   public String email;
   private String password;

   public String setPassword(String password) {
       return this.password = password;
   }
}