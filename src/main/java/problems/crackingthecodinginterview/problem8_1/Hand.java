package problems.crackingthecodinginterview.problem8_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Hand<C>
{
	private final ArrayList<C> cards;
	
	public Hand( )
	{
		this.cards = new ArrayList<>();
	}
	
	public Hand( Collection<C> cards )
	{
		this.cards = new ArrayList<>(cards);
	}
	
	public List<C> getCards( )
	{
		return this.cards;
	}
	
	public int getSize( )
	{
		return this.cards.size();
	}
	
	public void takeCard( C card )
	{
		this.cards.add(card);
	}
	
	public void discard( C card )
	{
		if( !this.cards.remove(card) )
		{
			throw new IllegalArgumentException("Card does not exist in hand.");
		}
	}
}
