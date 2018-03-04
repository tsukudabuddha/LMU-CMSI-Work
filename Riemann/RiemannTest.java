public class RiemannTest {

	  public static void main(String[] args){
			  final double DEGREE_OF_ACCURACY = .00001;
		    try{
				    double upperBound = Double.parseDouble(args[args.length - 1]);
				  	double lowerBound = Double.parseDouble(args[args.length - 2]);
						if(upperBound < lowerBound){

						    double sum1 = 0;
						    double sum2 = 0;

						    double[] coefficients = new double[args.length - 2];

						    for(int i = args.length - 3; i >= 0; i--){
		               coefficients[i] = Double.parseDouble(args[i]);
						    }

						    double accuracy = 10;
						    double width = upperBound - lowerBound;
						    double height = 0;
						    double estimate = 0;
						    double temp = estimate * 5;

						    while(accuracy > DEGREE_OF_ACCURACY){

							      for(int i = coefficients.length - 1; i >= 0; i--){
				                estimate += (coefficients[i]*(Math.pow(upperBound, (i + 1))) / (i + 1));
								    }

								    for(int i = coefficients.length - 1; i >= 0; i--){
				                estimate -= (coefficients[i]*(Math.pow(lowerBound, (i + 1))) / (i + 1));
								    }

								    accuracy = temp/estimate;
								    temp = estimate;

								    if(accuracy > DEGREE_OF_ACCURACY){
									      estimate = 0;
								    }
						    }
						    System.out.println(estimate);
						} else {
							  System.out.println("INAPPROPRIATE BOUNDS");
						}

			  } catch(IndexOutOfBoundsException ex) {
				    throw new IllegalArgumentException("You must input a coefficient, a lower bound, and an upper bound");
			  }

			}

}
