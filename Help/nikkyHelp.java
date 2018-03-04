public class Riemann
{
    public static double polyCalc(Double[] f, Double x) {
		int length = f.length;
		int lastArg = length - 1;
		int spaceCount = 0;
		double result = 0;

		for (int i = lastArg; i >= 0; i--){
		    for (int j = spaceCount; j < lastArg + 1; j++) {
			    double set = f[j] * (Math.pow(x, i));
			    result += set;
            }
		}
		return result;
	}

    public static double midpoint(double lower, double upper) {
		double mid = (lower + upper) / 2;
		return mid;
	}

    public static void main (String[] args) {
        double lower = Double.parseDouble(args[args.length - 2]);
        double upper = Double.parseDouble(args[args.length - 1]);
        int polynomialLength = args.length - 3;

        if (args.length <= 2) {
            throw new IllegalArgumentException("INSUFFICIENT DATA");
        }
        else if (lower > upper) {
            throw new IllegalArgumentException("INAPPROPRIATE BOUNDS");
        }

        Double[] polynomial = new Double[polynomialLength];
        int count = 1;
        for (int i = 0; i < polynomialLength; i++) {
            polynomial[i] = Double.parseDouble(args[count]);
            count++;
        }

        double area = (upper - lower) * (polyCalc(polynomial, (midpoint(upper, lower))));
		double lastArea = 0;
		double slices = 2;

        while (Math.abs(lastArea - area) > .001) {
			double sliceWidth =  Math.abs((upper - lower) / slices);
			double newLow = lower;
		    double newUp = lower + sliceWidth;
			lastArea = area;
			area = 0;

			for(int i = (int) slices; i > 0; i--) {
			    double newArea = ((newUp - newLow) * (polyCalc(polynomial, (midpoint(newUp, newLow)))));
				area += newArea;
				newLow += sliceWidth;
				newUp += sliceWidth;
			}
			slices = slices * 2;
		}
        System.out.println(area);
    }
}
