public class CalendarStuff {
  public static boolean isLeap(int year){
    //Divisible by 4 and not centenial
    if(year % 4 == 0){
      if(year % 100 == 0 && year % 400 == 0){
        return true;
      } else if(year % 400 != 0){
        return false;
      } else {
        return true;
      }
    } else {
      return false;
    }
  }

  public static boolean isValid(int month, int day, int year){
    if(month > 0 && month <= 12 && day > 0 && day <= 31 && year >= 0){
      if((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31){
        return true;
      } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30){
        return true;
      } else if(month == 2 && ! isLeap(year) && day <= 28){
        return true;
      } else if(month == 2 && isLeap(year) && day <= 29){
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public static boolean inOrder(int month1, int day1, int year1, int month2, int day2, int year2){
    //In chronocloigcal order
    // MUST BE VALID DATES
    //Add comprehensive set of tests
    switch(month1, day1, year1, month2, day2, year2)
    case (year1 <= year2 && month1 <= month2 && day1 <= day2):
      return true;
    else
      return false;
  }

  public static void main ( String[] args ) {
    System.out.println("\nisLeap() tests:");
    System.out.println( isLeap(2000));
    System.out.println( ! isLeap(2002));
    System.out.println( isLeap(1600));
    System.out.println( ! isLeap(1700));

    System.out.println("\nisValid() tests:");
    System.out.println( isValid(1,1,2000));
    System.out.println( isValid(2,29,2002));
    System.out.println( isValid(2,29,1600));
    System.out.println( isValid(0,0,1700));

    System.out.println("\nisValid() tests:");
    System.out.println( inOrder(1, 1, 2000, 1, 1, 2000) );
    System.out.println( inOrder(1, 1, 2001, 1, 1, 2000) );
    System.out.println( inOrder(1, 2, 2000, 1, 1, 2000) );
    System.out.println( inOrder(2, 1, 2000, 1, 1, 2000) );
    System.out.println( inOrder(0, 1, 2000, 1, 1, 2000) );
    System.out.println( inOrder(1, 1, 2000, 0, 1, 2000) );
    System.out.println( inOrder(2, 29, 2001, 1, 1, 2002) );

  }
}
