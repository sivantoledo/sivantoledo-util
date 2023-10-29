package sivantoledo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class LocalConfiguration extends Properties {
  
  private String name;
  private String directory;
  public LocalConfiguration(String name, String directory) {
    this.name   = name;
    this.directory = directory; 
  }
  
  private String fullname() {
    if (directory==null) return System.getProperty("user.home")+File.separator+name;
    else                 return System.getProperty("user.home")+File.separator+directory+File.separator+name; 
  }
  
  public void load() {
    String name = fullname();
    try {
      load(new java.io.FileReader(name));
      //put("y","12354");
      System.out.printf("Read configuration file %s, size=%d\n", name, size());
    } catch (IOException e) {
      System.out.printf("configuration file %s not found or could not be read (%s)\n", name, e.getMessage());
      
      // check if the directory exists and if not, try to create it
      if (directory!=null) {
        File d = new File( System.getProperty("user.home")+File.separator+directory );
        if (!d.exists()) {
          boolean created = d.mkdir();
          if (!created) {
            System.out.printf("could not create directory %s\n", d.toString());
            return;
          }
        }
      }
      
      // create file if possible
      try {
        FileOutputStream fos = new FileOutputStream(name);
        System.out.printf("Created new configuration file %s\n", name);
        fos.close();
      } catch (IOException e1) {
        System.out.printf("configuration file %s could not be created (%s)\n", name, e1.getMessage());
        return;
      }
    }    
  }
  
  public void store() {
    String name = fullname();
    try {
      this.store(new FileWriter(name), "automatic");
    } catch (IOException e) {
      System.out.printf("configuration file %s could not be stored (%s)\n", name, e.getMessage());
    }
  }
  
  // helper classes for user, password
  public String user(String resource) {
    return getProperty(resource+"-user");
  }
  public String password(String resource) {
    return getProperty(resource+"-password");
  }

  public static void main(String[] args) {
    LocalConfiguration c = new LocalConfiguration("config.txt",".atlas");
    c.load();
    System.out.printf("Sivan ==> %s\n", c.getProperty("Sivan"));
    //c.setProperty("Sivan", "Toledo");    
    c.store();
  }
}
