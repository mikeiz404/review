package problems.crackingthecodinginterview.problem9_4;

import java.util.Collection;
import java.util.Set;

public interface TestInterface
{
	public <T> Set<Set<T>> makeAllSubsets( Collection<T> items );
}
