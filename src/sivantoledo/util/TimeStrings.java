package sivantoledo.util;

//import java.lang.Math;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.io.Serializable;

/**
 * Time Utilities
 * 
 * @author stoledo
 *
 */

public class TimeStrings {
    
  /* utility */
  public static double parseTime(String dateFormat, String s) {
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat,java.util.Locale.US);
    sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    double t = Double.NaN;
    try {
      t = ((double) sdf.parse(s).getTime()) / 1000.0;
    } catch (java.text.ParseException pe) {
      try {
        t = Double.parseDouble(s);
      } catch (NumberFormatException nfe) {
        System.out.printf("Cannot parse <%s> as a date in format <%s> or as a double\n",s,dateFormat);
        System.exit(1);
      }
    }
      
    return t;
  }
    
  public static String format(double time) {
    return format("yyyy-MMM-dd HH:mm z",time);
  }


  public static String formatSecs(double time) {
    return format("yyyy-MMM-dd HH:mm:ss z",time);
  }
  
  public static String formatSpan(double span) {
    if (span < 60) 
      return String.format("%.0fs", span);
    if (span < 60*60) {
      double minutes = Math.floor(span/60);
      double seconds = span - 60*minutes;
      if (seconds<0.5) return String.format("%.0fm"     , minutes);
      else             return String.format("%.0fm%.0fs", minutes,seconds);
    }
    if (span < 24*60*60) {
      double hours = Math.floor(span/(60*60));
      double minutes = span/60 - 60*hours;
      if (minutes<0.5) return String.format("%.0fh"     , hours);
      else             return String.format("%.0fh%.0fm", hours,minutes);
    }
    {
      double days = Math.floor(span/(24*60*60));
      double hours = span/(60*60) - 24*days;
      if (hours<0.5) return String.format("%.0fd"     , days);
      else           return String.format("%.0fd%.0fh", days, hours);
    }
  }
  
  public static String format(String dateFormat, double time) {
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat,java.util.Locale.US);
    sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    return sdf.format(new Date((long) time*1000));
  }
}
