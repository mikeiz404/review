package problems.crackingthecodinginterview.problem8_1.blackjack;

import java.util.HashSet;
import java.util.Set;
import problems.crackingthecodinginterview.problem8_1.Hand;
import problems.crackingthecodinginterview.problem8_1.StandardType;

public class BjHand extends Hand<BjCard>
{
	public final int SCORE_MAX=21;
	
	public Set<StandardType> getSplitTypes( )
	{
		HashSet<StandardType> types = new HashSet<>();
		HashSet<StandardType> splitTypes = new HashSet<>();
		
		for( BjCard card : getCards() )
		{
			StandardType type = card.getType();
			if( types.contains(type) )
			{
				splitTypes.add(type);
			}
		}
		
		return splitTypes;
	}
	
	public boolean canSplit( )
	{
		return getSplitTypes().size() != 0;
	}
	
	public BjHand split( BjCard card )
	{
		discard(card);
		
		BjHand otherHand = new BjHand();
		otherHand.takeCard(card);
		
		return otherHand;
	}
	
	public int lowScore( )
	{
		int sum = 0;
		for( BjCard card : getCards() )
		{
			sum += card.lowScore();
		}
		
		return sum;
	}
	
	public int highScore( )
	{
		int sum = 0;
		for( BjCard card : getCards() )
		{
			sum += card.highScore();
		}
		
		return sum;
	}
	
	public int bestScore( )
	{
		int highScore = highScore();
		if( highScore > SCORE_MAX )
		{
			return lowScore();
		}
		else
		{
			return highScore;
		}
	}
	
	public boolean isBusted( )
	{
		return bestScore() > SCORE_MAX;
	}
	
	public boolean isBlackJack( )
	{
		return bestScore() == SCORE_MAX;
	}
}
