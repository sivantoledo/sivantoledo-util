package sivantoledo.util;

public class Formatter {
  public static String compactInteger(long n) {
    if (n < 1e4)  return String.format( "%4d", n);
    if (n < 1e7)  return String.format("%4dk", Math.round(n / 1e3));
    if (n < 1e10) return String.format("%4dM", Math.round(n / 1e6));
                  return String.format("%4dG", Math.round(n / 1e9));    
  }

}
