package problems.crackingthecodinginterview.problem8_1;

public enum StandardType
{
	ACE(1, 11, false),
	TWO(1, false),
	THREE(3, false),
	FOUR(4, false),
	FIVE(5, false),
	SIX(6, false),
	SEVEN(7, false),
	EIGHT(8, false),
	NINE(9, false),
	JACK(10, true),
	QUEEN(10, true),
	KING(10, true);
	
	private final int lowScore;
	private final int highScore;
	private final boolean face;
	
	private StandardType( int low, int high, boolean face )
	{
		this.lowScore = low;
		this.highScore = high;
		this.face = face;
	}
	
	private StandardType( int score, boolean face )
	{
		this(score, score, face);
	}

	public int getLowScore( )
	{
		return this.lowScore;
	}

	public int getHighScore( )
	{
		return this.highScore;
	}
	
	public boolean isFace( )
	{
		return this.face;
	}
}
