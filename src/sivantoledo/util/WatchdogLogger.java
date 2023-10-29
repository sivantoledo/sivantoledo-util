package sivantoledo.util;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WatchdogLogger extends Watchdog {
  
  private final static Logger log = LogManager.getLogger();
  
  public WatchdogLogger(final String name,final double periodSeconds, boolean enabled) {
    super(name, periodSeconds, enabled, new ExpirationAction() {
      @Override public void expired() {
        log.printf(Level.INFO, "Watchdog %s expired", name);
      }
    });
    log.printf(Level.INFO, "Watchdog %s started", name);
  }

  /*
  //private String name;
  //private double period;
  
  private AtomicBoolean flag  ;
  private AtomicBoolean active; 
  private Thread t;
  
  public WatchdogLogger(final String name,final double periodSeconds, boolean enabled) {
    active = new AtomicBoolean(enabled);
    flag   = new AtomicBoolean(true);
    t = new Thread(new Runnable() {
      @Override
      public void run() {
        log.printf(Level.INFO, "Watchdog %s starting", name);
        while (true) {
          if (active.get()) flag.set(true);
          try {
            Thread.sleep((long) (periodSeconds * 1000));
          } catch (InterruptedException ie) {
            continue; // try again
          }
          if (active.get() && flag.get()) log.printf(Level.INFO, "Watchdog %s expired", name);
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
  */
}
