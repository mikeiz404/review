package problems.interviews.company2.position0.problem1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution1
{
	public static void main( String[] args )
	throws FileNotFoundException
	{
		if( args.length != 1 )
		{ // invalid args
			System.err.println(String.format("java %s file_path", Solution1.class.getName()));
			
			System.exit(1);
		}
		else // args.length == 1
		{ // valid args
			
			// open file
			String path = args[0];
			File file = new File(path);
			
			// calc frequencies
			Map<String, Integer> freqMap = calcWordFrequencies(new FileInputStream(file));
			
			// print map
			for( String word : freqMap.keySet() )
			{
				int count = freqMap.get(word);
				System.out.println(String.format("%d %s", count, word));
			}
		}
		
	}
	
	public static Map<String, Integer> calcWordFrequencies( InputStream stream )
	{
		HashMap<String, Integer> freqMap = new HashMap<>();
		
		try( Scanner scanner = new Scanner(stream) )
		{
    		while( scanner.hasNext() )
    		{
    			String word = scanner.next().toLowerCase();
    			
    			int count;
    			if( freqMap.containsKey(word) )
    			{
    				count = freqMap.get(word);
    			}
    			else // !freqMap.containsKey(word)
    			{ // first occurrence
    				count = 0;
    			}
    			
    			count++;
    			
    			freqMap.put(word, count);
    		}
		}
		
		return freqMap;
	}
}
