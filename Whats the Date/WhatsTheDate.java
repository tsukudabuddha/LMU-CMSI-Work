public class WhatsTheDate {

  public static String addDays(int month, int day, int year, int daysToAdd){
    if(CalendarStuff.isValid(month, day, year)){

      for(int i = daysToAdd; i > 0; i -= 1){
        day ++;
        if(day > CalendarStuff.daysInMonth(month, year)){
          month ++;
          day = 1;
        }
        if(month > 12){
          year ++;
          month = 1;
        }

      }
      return Integer.toString(month) + " " + Integer.toString(day) + " " + Integer.toString(year);
  } else {
    throw new IllegalArgumentException();
  }
}


  public static void main(String[] args){
      int month = Integer.parseInt(args[0]);
      int day = Integer.parseInt(args[1]);
      int year = Integer.parseInt(args[2]);
      int daysToAdd = Integer.parseInt(args[3]);
      System.out.println( addDays(month, day, year, daysToAdd) );
  }

}
