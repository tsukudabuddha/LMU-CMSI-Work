public class CalendarStuff {

  public static boolean isLeap(int year){
    //Divisible by 4 and not centenial
    if(year > 0){
      if(year % 4 == 0){
        if(year % 100 == 0 && year % 400 == 0){
          return true;
        } else if(year % 100 == 0 && year % 400 != 0){
          return false;
        } else {
          return true;
        }
      } else {
        return false;
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
    if(isValid(month1, day1, year1) && isValid(month2, day2, year2)) {
      if(year1 < year2) {
        return true;
      } else if(year1 == year2) {
        if(month1 < month2){
          return true;
        } else if(month1 == month2){
          if(day1 <= day2){
            return true;
          }
        }
      }
      else {
        return false;
      }
    } else {
      return false;
    }
    return false;
  }

  public static int daysInMonth(int month, int year){
      switch(month){
        case 1: case 3:
        case 5: case 7:
        case 8: case 10:
        case 12:
          return 31;
        case 4: case 6:
        case 9: case 11:
          return 30;
        case 2:
          return isLeap(year)? 29: 28;
        default: return(0);
      }
      }

  public static void main ( String[] args ) {
    /*
    System.out.println("\nisLeap() tests:");
    System.out.println( isLeap(2000));
    System.out.println( isLeap(1600));
    System.out.println( ! isLeap(2002)); // not divisible by 4
    System.out.println( ! isLeap(1700)); // not quad centenial
    System.out.println( ! isLeap(0)); // year 0 was not a leap year... maybe

    System.out.println("\nisValid() tests:");
    System.out.println( isValid(1,1,2000));
    System.out.println( isValid(2,29,1600));
    System.out.println( isValid(1,1,1700));
    System.out.println( ! isValid(0,0,1700)); // day and month must be greater than 0
    System.out.println( ! isValid(2,29,2002)); // 2002 is not a leap year, so Feb 29 DNE in this context

    System.out.println("\ninOrder() tests:");
    System.out.println( inOrder(1, 1, 2000, 1, 1, 2000) );
    System.out.println( ! inOrder(1, 1, 2001, 1, 1, 2000) ); // 2001 is greater than 2000
    System.out.println( ! inOrder(1, 2, 2000, 1, 1, 2000) ); // 1 day later
    System.out.println( ! inOrder(2, 1, 2000, 1, 1, 2000) ); // 1 month later
    System.out.println( ! inOrder(0, 1, 2000, 1, 1, 2000) ); // month 0 is invalid Date
    System.out.println( ! inOrder(1, 1, 2000, 0, 1, 2000) ); // month2 0 is invalid
    System.out.println( ! inOrder(2, 29, 1700, 1, 1, 2002) ); // Feb 29 1700 DNE
    System.out.println( ! inOrder(-1, 1, 2001, 1, 1, 2000) ); // Cannot be negative
    System.out.println( ! inOrder(0, 0, 0, 0, 0, 0) ); // All zeroes are invalid dates
    */
    System.out.println("\ndaysInMonth() tests:");
    System.out.println( daysInMonth(2, 2000));
    System.out.println( daysInMonth(1, 2000));
  }
}
