package sivantoledo.timelocation;

public class BasicTimeSegment implements TimeSegment {
  private final double start, end;
  
  public BasicTimeSegment(double start, double end) {
    this.start = start;
    this.end = end;
  }
  
  @Override
  public double startTime() { return start; }

  @Override
  public double endTime() { return end; }
}
