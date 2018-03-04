/**
	This class models an ordinary deck of fifty-two playing cards (indexed from zero).
*/
public class Deck {

	// private instance data;
	private Card[] cards;

	/** This public constructor makes a new deck with the cards in rank order (ace to king)
	by suit, with the suits in Bridge order (spades, hearts, diamonds, clubs). */
	public Deck() {
		this.cards = new Card[52];
		int i = 0;
		for ( int suit = Card.SPADES; suit <= Card.CLUBS; suit++ ) {
			for ( int rank = Card.ACE; rank <= Card.KING; rank++ ) {
				this.cards[i] = new Card(rank,suit);
				i++;
			}
		}
	}

	/** Returns a COPY of the card at the specified index in this deck. */
	public Card cardAt(int index) {
		if ( index < 0 | index > 51 ) {
			throw new IllegalArgumentException();
		} else {
			return new Card( this.cards[index].getRank(),this.cards[index].getSuit() );
		}
	}

	/** Shuffles this deck. */
	public void shuffle() {
		int x = 0;
		Card tmp;
		// Replace the next instruction with your code:
		for (int i = 0; i < this.cards.length; i++){
			x = (int) Math.floor(Math.random() * this.cards.length);
			tmp = this.cards[i];
			this.cards[i] = this.cards[x];
			this.cards[x] = tmp;
    }
	}

	/** Returns a stringy version of this deck. */
	public String toString() {
		String newString = "";
		int i = 0;
		for ( int suit = Card.SPADES; suit <= Card.CLUBS; suit++ ) {
			for ( int rank = Card.ACE; rank <= Card.KING; rank++ ) {
				newString += "\n" + this.cards[i];
				i++;
			}
		}
		return newString;
	}

	/** Test program for this class. */
	public static void main ( String[] args ) {
		// Replace the next instruction with your code:
		int firstCard = 0;
		int lastCard = 0;
		for(int i = 1; i < 100000; i++){
			Deck d = new Deck();
			Deck d1 = new Deck();
			d.shuffle();
			d1.shuffle();
			if(d.cards[0].toString() == d1.cards[0].toString())
				firstCard ++;
			if(d.cards[51].toString() == d1.cards[51].toString())
				lastCard++;
		}
		System.out.println(firstCard);
		System.out.println(lastCard);


}
}
