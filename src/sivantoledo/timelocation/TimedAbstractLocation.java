package sivantoledo.timelocation;

import sivantoledo.util.TimeStrings;

public class TimedAbstractLocation implements TimeStampedAbstractLocation {

  private double time;
  private double[] coordinates;
  
  public TimedAbstractLocation(double time, double ... coords) {
    this.time = time;
    this.coordinates = coords;
  }
  
  @Override
  public double[] coordinates() { return coordinates; }

  @Override
  public double time() { return time; }
  
  @Override public String toString() {
    return String.format("%s\n%.0f %.0f", 
        TimeStrings.formatSecs(time()),
        coordinates()[0],coordinates()[1]);
  }


}
