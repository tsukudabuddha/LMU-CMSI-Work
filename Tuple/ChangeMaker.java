import java.util.Arrays;

public class ChangeMaker {
    public static void main (String[] args){

    // Input Validation
    if(args.length < 2){
        throw new IllegalArgumentException("BAD DATA");
    }
    for(int i = 0; i < args.length; i++){
        try{
            Integer.parseInt(args[i]);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("BAD DATA");
        }
        if(Integer.parseInt(args[i]) <= 0){
            throw new IllegalArgumentException("BAD DATA");
        }
        for(int j = 0; j < args.length - 1; j++){
            if(Integer.parseInt(args[j]) == Integer.parseInt(args[i]) && j != i){
                throw new IllegalArgumentException("BAD DATA");
            }
        }
    }

		// Create coins array
		int[] coins = new int[args.length - 1];
		for( int i = 0; i < args.length - 1; i++){
				coins[i] = Integer.parseInt(args[i]);
		}

    int[] newOrder = coins.clone();
		int[] originalCoins = coins.clone();

    // Sort coins
    int temp = 0;
    for(int i = 0; i < coins.length - 1; i++){
        if(coins[i] > coins[i + 1]){
            temp = coins[i];
            coins[i] = coins[i + 1];
   					coins[i + 1] = temp;
						i = -1;
			  }
    }
		int counter = 0;

		while(counter < coins.length){
			  for(int i = 0; i < coins.length; i++){
					  for(int j = 0; j < newOrder.length; j++){
							  if(coins[i] == newOrder[j]){
									  newOrder[j] = i;
										counter++;
								}
						}

				}
		}

		// Create empty 2D Tuple Array
		int totalChange = Integer.parseInt(args[args.length - 1]);
		Tuple[][] table = new Tuple[args.length - 1][totalChange + 1];
		for(int i = 0; i < args.length - 1; i++){
			  table[i][0] = new Tuple(coins.length);
				table[i][0].setElement(0, coins[i]);
		}

		// Main changeMaker for loop
		int remiander = 0;
		for(int i = 0; i < coins.length; i++){
		    for(int j = 1; j <= totalChange; j++){
					  if(j > table[i][0].getElement(0)){
							  remiander = j - table[i][0].getElement(0);
								table[i][j] = new Tuple(coins.length);
								table[i][j].setElement(i, 1);
								try{
									  table[i][j] = table[i][j].add(table[i][remiander]);
										if(table[i][j].sum() > table[i - 1][j].sum()){
											  table[i][j] = table[i - 1][j].clone();
										}
								} catch(NullPointerException e){
								try{
									  if(table[i - 1][j] != null){
											  table[i][j] = table[i - 1][j].clone();
										} else {
											  table[i][j] = null;
										}
								} catch (ArrayIndexOutOfBoundsException exc){
									  table[i][j] = null;
								}
							  } catch( ArrayIndexOutOfBoundsException e){

								}
							} else if (j == table[i][0].getElement(0)){
								  table[i][j] = new Tuple(coins.length);
									table[i][j].setElement(i, 1);
							} else {
							    try{
								      table[i][j] = table[i - 1][j].clone();
							    } catch(NullPointerException | ArrayIndexOutOfBoundsException e){
								      table[i][j] = null;
							    }
					    }
			  }
    }

		// Create endRow and endCol
		int endRow = coins.length - 1;
		int endCol = table[0].length - 1;

		// Print out results... prints out Impossible if the remaining Tuple is null
		try{
			  for(int i = 0; i < table[endRow][endCol].length(); i++){
					  System.out.println(table[endRow][endCol].getElement(newOrder[i]) + " X " + originalCoins[i] + " coins");
				}
				System.out.println(table[endRow][endCol].sum() + " total coins");
			  }catch (NullPointerException e){
					  System.out.println("Impossible");
				}
		}
}
