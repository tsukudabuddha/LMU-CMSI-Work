import java.util.Random;

public class RandomizedIntegration {

		private static double maxHeight(double[] coefficients, double lowerBound, double upperBound) {
		    double maxHeight = 0, temp = 0;
				double x = lowerBound;
				while(x <= upperBound){
					  temp = 0;
				    for(int i = coefficients.length - 1; i >= 0; i--){
					      temp += (coefficients[i]*(Math.pow(x, i)));
		        }
						if(temp > maxHeight){
								maxHeight = temp;
						}
					  x += .0001;
			  }
			  return maxHeight;
		}

		private static double minHeight(double[] coefficients, double lowerBound, double upperBound) {
		    double minHeight = 0, temp = 0;
				double x = lowerBound;
				while(x <= upperBound){
					  temp = 0;
				    for(int i = coefficients.length - 1; i >= 0; i--){
					      temp += (coefficients[i]*(Math.pow(x, i)));
		        }
						if(temp < minHeight){
								minHeight = temp;
						}
					  x += .0001;
			  }
			  return minHeight;
		}

	  private static double f(double[] coefficients, double x){
        double result = 0;
        for(int i = coefficients.length - 1; i >= 0; i--){
            result += (coefficients[i]*(Math.pow(x, i)));
        }
			  return result;
		}

	  public static void main(String[] args){

		    if(args.length < 3){
					  System.out.println("INSUFFICIENT DATA");
				} else {
					  try{
							  double upperBound = Double.parseDouble(args[args.length - 1]);
						    double lowerBound = Double.parseDouble(args[args.length - 2]);
						} catch (NumberFormatException ex) {
							  System.out.println("You must input a double");
						}
						double upperBound = Double.parseDouble(args[args.length - 1]);
						double lowerBound = Double.parseDouble(args[args.length - 2]);
						final double POINT_NUMBER = 1000000;
						double randX = 0, counter = 0, guess = 0;

						double[] coefficients = new double[args.length - 2];
						for(int i = args.length - 3; i >= 0; i--){
							 coefficients[i] = Double.parseDouble(args[i]);
						}
						double maxHeight = maxHeight(coefficients, lowerBound, upperBound);
						double minHeight = minHeight(coefficients, lowerBound, upperBound);
						double height = maxHeight - minHeight;
						double width = upperBound - lowerBound;


				    if(upperBound < lowerBound){
							  System.out.println("INAPPROPRIATE BOUNDS");
						} else {

								for(int i = 0; i < POINT_NUMBER; i++){
									  randX = Math.random() * (upperBound + Math.abs(lowerBound)) - Math.abs(lowerBound);
                    guess = (Math.random() * height) + minHeight;
										if(f(coefficients,randX) > 0){
											  if(guess <= f(coefficients, randX) && guess >= 0){
													  counter++;
												}
										} else {
											  if(guess > f(coefficients, randX) && guess <= 0){
													  counter--;
												}
										}
								}
								double result = height * width * (counter / POINT_NUMBER);

								System.out.println(result);
				    }
		    }
    } // main()
} // Class
