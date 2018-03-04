public class ChangeMaker {

	    public static int optimalRow(Tuple[][] table, Tuple tuple, int row, int column) {
				  int total = tuple.sum();
					int optimalRow = 0;
					int checkedTotal = 0;

					for (int i = 0; i <= row; i++ ){
							if(table[i][column] != null) {
						      checkedTotal = table[i][column].sum();
						  }
							if (checkedTotal < total && checkedTotal > 0) {
								optimalRow = i;
								break;
							} else if (checkedTotal == total && i == row) {
								optimalRow = row;
								break;
							}
		}

		return optimalRow;
	}

	  public static void main(String[] args) {
				int counter = 0;
			  int[] coins = new int[args.length - 1 ];
				for( int i = 0; i < args.length - 1; i++){
					  coins[i] = Integer.parseInt(args[i]);
						if( coins[i] < 1){
							  throw new IllegalArgumentException("Enter a valid coin denomination");
						}
				}
				try {
					  Integer.parseInt(args[(args.length - 2)]);
				} catch(IndexOutOfBoundsException ex) {
            System.out.println("IMPOSSIBLE");
				}

				if(Integer.parseInt(args[(args.length - 1)]) < 0){
					  throw new IllegalArgumentException("Enter an amount that is at least 0");
				}
				int totalAmount = Integer.parseInt(args[args.length - 1]);
				Tuple[][] table = new Tuple[coins.length][totalAmount + 1];

// Fill in table w/ empty tuples
				for( int i = 0; i < coins.length; i++){
					  for(int j = 0; j <= totalAmount; j++){
						  table[i][j] = new Tuple(coins.length);
								}
							}
// Fill in first row
				for (int i = 1; i <= totalAmount; i++) {
					  Tuple tuple = table[0][i];
						int coin = Integer.parseInt(args[0]);
						int amount = i;
						int remainder = amount;
						int coinAmt = (int)Math.floor(amount / coin);

						while(remainder >= coin){
								remainder -= coin;
						}
						if (remainder != 0) {
							  tuple = null;
						} else {
							  tuple.setElement(0, coinAmt);
						}
						if(tuple != null){
								//System.out.println(amount);
							  if(tuple.sum() == 0 && i >= 1){
									  tuple = null;
								}
						}
						System.out.println(tuple);
		}

// For Loop to

				for(int i = 1; i < coins.length; i++) {
						int coin = Integer.parseInt(args[i]);

						for (int j = 1; j <= totalAmount; j++) {
						    Tuple tuple = table[i][j];
						  	int amount = j;
							  int remainder = amount - coin;
							  int coinAmt = (int)Math.floor(amount / coin);
								System.out.println(j + " " + coins[i] + " " + coinAmt);

								if (remainder == 0) { //If the coin fits perfectly
									  tuple.setElement(i, coinAmt);
										if(table[i - 1][j] != null){
											  if(table[i - 1][j].sum() < tuple.sum()){
													  tuple = table[i - 1][j].clone();
												}
										}

								} else if (remainder != 0 && amount < coin) { //If the coin leaves a remainder but the value is less than the cents
										if(table[i - 1][j] != null){
											  if(table[i - 1][j].sum() != 0 && table[i - 1][j].sum() < tuple.sum())
											      tuple = table[i - 1][j].clone();
										} else {
											  tuple = null;
										}

								} else if (remainder != 0 && remainder > coin) { //If the coin leaves a remainder but the value is greater than the cents
									  tuple.setElement(i, coinAmt);
										Tuple remainderTuple = new Tuple(args.length);
										Tuple optimalTuple = new Tuple(args.length);
										try{
											  remainderTuple = table[i][remainder].clone();
										} catch(NullPointerException ex) {

										}
										if(remainderTuple != null){
											  if(remainderTuple.sum() != 0)
												    tuple.add(remainderTuple);
										}


										if(tuple != null){
											  int optimalRow = optimalRow(table, tuple, i, j);
												optimalTuple = table[optimalRow][amount].clone();
										} else {
											  optimalTuple = table[i - 1][amount].clone();
										}


										tuple = optimalTuple.clone();

								}
								if(tuple != null){
									  if(tuple.sum() == 0){
											  tuple = null;
										}
								}

				    table[i][j] = tuple;
						//System.out.println("j = " + j);

			}
		}
		    for( int i = 0; i < coins.length; i++){
					  for(int j = 0; j <= totalAmount; j++){
							  //System.out.println(table[i][j]);
								}
							}

				for(int i = 0; i < coins.length; i++){
					  System.out.println(table[coins.length - 1][totalAmount].getElement(i) + " x " + coins[i] + " cent coins");
				}
				System.out.println("Total number of coins: " + (table[coins.length - 1][totalAmount]).sum());



		}

}
