/**
    This is an illustrative collection of string-related methods plus
    a comprehensive set of unit tests (see method main().

    We will prescribe some additional methods at class which you should
    add to this class along with corresponding tests.

*/
public class StringMethods {

    /** Returns true iff s is the empty string. */
	public static boolean isEmptyString ( String s ) {
		return s.length() == 0;
	}

    /** Returns true iff s contains at least one instance of ch. */
	public static boolean containsChar ( String s, char ch ) {
		for ( int i = 0; i < s.length(); i++ ) {
			if ( s.charAt(i) == ch ) {
				return true;
			}
		}
		return false;
	}

    /** Returns true iff s contains at least one vowel. */
	public static boolean containsVowel ( String s) {
		return  containsChar(s,'A') || containsChar(s,'a') ||
                containsChar(s,'E') || containsChar(s,'e') ||
                containsChar(s,'I') || containsChar(s,'i') ||
                containsChar(s,'O') || containsChar(s,'o') ||
                containsChar(s,'U') || containsChar(s,'u');
	}

    /** Iterative method which returns true iff s is a palindrome. */
	public static boolean isPalindrome ( String s) {
		if ( s.length() <= 1 ) { return true; }
        else {
            int i, j;
            for ( i = 0, j = s.length()-1; i < j; i++, j-- ) {
                if ( s.charAt(i) != s.charAt(j) ) {
                    return false;
                }
            }

            return true;
        }
	}

    /** Recursive method which returns true iff s is a palindrome. */
	public static boolean isPalindrome2 ( String s) {
		if ( s.length() <= 1 ) {
            return true;
        }
        else if ( s.charAt(0) != s.charAt( s.length()-1 ) ) {
            return false;
        } else {
            switch ( s.length() ) {
                case 2 :
                case 3 :    return true;
                default :   return isPalindrome( s.substring ( 1, s.length()-1 ) );
            }
        }
	}
		// Returns a string composed of only the odd-indexed chars of s
		public static String everyOtherCharOf ( String s ) {
			String newString = "";
			for(int i = 1; i < s.length(); i++) {
				if((i) % 2 != 0){
				newString += s.charAt(i);
				}
			}
			return newString;
		}
		// Returns
		public static boolean containsSubstring ( String s, String t){
			boolean sub = false;
			for ( int i = 0 ; i <= s.length() - t.length(); i++ ){
        if(s.substring(i, i + ( t.length() ) ).equals(t)) {
            sub = true;
						break;
        }
			}
			return sub;
		}
		// Returns the "alphabetical respelling" of s
		public static String alphabeticalize ( String s ){
			char arr[] = s.toCharArray();
			char tmp = 'a';
			for(int a = 0; a < s.length(); a++){
				for(int i = 0; i < s.length() - 1; i++){
					if(arr[i] > arr[i + 1])
					{
						tmp = arr[i + 1];
						arr[i + 1] = arr[i];
        		arr[i] = tmp;
					}
				}
			}
			return String.valueOf(arr);
		}


		public static boolean areAnagrams(String s, String t){
				if(alphabeticalize(s).equals(alphabeticalize(t)))
					return true;
				else
					return false;
		}

		public static String randomize(String s){
			String randomString = "";
			char tmp;
			int x;
			while(s.length() > 0){
				x = (int) Math.floor(Math.random() * s.length());
				tmp = s.charAt(x);
				s = s.substring(0, x) + s.substring(x + 1); // Thomas Booschner Help
				randomString += tmp;
			}
			return randomString;
		}


		/** Comprehensive set of tests for the above methods. */
    public static void main ( String[] args ) {
			/*
			System.out.println ( "\nareAnagrams tests (5):" );
			System.out.println ( StringMethods.areAnagrams( "repaint","painter") );
			System.out.println ( StringMethods.areAnagrams( "printer","reprint") );
			System.out.println ( StringMethods.areAnagrams( "cheating","teaching") );
			System.out.println ( StringMethods.areAnagrams( "relation","oriental") );
			System.out.println ( StringMethods.areAnagrams( "Hello","lloHeeee") );

			System.out.println ( StringMethods.randomize( "abcd") );
			System.out.println ( StringMethods.randomize( "Andrew") );
			System.out.println ( StringMethods.randomize( "Hello") );

				/*
				System.out.println ( "\nalphabeticalize tests:" );
				System.out.println ( alphabeticalize("") );
				System.out.println ( alphabeticalize("orange") );
				System.out.println ( alphabeticalize("tabernacle") );
				System.out.println ( alphabeticalize("Andrew") );
				System.out.println ( alphabeticalize("abcde") );
				System.out.println ( alphabeticalize("edcba") );

				System.out.println ( "\neveryOtherCharOf tests:" );
				System.out.println ( everyOtherCharOf("") );
				System.out.println ( everyOtherCharOf("a") );
				System.out.println ( everyOtherCharOf("ab") );
				System.out.println ( everyOtherCharOf("Andrew") );


				System.out.println ( "\ncontainsSubstring tests:" );
	      System.out.println ( containsSubstring("abcd", "a") );
        System.out.println ( ! containsSubstring("bcfdd", "Hello") );
        System.out.println ( containsSubstring("Andrew", "drew") );
				System.out.println ( ! containsSubstring("Tommy", "mom") );
				System.out.println ( ! containsSubstring("", "mom") );
	
				System.out.println ( "\nisEmptyString tests:" );
        System.out.println ( ! isEmptyString( "bcdef") );
        System.out.println ( ! isEmptyString( "3") );
        System.out.println ( isEmptyString( "") );

        System.out.println ( "\ncontainsChar tests:" );
        System.out.println ( containsChar( "bcdaf", 'b') );
        System.out.println ( containsChar( "bcdaF", 'F') );
        System.out.println ( containsChar( "bcdaF", 'c') );
        System.out.println ( containsChar( "X", 'X') );
        System.out.println ( ! containsChar( "bcdaF", 'X') );
        System.out.println ( ! containsChar( "", 'X') );

        System.out.println ( "\ncontainsVowel tests:" );
        System.out.println ( containsVowel("bcdaf") );
        System.out.println ( containsVowel("bcde") );
        System.out.println ( containsVowel("ibcd") );
        System.out.println ( containsVowel("iobcd") );
        System.out.println ( containsVowel("bucud") );
        System.out.println ( containsVowel("bcdAf") );
        System.out.println ( containsVowel("bcdE") );
        System.out.println ( containsVowel("Ibcd") );
        System.out.println ( containsVowel("IObcd") );
        System.out.println ( containsVowel("bUcUd") );
        System.out.println ( ! containsVowel("bcdFGh") );
        System.out.println ( ! containsVowel("") );

        System.out.println ( "\nisPalindrome tests:" );
        System.out.println ( isPalindrome("") );
        System.out.println ( isPalindrome("a") );
        System.out.println ( isPalindrome("bb") );
        System.out.println ( isPalindrome("bcb") );
        System.out.println ( isPalindrome("otto") );
        System.out.println ( isPalindrome("madamimadam") );
        System.out.println ( ! isPalindrome("bc") );
        System.out.println ( ! isPalindrome("bcc") );
        System.out.println ( ! isPalindrome("bcdb") );
        System.out.println ( ! isPalindrome("madaminadam") );

        System.out.println ( "\nisPalindrome2 tests:" );
        System.out.println ( isPalindrome2("") );
        System.out.println ( isPalindrome2("a") );
        System.out.println ( isPalindrome2("bb") );
        System.out.println ( isPalindrome2("bcb") );
        System.out.println ( isPalindrome2("otto") );
        System.out.println ( isPalindrome2("madamimadam") );
        System.out.println ( ! isPalindrome2("bc") );
        System.out.println ( ! isPalindrome2("bcc") );
        System.out.println ( ! isPalindrome2("bcdb") );
        System.out.println ( ! isPalindrome2("madaminadam") );

				*/
				System.out.println ( "\ncontainsSubstring tests (8):" );
        try { System.out.println ( StringMethods.containsSubstring( "bcdef","cde") ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( StringMethods.containsSubstring( "bcdef","def") ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( StringMethods.containsSubstring( "bcdef","bc") ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( StringMethods.containsSubstring( "bcdef","b") ); } catch ( Exception e ) { System.out.println (false); }
      	try {  System.out.println ( StringMethods.containsSubstring( "bcdef","f") ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( StringMethods.containsSubstring( "bcdef","") ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( ! StringMethods.containsSubstring( "bcdef","bcc") ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( ! StringMethods.containsSubstring( "bcdef","cf") ); } catch ( Exception e ) { System.out.println (false); }

        System.out.println ( "\neveryOtherCharOf tests (7):" );
        try { System.out.println ( "yohn".equals (StringMethods.everyOtherCharOf( "Xylophone") ) ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( "yohns".equals (StringMethods.everyOtherCharOf( "Xylophones") ) ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( "b".equals (StringMethods.everyOtherCharOf( "ab") ) ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( "b".equals (StringMethods.everyOtherCharOf( "aba") ) ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( "bx".equals (StringMethods.everyOtherCharOf( "abax") ) ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( "".equals (StringMethods.everyOtherCharOf( "a") ) ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( "".equals (StringMethods.everyOtherCharOf( "") ) ); } catch ( Exception e ) { System.out.println (false); }

        System.out.println ( "\nalphabeticalize tests (5):" );
        try { System.out.println ( "ehlnoopsxy".equals (StringMethods.alphabeticalize( "xylophones") ) ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( "ab".equals (StringMethods.alphabeticalize( "ba") ) ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( "ab".equals (StringMethods.alphabeticalize( "ab") ) ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( "a".equals (StringMethods.alphabeticalize( "a") ) ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( "".equals (StringMethods.alphabeticalize( "") ) ); } catch ( Exception e ) { System.out.println (false); }

        System.out.println ( "\nareAnagrams tests (7):" );
        try { System.out.println ( StringMethods.areAnagrams( "bcdef","cbdfe") ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( StringMethods.areAnagrams( "bcdef","fedcb") ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( StringMethods.areAnagrams( "","") ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( StringMethods.areAnagrams( "a","a") ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( ! StringMethods.areAnagrams( "abb","aba") ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( ! StringMethods.areAnagrams( "aa","aaa") ); } catch ( Exception e ) { System.out.println (false); }
        try { System.out.println ( ! StringMethods.areAnagrams( "a","b") ); } catch ( Exception e ) { System.out.println (false); }

        System.out.println ( "\nrandomize tests (0):" );
    }
	}
