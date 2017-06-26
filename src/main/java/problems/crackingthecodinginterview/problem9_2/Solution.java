package problems.crackingthecodinginterview.problem9_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.google.common.collect.Lists;

public class Solution
{
	private static final Point MOVE_RIGHT = new Point(1, 0);
	private static final Point MOVE_DOWN = new Point(0, 1);
	private static final Point ORIGIN = new Point(0, 0);
	
	/**
	 * Note: It is possible to do this mathematically. A path will be x+y steps long where `x - 0` right steps and `y - 0` 
	 * down step are taken. # paths = (x + y)! / (x!y!). See book for explanation.
	 * 
	 * Approach: Recursively take each possible sub-path from the current position. A path is complete and possible when
	 * the destination is reached. A path is impossible when either x or y exceed the destination x or y since moves cannot
	 * be "undone".
	 * 
	 * Time: O(xy) where `x = dstX - 0` and `y = dstY - 0`.
	 * Space: O(xy) to store cached results.
	 * @param dstX destination x.
	 * @param dstY destination y.
	 * @return the number of unique paths.
	 */
	public static int computePathCount( int dstX, int dstY )
	{
		return computePathCountRec(new int[dstX + 1][dstY + 1], dstX, dstY, 0, 0);
	}
	
	protected static int computePathCountRec( int[][] cache, int dstX, int dstY, int x, int y )
	{
		if( x > dstX || y > dstY )
		{ // base
			return 0;
		}
		else if( x == dstX && y == dstY )
		{ // base
			return 1;
		}
		else
		{ // recursive
			if( cache[x][y] != 0 )
			{ // cached
				return cache[x][y];
			}
			else
			{ // compute
				return computePathCountRec(cache, dstX, dstY, x + 1, y) + computePathCountRec(cache, dstX, dstY, x, y + 1);
			}
		}
	}
	
	/**
	 * Approach: Similar to computePathCount except no memoization is necessary and when the destination is reached, a list
	 * of points is returned. If a point is blocked, treat it like an out of bounds point and return null. When recursing
	 * the return results need to be checked to determine if we can end early and update the list with our current point
	 * in the path.
	 * 
	 * Time: O(xy).
	 * Space: O(xy) for blocked set max size.
	 * 
	 * @param blocked a set of blocked points.
	 * @param dst
	 * @return a list of points from (0, 0) to dst if possible, null otherwise
	 */
	public static List<Point> findAPath( Set<Point> blocked, Point dst )
	{
		List<Point> path = findAPath(blocked, dst, ORIGIN);
		
		if( path != null )
		{
			return Lists.reverse(path);
		}
		else
		{
			return null;
		}
	}
	
	protected static List<Point> findAPath( Set<Point> blocked, Point dst, Point src )
	{
		if( src.getX() > dst.getX() || src.getY() > dst.getY() )
		{ // base
			return null;
		}
		else if( blocked.contains(src) )
		{ // base
			return null;
		}
		else if( src.equals(dst) )
		{ // base
			ArrayList<Point> path = new ArrayList<>();
			path.add(dst);
			
			return path;
		}
		else
		{ // recursive
			List<Point> rightPath = findAPath(blocked, dst, src.add(MOVE_RIGHT));
			if( rightPath != null )
			{
				rightPath.add(src);
				return rightPath;
			}
			
			List<Point> downPath = findAPath(blocked, dst, src.add(MOVE_DOWN));
			if( downPath != null )
			{
				downPath.add(src);
				return downPath;
			}
			
			// note: there are no valid moves from this point which lead to the destination.
			// note: only failed paths are being marked since a valid path would have returned a list so there is no need to mark it as visited.
			blocked.add(src);
			return null;
		}
	}
}
