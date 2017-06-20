package problems.crackingthecodinginterview.problem8_1.blackjack;

import problems.crackingthecodinginterview.problem8_1.StandardCard;
import problems.crackingthecodinginterview.problem8_1.StandardColor;
import problems.crackingthecodinginterview.problem8_1.StandardSuit;
import problems.crackingthecodinginterview.problem8_1.StandardType;

public class BjCard extends StandardCard
{
	public BjCard( StandardColor color, StandardSuit suit, StandardType type )
	{
		super(color, suit, type);
	}
	
	public int lowScore( )
	{
		return getType().getLowScore();
	}
	
	public int highScore( )
	{
		return getType().getHighScore();
	}

}
