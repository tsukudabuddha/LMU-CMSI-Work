import java.util.Random;

public class Test {

	  public static void main(String[] args){
        String a = "4876";
				String returnString = "";
				String[] stringArray = a.split("");
	      int[] integerArray = new int[stringArray.length];
				for(int i = stringArray.length - 1; i >= 0; i--){
            integerArray[i] = Integer.parseInt(stringArray[i]);
				}
				for(int i = 0; i < integerArray.length; i++){
            returnString += integerArray[i];
				}
				System.out.println(returnString);
    } // main()
} // Class
