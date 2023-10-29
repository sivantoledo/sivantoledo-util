package sivantoledo.util;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Watchdog {
  
  private AtomicBoolean flag  ;
  private AtomicBoolean active; 
  private Thread t;
  
  public static interface ExpirationAction { public void expired(); }
  
  public Watchdog(final String name,final double periodSeconds, boolean enabled, final ExpirationAction action) {
    active = new AtomicBoolean(enabled);
    flag   = new AtomicBoolean(true);
    t = new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
          if (active.get()) flag.set(true);
          try {
            Thread.sleep((long) (periodSeconds * 1000));
          } catch (InterruptedException ie) {
            continue; // try again
          }
          if (active.get() && flag.get()) action.expired();
        }
      }      
    });
    t.setDaemon(true);
    t.start();
  }
  
  public void disable() {
    active.set(false);
    flag.set(false);
    t.interrupt();
  }

  public void enable() {
    flag.set(true);
    active.set(true);
    t.interrupt();
  }

  public void pacify() {
    flag.set(false);
    t.interrupt();
  }
}
