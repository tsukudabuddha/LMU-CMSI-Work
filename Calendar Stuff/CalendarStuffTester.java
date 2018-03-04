/**
    This is a test program for the three  methods in public class CalendarStuff.
    There are (presently) forty-five separate tests. Successful (unsuccessful) tests output true
    (respectively, false).
*/

public class CalendarStuffTester {

    public static void main ( String [] args ) {
        test_isLeap();  // 8 tests
        test_isValid(); // 27 tests
        test_inOrder(); // 10 tests
    }

    static void test_isLeap(){

        System.out.println ( "\nEIGHT TESTS FOR isLeap():\n" );

        try { System.out.println ( CalendarStuff.isLeap (1600) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isLeap (1700) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isLeap (1800) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isLeap (1900) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.isLeap (1992) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isLeap (1993) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isLeap (1994) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isLeap (1995) ); }
        catch ( Exception e ) { System.out.println ( false ); }
    }

    static void test_isValid() {

        System.out.println ( "\nTWENTY-SEVEN TESTS FOR isValid():\n" );

        try { System.out.println ( ! CalendarStuff.isValid (0,10,2000) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (13,25,2001) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (11,0,2000) ); }
        catch ( Exception e ) { System.out.println ( false ); }


        try { System.out.println ( CalendarStuff.isValid (1,31,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (1,32,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.isValid (2,28,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (2,29,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.isValid (3,31,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (3,32,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.isValid (4,30,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (4,31,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.isValid (5,31,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (5,32,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.isValid (6,30,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (6,31,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.isValid (7,31,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (7,32,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.isValid (8,31,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (8,32,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.isValid (9,30,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (9,31,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.isValid (10,31,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (10,32,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.isValid (11,30,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (11,31,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.isValid (12,31,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.isValid (12,32,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

    }

    static void test_inOrder() {

        System.out.println ( "\nTEN TESTS FOR inOrder():\n" );

        try { System.out.println ( ! CalendarStuff.inOrder (1,32,2000,2,1,2000) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.inOrder (1,1,2000,2,29,2001) ); }
        catch ( Exception e ) { System.out.println ( false ); }


        try { System.out.println ( ! CalendarStuff.inOrder (12,31,2014,12,20,2014) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.inOrder (11,30,2014,12,31,2013) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.inOrder (10,15,2005,10,10,2005) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( ! CalendarStuff.inOrder (10,15,2005,9,20,2005) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.inOrder (9,1,2005,9,1,2005) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.inOrder (2,10,2005,8,12,2005) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.inOrder (9,1,2005,8,2,2006) ); }
        catch ( Exception e ) { System.out.println ( false ); }

        try { System.out.println ( CalendarStuff.inOrder (9,10,2005,7,8,2007) ); }
        catch ( Exception e ) { System.out.println ( false ); }
    }


}
