package problems.crackingthecodinginterview.problem8_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import static com.google.common.base.Preconditions.checkState;

public class Deck<C>
{
	private ArrayList<C> cards;
	
	public Deck( )
	{
		this(new ArrayList<>());
	}
	
	public Deck( Collection<C> cards )
	{
		this.cards = new ArrayList<>(cards);
	}
	
	public void shuffle( )
	{
		Collections.shuffle(this.cards);
	}
	
	public C deal( )
	{
		checkState(getSize() > 0);
		
		return this.cards.remove(this.cards.size() - 1);
	}
	
	public int getSize( )
	{
		return this.cards.size();
	}
	
	public List<C> getCards( )
	{
		return this.getCards();
	}
	
	public List<C> replaceCards( Collection<C> cards )
	{
		return this.cards = new ArrayList<>(cards);
	}
}
