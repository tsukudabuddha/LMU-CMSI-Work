public class Riemann {

	  public static void main(String[] args){

		    if(args.length < 3){
					  System.out.println("INSUFFICIENT DATA");
				} else {
			      final double DEGREE_OF_ACCURACY = .00001;
				    double upperBound = Double.parseDouble(args[args.length - 1]);
				    double lowerBound = Double.parseDouble(args[args.length - 2]);
				    if(upperBound < lowerBound){
							  System.out.println("INAPPROPRIATE BOUNDS");
						} else {
				    		double estimate = 0;
				    		double temp = 0;
				        double sum1 = 0;
				        double sum2 = 0;
								double accuracy = 1;
				        double[] coefficients = new double[args.length - 2];

				        for(int i = args.length - 3; i >= 0; i--){
                   coefficients[i] = Double.parseDouble(args[i]);
				        }

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

						  }
		    }
    } // main()
} // Class
