public class ChangeMaker {


    public static int[] coinChecker(int[] coins, int amount){
			  int[] returningArray = new int[coins.length];
				int x = 0;
			  for(int i = 0; i < coins.length; i++){
					  if(amount == 0){
							  returningArray = new int[] {0,0,0};
						}
					  else if(amount == coins[i]){
							  returningArray = new int[] {1,0,0};
						} else if(amount - coins[i] > 0){
							  while(amount > coins[i]){
									  amount -= coins[i];
										x++;
								}
								returningArray = new int[] {x,0,0};
						} else if(amount - coins[i] < 0){
							  return new int[] {-1,-1,-1};
						}
						System.out.println("cC" + i);
				}
				return returningArray;
		}

	  public static void main(String[] args){
			  int[] coins = new int[args.length];
				for(int i = 0; i < args.length; i++){
					  coins[i] = Integer.parseInt(args[i]);
				}
				Tuple currency = new Tuple(coins);
				try{
					  Integer.parseInt(args[(args.length - 1)]);
				} catch(IndexOutOfBoundsException ex){
            System.out.println("Input integers for the coin(s) worth");
				}
				int amount = Integer.parseInt(args[(args.length - 1)]);
				Tuple[][] table = new Tuple[coins.length][amount + 1];

				for( int i = 0; i < coins.length; i++){
					  for(int j = 0; j < amount; j++){
								if((i - j) == 0){
									  table[i][j] = new Tuple(coins.length);
								}
								else {
							      table[i][j] = new Tuple(coinChecker(coins, amount));
							  }
								System.out.println("j" + j);
						}
						System.out.println("i" + i);
				}
				for(int i = 0; i < coins.length; i++){
					  System.out.println(table[amount][i] + " x " + coins[i] + " cent coins");
				}
				System.out.println("Total number of coins: " + table[coins.length][amount]);



		}
}
