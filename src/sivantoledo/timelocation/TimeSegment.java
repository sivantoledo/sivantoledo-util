package sivantoledo.timelocation;

public interface TimeSegment {
  public double startTime(); // if this is a point event, start==end
  public double endTime();
}
