package problems.crackingthecodinginterview.problem8_1;

public class Card<C, S, T>
{
	private final C color;
	private final S suit;
	private final T type;
	
	public Card( C color, S suit, T type )
	{
		this.color = color;
		this.suit = suit;
		this.type = type;
	}

	public C getColor( )
	{
		return this.color;
	}

	public S getSuit( )
	{
		return this.suit;
	}

	public T getType( )
	{
		return this.type;
	}
}
