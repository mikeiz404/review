package problems.crackingthecodinginterview.problem4_9;

import org.mockito.Mockito;

public class TestRevPathSumPrinter extends TestPathSumPrinter
{
	private Printer printer;
	
	public PathSumPrinter getMakeInstance( )
	{
		this.printer = Mockito.mock(Printer.class);
		return  new RevPathSumPrinter(getPrinter());
	}

	@Override
	public Printer getPrinter( )
	{
		return this.printer;
	}
}
