package sivantoledo.util;

import java.util.Scanner;

public class ConsoleUserPasswordGetter extends UserPasswordGetter {
  
  @Override
  public boolean ask(LocalConfiguration config, String resourceLabel, String user, String password) {
    u = p = null;
    
    if (user==null || password==null) {
      System.out.printf("Collecting credentials for %s\n",resourceLabel);
      
      Scanner s = new Scanner(System.in);
      
      if (user==null && config!=null) user = config.getProperty(resourceLabel+"-user");
      if (user==null || user.equals("")) {
        System.out.printf("User: ");
        user = s.nextLine();
        if (config!=null) {
          config.setProperty(resourceLabel+"-user", user);
          config.store();
        }
      } else {
        System.out.printf("User: %s\n",user);        
      }
      if (password==null && config!=null) password = config.getProperty(resourceLabel+"-password");
      if (password==null || password.equals("")) {
        System.out.printf("password: ");
        password = s.nextLine();
        if (config!=null) {
          config.setProperty(resourceLabel+"-password", password);
          config.store();
        }
      }
      // don't close because this also closes System.in // s.close(); 
    }
    
    u = user;
    p = password;
    return true;
  }
}