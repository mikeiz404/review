package problems.crackingthecodinginterview.problem2_5;

import java.util.LinkedList;
import javax.validation.constraints.NotNull;

public interface LLSummerInterface
{
	public LinkedList<Integer> sum( @NotNull LinkedList<Integer> a, @NotNull LinkedList<Integer> b );
}
