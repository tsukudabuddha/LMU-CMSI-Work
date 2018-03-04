public class Clock {
    // Private
	  private int hours;
	  private int minutes;
    private double seconds;
    private double timeslice;
    private double totalSeconds;

	  // Constructor for Clock
	  public Clock ( int hours, int minutes, double seconds, double timeslice, double totalSeconds) {
		if ( hours < 0 || hours > 12 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59.9 || timeslice > 1800.0) {
			  throw new IllegalArgumentException();
		} else {
			  this.hours = hours;
			  this.minutes = minutes;
        this.seconds = seconds;
        this.timeslice = timeslice;
        this.totalSeconds = totalSeconds;
		}
	}
    // Adds timeslice to total time each iteration
    public void iterate() {
        this.totalSeconds = this.totalSeconds + this.timeslice;
    }

    // returns seconds
    public double getSeconds() {
       return this.totalSeconds;
    }

    // returns times in String 
    public String toString() {
        double hours = this.totalSeconds / 3600;
        double remainder = this.totalSeconds % 3600;
        double minutes = remainder / 60;
        double seconds = remainder % 60;
        String newString = (int) hours + ":" + (int) minutes + ":"  +  seconds;

        if(minutes < 10){
            newString = (int) hours + ":" + "0" + (int) minutes + ":"  +  seconds;
        } else {
            newString = (int) hours + ":" + (int) minutes + ":"  +  seconds;
        }
        return newString;

    }


}
