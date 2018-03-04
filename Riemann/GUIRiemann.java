import java.awt.*;
import javax.swing.*;

public class GUIRiemann extends JFrame {

	  public GUIRiemann(String output) {
			  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				JFrame frame = new JFrame();
				this.setTitle("GUI Riemann Sums");
		    this.setSize(1000,100);
		    this.setLocation((screenSize.width / 2) - 500,(screenSize.height / 2));
				JLabel label = new JLabel("<html><div style='text-align: center;'>" + output + "</div></html>");
				add(label);
				this.setVisible(true);
	  }

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
								String outputString = "";
								String superScript = "";

								for(int i = coefficients.length - 1; i >= 0; i--){
									  if(i > 0){
											  switch (i) {
												    case 1: superScript = "";
														break;
														case 2: superScript = "\u00B2";
														break;
														case 3: superScript = "\u00B3";
														break;
														case 4: superScript = "\u2074";
														break;
														case 5: superScript = "\u2075";
														break;
														case 6: superScript = "\u2076";
														break;
														case 7: superScript = "\u2077";
														break;
														case 8: superScript = "\u2078";
														break;
														case 9: superScript = "\u2079";
														break;
												}
												if(i > 9){
													  superScript = "^" + i;
												}
												if(i == coefficients.length - 1){
													  if(coefficients[i] < 0){
															  outputString += "-" + Math.abs(coefficients[i]) + "x" + superScript;
														} else {
															  outputString += Math.abs(coefficients[i]) + "x" + superScript;
														}

												} else {
													  if(coefficients[i] < 0){
														    outputString += coefficients[i] + "x" + superScript;
														} else {
															  outputString += " + " + coefficients[i] + "x" + superScript;
														}
												}

										} else {
											  if(coefficients[i] < 0){
													  outputString += " - " + coefficients[i];
												} else {
													  outputString += " + " + coefficients[i];
												}

										}
								}
								//System.out.println(outputString);
								outputString += " = " + estimate;
								JFrame f = new GUIRiemann(outputString);
					      f.show();

								}

						}
    } // main()
} // Class
