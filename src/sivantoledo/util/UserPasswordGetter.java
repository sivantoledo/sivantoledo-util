package sivantoledo.util;

public abstract class UserPasswordGetter {
  public abstract boolean ask(LocalConfiguration config, String resourceLabel, String user, String password);

  protected String u, p;
  public String getUser()     { return u; }
  public String getPassword() { return p; }

  private static UserPasswordGetter registered = new ConsoleUserPasswordGetter();
  public static UserPasswordGetter get()                            { return registered; }
  public static void               register(UserPasswordGetter upg) { registered = upg;  }
}
