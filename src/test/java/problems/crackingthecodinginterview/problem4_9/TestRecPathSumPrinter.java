package problems.crackingthecodinginterview.problem4_9;

import org.mockito.Mockito;

public class TestRecPathSumPrinter extends TestPathSumPrinter
{
	private Printer pathPrinter;
	
	public RecPathSumPrinter getMakeInstance( )
	{
		this.pathPrinter = Mockito.mock(Printer.class);
		return new RecPathSumPrinter(getPrinter());
	}
	
	public Printer getPrinter( )
	{
		return this.pathPrinter;
	}
}
