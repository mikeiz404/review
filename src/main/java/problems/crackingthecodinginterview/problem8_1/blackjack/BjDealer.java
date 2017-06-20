package problems.crackingthecodinginterview.problem8_1.blackjack;

import problems.crackingthecodinginterview.problem8_1.Deck;

public class BjDealer
{
	private final Deck<BjCard> deck;
	
	public BjDealer( Deck<BjCard> deck )
	{
		this.deck = deck;
	}
	
	public BjHand dealHand( )
	{
		BjHand hand = new BjHand();
		hand.takeCard(deck.deal());
		
		return new BjHand();
	}
	
	public void hit( BjHand hand )
	{
		hand.takeCard(deck.deal());
	}
}
