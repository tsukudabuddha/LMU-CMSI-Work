public class ClockSim {

	public static void main ( String[] args ) {
				// Initialize bulk of var
        double angle = 0.0;
        double suppAngle = 360.0;
        double totalSeconds = 0.0;
				double timeSlice = 0.0;
        String newString = "";

				if(args.length < 1 || args.length > 2){
					  throw new IllegalArgumentException("You must input one or two arguments");
				}
				try {
            Double.parseDouble(args[1]);
        } catch (ArrayIndexOutOfBoundsException ex){
            if (Double.parseDouble(args[0]) < 0) {
                throw new IllegalArgumentException("Input must be greater than zero.");
            }
						timeSlice = 60.0;
				}
        if(timeSlice != 60.0){
            if (Double.parseDouble(args[0]) < 0 || Double.parseDouble(args[1]) < 0) {
                throw new IllegalArgumentException("Input must be greater than zero.");
            } else {
              timeSlice = Double.parseDouble(args[1]);
            }
        }

        double inputAngle =  Double.parseDouble(args[0]);

				// Create the clock object
        Clock clock = new Clock (0 ,0 ,0.0 ,timeSlice, totalSeconds);

        // Initialize totalSeconds and angle
        totalSeconds = clock.getSeconds();
        toAngle(totalSeconds);

        //INITIALIZING zoneAddition
        double zoneAddition = (toAngle(timeSlice) / 2);

        //RUNNING
        while (totalSeconds < 43200) {

            clock.iterate();
            totalSeconds = clock.getSeconds();

            angle = toAngle(totalSeconds);

            suppAngle = (360 - angle);

            if(angle == inputAngle || suppAngle == inputAngle || zoneCheck(angle, zoneAddition, inputAngle) == true || zoneCheck(suppAngle, zoneAddition, inputAngle) == true) {
                newString = newString + ( clock.toString() ) + "\n";
            }
        }

        System.out.println(newString);

    }

    public static double toAngle(double totalSeconds) {

        int hours = (int) totalSeconds / 3600;
        double leftover = totalSeconds % 3600;
        double minutes = leftover / 60;
        double seconds = leftover % 60;

        double handAngle = 0.5 * ( (double) hours * 60 + minutes);
        double minuteAngle = 6 * minutes;

        return Math.abs(handAngle - minuteAngle);
    }

    public static Boolean zoneCheck(double angle, double zoneAddition, double inputAngle) {

        if (angle < inputAngle && inputAngle < angle + zoneAddition) {
            return true;
        } else if (angle > inputAngle && inputAngle > angle - zoneAddition) {
            return true;
        } else {
            return false;
        }
    }
}
