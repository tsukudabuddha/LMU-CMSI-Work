import java.util.Arrays;
public class BigInteger {
	  private String stringValue;
	  private String[] stringArray;
	  private int[] integerArray;
		private int signum = 1;

	  public BigInteger ( String val ){ // one of several java.math.BigInteger constructors
			  if(val.contains("-")){
					  this.signum = -1;
						val = val.substring(1, val.length());
				}
				while(val.startsWith("0") && val.length() > 1){
					  val = val.substring(1, val.length());
				}
			  this.stringArray = val.split("");
				this.stringValue = val;
	      this.integerArray = new int[this.stringArray.length];
				for(int i = stringArray.length - 1; i >= 0; i--){
            this.integerArray[stringArray.length - 1 - i] = Integer.parseInt(this.stringArray[i]);
				}
		}

		public BigInteger add ( BigInteger val ) { // returns a BigInteger whose value is this plus val
				int resultLength = this.integerArray.length;
			  if(this.integerArray.length < val.integerArray.length){
            resultLength = val.integerArray.length;
				}
				int[] resultArray = new int[resultLength];
				boolean remainder = false;
				if(this.signum == 1 && val.signum == 1 ){
					  if(resultLength == this.integerArray.length){
							  for(int i = 0; i < resultLength; i++){
									  try{
										    resultArray[i] = this.integerArray[i] + val.integerArray[i];
										} catch (IndexOutOfBoundsException ex){
											  resultArray[i] = this.integerArray[i];
										}
										if(remainder){
											  resultArray[i] += 1;
										}
										if(resultArray[i] > 9){
											  remainder = true;
												resultArray[i] -= 10;
										} else if(resultArray[i] < 9) {
											  remainder = false;
										}
										// add to length of array
										if(i == resultLength - 1 && remainder){
		                    resultArray = addRemainder(resultArray);
										}
								}
						} else if(resultLength == val.integerArray.length){
							  for(int i = 0; i < resultLength; i++){
									  try{
										    resultArray[i] = this.integerArray[i] + val.integerArray[i];
										} catch (IndexOutOfBoundsException ex){
											  resultArray[i] = val.integerArray[i];
										}

										if(remainder){
											  resultArray[i] += 1;
										}
										if(resultArray[i] > 9){
											  remainder = true;
												resultArray[i] -= 10;
										} else if(resultArray[i] < 9) {
											  remainder = false;
										}
										// add to length of array
										if(i == resultLength - 1 && remainder){
		                    resultArray = addRemainder(resultArray);
										}
								}
						}
				} else if(this.signum == -1){
					  val.subtract(this);
				} else if(val.signum == -1){
					  this.subtract(val);
				}
		    String returnString = "";
		    for(int i = resultArray.length - 1; i >= 0; i--){
					  returnString += resultArray[i];
				}

				BigInteger result = new BigInteger(returnString);
				if(zeroCheck(result)){
					  result = ZERO;
				}
		    return result;

		}

		// Does not work for negative numbers
	  public BigInteger subtract ( BigInteger val ) { // returns a BigInteger whose value is this minus val
			  int resultLength = this.integerArray.length;
			  if(this.integerArray.length < val.integerArray.length){
            resultLength = val.integerArray.length;
				}
				int[] resultArray = new int[resultLength];
				boolean remainder = false;
				if(this.compareTo(val) == 1){
				    for(int i = 0; i < resultLength; i++){
								try{
										resultArray[i] = this.integerArray[i] - val.integerArray[i];
								} catch (IndexOutOfBoundsException ex){
										resultArray[i] = this.integerArray[i];
								}
								if(remainder){
										resultArray[i] -= 1;
								}
								if(resultArray[i] < 0){
										remainder = true;
										resultArray[i] += 10;
								} else if(resultArray[i] > 0) {
										remainder = false;
								}
						}
				} else if(this.compareTo(val) == -1){
						for(int i = 0; i < resultLength; i++){
					      try{
								    resultArray[i] = val.integerArray[i] - this.integerArray[i];
						    } catch (IndexOutOfBoundsException ex){
								    resultArray[i] = val.integerArray[i];
						    }
								if(remainder){
										resultArray[i] -= 1;
								}
								if(resultArray[i] < 0){
										remainder = true;
										resultArray[i] += 10;
								} else if(resultArray[i] > 0) {
										remainder = false;
								}
						}
				}

        String returnString = "";
				for(int i = resultArray.length - 1; i >= 0; i--){
						returnString += resultArray[i];
				}

				BigInteger result = new BigInteger(returnString);
				if(zeroCheck(result)){
					  result = ZERO;
				}
				if(this.compareTo(val) == -1){
					  result.signum = -1;
				}
		    return result;
		}

		// Does not work for negative numbers
		public BigInteger multiply ( BigInteger val ) { // returns a BigInteger whose value is this times val
			  BigInteger result = this;
				BigInteger counter = ZERO;
				if(this.signum != val.signum){
					  result.signum = -1;
				}

			  if(val.equals(ZERO) || this.equals(ZERO)){
				    result = ZERO;
				} else {
					  while(counter.compareTo(val) == -1){
							  result = result.add(this);
								counter = counter.add(ONE);
								if(counter.compareTo(val) == 0){
									  result = result.subtract(this);
								}
						}
				}
				return result;
		}
		// Does not work for negative numbers
		public BigInteger divide ( BigInteger val ) { // returns a BigInteger whose value is this divided by val
			  BigInteger temp = this;
				BigInteger counter = ZERO;

			  if(val.equals(ZERO)){
				    System.out.print("Cannot divide by zero");
				} else if(this.equals(ZERO)){
            counter = ZERO;
				} else {
					  while(temp.compareTo(val) == 1){
							  temp = temp.subtract(val);
								counter = counter.add(ONE);
						}
						if(temp.compareTo(val) == 0){
								counter = counter.add(ONE);
						}
				}

				return counter;
		}
		public BigInteger remainder ( BigInteger val ) { // returns a BigInteger whose value is the remainder of this when divided by val
			  BigInteger result = this;
			  while(result.compareTo(val) == 1){
					  result = result.subtract(val);
				}
				return result;

		}
		public String toString () { // returns the decimal string represention of this BigInteger
			  String toString = this.stringValue;
			  if(this.signum == -1){
					  toString = "-" + toString;
				}
			  return toString;
		}
		public int compareTo ( BigInteger val ) { // returns -1 or 0 or 1 according to whetner this BigInteger is numerically less than or equal to or greater than val
        int result = 5;
				int sum = 0;
				if(this.signum == 1 && val.signum == -1){
					  result = 1;
				} else if(val.signum == 1 && this.signum == -1){
					  result = -1;
				} else if(val.signum == 1 && this.signum == 1){
					  if(this.integerArray.length > val.integerArray.length){
							  result = 1;
						} else if(this.integerArray.length < val.integerArray.length){
							  result = -1;
						} else if(this.integerArray.length == val.integerArray.length){
							  for(int i = this.integerArray.length - 1; i >= 0; i--){
									  if(this.integerArray[i] > val.integerArray[i]){
											  result = 1;
												break;
										} else if(this.integerArray[i] < val.integerArray[i]){
											  result = -1;
												break;
										} else if((i == 0) && this.integerArray[i] == val.integerArray[i]){
											  result = 0;
												break;
										}
								}
						}
				} else if(val.signum == -1 && this.signum == -1){
						for(int i = this.integerArray.length - 1; i >= 0; i--){
								if(this.integerArray[i] < val.integerArray[i]){
										result = 1;
										break;
								} else if(this.integerArray[i] > val.integerArray[i]){
										result = -1;
										break;
								} else if((i == 0) && this.integerArray[i] == val.integerArray[i]){
										result = 0;
										break;
								}
						}
				}
				return result;
		}
		public boolean equals ( Object x ) { // returns true iff x is a BigInteger whose value is numerically equal to this BigInteger
			  boolean result = false;
				if(x == null){
					  return false;
				} else if(this.getClass() == x.getClass()){
            if(this.toString().compareTo(x.toString()) == 0){
					      result = true;
				    }
				}
				return result;
		}
		public static final BigInteger ZERO = new BigInteger("0"); // a classwide constant for zero
		public static final BigInteger ONE = new BigInteger("1");; // a classwide constant for one
		public static final BigInteger TEN = new BigInteger("10");; // a classwide constant for ten

		public static BigInteger valueOf ( long val ){ // constructs, and returns, a BigInteger from a long
			  return new BigInteger(String.valueOf(val));
		}

		public static int[] addRemainder(int[] array) {
        array  = Arrays.copyOf(array, array.length + 1);
    	  array[array.length - 1] = 1;
    	  return array;
		}

		public static String arrayToString(int[] array){
			  String returnString = "";
        for(int i = 0; i < array.length; i++){
					  returnString += Integer.toString(array[i]);
				}
				return returnString;
		}

		public static boolean zeroCheck(BigInteger val){
			  boolean result = false;
				if(val.compareTo(ZERO) == 0 ){
					  result = true;
				}
				return result;
		}

		public static void main(String[] args) {
			  long x = 10;
				BigInteger bigInt0 = new BigInteger("0");
        BigInteger bigInt1 = new BigInteger("0010");
				BigInteger bigInt2 = new BigInteger("10");
				BigInteger bigInt3 = new BigInteger("-10");
				BigInteger bigInt4 = BigInteger.valueOf(x);
				BigInteger bigInt5 = new BigInteger("500");
				BigInteger bigInt6 = new BigInteger("-10");
				BigInteger bigInt7 = new BigInteger("11111111111111111112121212112211212121212121221212122121212121212121212121212121212121211111111111111");

				// Addition Tests
				System.out.println("Add function tests:");
				System.out.println(bigInt1.add(bigInt2).toString().compareTo("20") == 0);
				System.out.println(bigInt1.add(bigInt4).toString().compareTo("20") == 0);
				System.out.println(bigInt1.add(bigInt5).toString().compareTo("510") == 0);
				System.out.println(bigInt1.add(bigInt3).toString().compareTo("0") == 0);
				System.out.println(bigInt5.add(bigInt1).toString().compareTo("510") == 0);


				// equals method
				System.out.println("\nequals function tests:");
				System.out.println(bigInt1.equals(bigInt2));
				System.out.println(! bigInt1.equals(bigInt3));
				System.out.println(bigInt4.equals(bigInt2));
				System.out.println(! bigInt5.equals(bigInt3));
				System.out.println(bigInt0.multiply(bigInt1).equals(ZERO));
				System.out.println(bigInt0.divide(bigInt1).equals(ZERO));

				// compareTo
				System.out.println("\ncompareTo function tests:");
				System.out.println(bigInt4.compareTo(bigInt1) == 0);
				System.out.println(bigInt0.compareTo(bigInt5) == -1);
				System.out.println(bigInt3.compareTo(ZERO) == -1);
				System.out.println(ZERO.compareTo(bigInt3) == 1);
				System.out.println(bigInt3.compareTo(bigInt6) == 0);

				// toString
				System.out.println("\ntoString function tests:");
				System.out.println(bigInt4.toString().compareTo("10") == 0);
				System.out.println(bigInt0.toString().compareTo("0") == 0);
				System.out.println(bigInt3.toString().compareTo("-10") == 0);
				System.out.println(bigInt5.toString().compareTo("500") == 0);

				// multiply
				System.out.println("Multiply function tests:");
				System.out.println(bigInt1.multiply(bigInt2).toString());
				System.out.println(bigInt1.add(bigInt4).toString().compareTo("20") == 0);
				System.out.println(bigInt1.add(bigInt5).toString().compareTo("510") == 0);
				System.out.println(bigInt1.add(bigInt3).toString().compareTo("00") == 0);

				// subtract funcitons:
				System.out.println("Subtract tests:");
				System.out.println(bigInt1.subtract(bigInt2).toString().compareTo("0") == 0);
				System.out.println(bigInt1.subtract(bigInt5).toString().compareTo("-490") == 0);

				// divide
				System.out.println("Divide tests:");
				System.out.println(bigInt5.divide(bigInt1).toString());
				System.out.println(bigInt1.divide(new BigInteger("3")).toString());

				// multiply
				System.out.println("Multiply tests:");
				System.out.println(bigInt5.multiply(bigInt1).toString());
				System.out.println(bigInt1.multiply(bigInt2).toString());
				System.out.println(bigInt7.multiply(bigInt1).toString());

		}
}
