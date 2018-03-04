/**
	This class models ordinary playing cards.
*/
public class Card {

	// public constants:

	public static final int ACE	= 1;
	public static final int DEUCE	= 2;
	public static final int TWO	= 2;
	public static final int THREE	= 3;
	public static final int FOUR	= 4;
	public static final int FIVE	= 5;
	public static final int SIX	= 6;
	public static final int SEVEN	= 7;
	public static final int EIGHT	= 8;
	public static final int NINE	= 9;
	public static final int TEN	= 10;
	public static final int JACK	= 11;
	public static final int KNAVE	= 11;
	public static final int QUEEN	= 12;
	public static final int KING	= 13;

	public static final int SPADES	= 1;
	public static final int HEARTS	= 2;
	public static final int DIAMONDS = 3;
	public static final int CLUBS	= 4;

	// private instance data;

	private int rank;
	private int suit;

	// public constructor:
	public Card ( int rank, int suit ) {
		if ( rank < Card.ACE | rank > Card.KING | suit < Card.SPADES | suit > Card.CLUBS ) {
			throw new IllegalArgumentException();
		} else {
			this.rank = rank;
			this.suit = suit;
		}
	}

	/** Returns this card's suit. */
	public int getSuit() {
		return this.suit;
	}

	/** Returns this card's rank. */
	public int getRank() {
		return this.rank;
	}

	/** Instance method that returns a stringy version of this card. */
	public String toString() {
		String result = "";
		switch ( this.rank ) {
			case 1 : result = "Ace of "; break;
			case 2 : result = "Deuce of "; break;
			case 3 : result = "Three of "; break;
			case 4 : result = "Four of "; break;
			case 5 : result = "Five of "; break;
			case 6 : result = "Six of "; break;
			case 7 : result = "Seven of "; break;
			case 8 : result = "Eight of "; break;
			case 9 : result = "Nine of "; break;
			case 10 : result = "Ten of "; break;
			case 11 : result = "Jack of "; break;
			case 12 : result = "Queen of "; break;
			case 13 : result = "King of "; break;
			default : throw new UnsupportedOperationException();
		}

		switch ( this.suit ) {
			case 1 : result += "Spades"; break;
			case 2 : result += "Hearts"; break;
			case 3 : result += "Diamonds"; break;
			case 4 : result += "Clubs"; break;
			default : throw new UnsupportedOperationException();
		}
		return result;
	}

	/** Classwide method that returns a stringy version of this card. */
	public static String toString ( Card c ) {
		return c.toString();
	}

	/** Test program for this class. */
	public static void main ( String[] args ) {
		// Replace the next instruction with your code:
		Card c = new Card(ACE,SPADES);
		Card c1 = new Card(KING,CLUBS);
		System.out.println(c.toString());
		System.out.println(c1.toString());
	}

}
