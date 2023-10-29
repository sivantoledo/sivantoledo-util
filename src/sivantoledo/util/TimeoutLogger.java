package sivantoledo.util;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimeoutLogger {
  
  private final static Logger log = LogManager.getLogger();
  
  //private String name;
  //private double period;
  
  private AtomicBoolean flag = new AtomicBoolean();

  private Thread t;
  
  public TimeoutLogger(final String name,final double periodSeconds) {
    //this.name = name;
    flag.set(true);

    t = new Thread(new Runnable() {
      @Override
      public void run() {
        log.printf(Level.INFO, "TimeoutLogger %s starting", name);
        while (flag.get()) {
          try {
            Thread.sleep((long) (periodSeconds * 1000));
            if (flag.get()) log.printf(Level.INFO, "TimeoutLogger %s timed out (%.2s expiration)", name,periodSeconds);
            else break;
          } catch (InterruptedException ie) {
            log.printf(Level.INFO, "TimeoutLogger %s interrupted, trying again", name);
            continue; // try again
          }
        }
        log.printf(Level.INFO, "TimeoutLogger %s terminating", name);
      }      
    });
    t.setDaemon(true);
    t.start();
  }
  
  public void pacify() { 
    flag.set(false); 
    t.interrupt(); 
  }
}
