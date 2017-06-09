package problems.crackingthecodinginterview.problem5_8;

import static com.google.common.base.Preconditions.checkArgument;

public class Screen
{
	public static final int BLOCK_SIZE = 8;
	private int width;
	private byte[] data;
	
	public Screen( int width, int height )
	{
		checkArgument(width % 8 == 0, "width must be a multiple of 8");
		checkArgument(height % 8 == 0, "height must be a multiple of 8");
		checkArgument(width > 0, "width must be > 0");
		checkArgument(height > 0, "height must be > 0");
		
		this.width = width;
		
		int size = (width / 8) * height;
		this.data = new byte[size];
	}
	
	public int getWidth( )
	{
		return this.width;
	}
	
	public int getHeight( )
	{
		return ((this.data.length * BLOCK_SIZE) / getWidth());
	}
	
	public void drawHLine( int y, int x1, int x2 )
	{
		checkArgument(y >= 0, "y must be >= 0");
		checkArgument(x1 >= 0, "x1 must be >= 0");
		checkArgument(x2 >= 0, "x2 must be >= 0");
		checkArgument(x2 >= x1, "x2 must be >= x1");
		checkArgument(y < getHeight(), "y must be < getHeight()");
		checkArgument(x1 < getWidth(), "y must be < getHeight()");
		checkArgument(x2 < getWidth(), "y must be < getHeight()");
		
		int startRowBlock = y * (getWidth() / BLOCK_SIZE);
		int startColBlock = startRowBlock + (x1 / BLOCK_SIZE);
		int startOffset = x1 % BLOCK_SIZE;
		int endOffset = x2 % BLOCK_SIZE;
		
		if( (x2 - x1) < (BLOCK_SIZE - startOffset) )
		{ // sub-block line
			fillBlock(startColBlock, startOffset, endOffset);
		}
		else
		{ // multi-block line
			
			// fill first block
			fillBlock(startColBlock, startOffset, (BLOCK_SIZE - 1));
			
			// fill full-blocks
			for( int b=1; b<((x2-x1)/BLOCK_SIZE); b++ )
			{
				int colBlock = startColBlock + b;
				fillBlock(colBlock);
			}
			
			// fill last block
			int endBlock = startRowBlock + (x2 / BLOCK_SIZE);
			fillBlock(endBlock, 0, endOffset);
		}
	}

	protected void fillBlock( int block )
	{
		this.data[block] = (byte) 0xFFFF;
	}
	
	protected void fillBlock( int block, int startOffset, int endOffset )
	{
		checkArgument(endOffset >= startOffset, "endOffset must be >= startOffset");
		checkArgument(endOffset - startOffset < BLOCK_SIZE, "endOffset - startOffset < BLOCK_SIZE");
		
		if( startOffset + endOffset == BLOCK_SIZE - 1 )
		{
			fillBlock(block);
		}
		else
		{
			int bits = (1 << (endOffset - startOffset) + 1) - 1;
			this.data[block] = (byte) (bits << startOffset);
		}
	}
	
	public byte[] getData( )
	{
		return this.data.clone();
	}
	
	public String blockToString( byte block )
	{
		StringBuilder b = new StringBuilder();
		for( int i=0; i<BLOCK_SIZE; i++ )
		{
			char val = ((block & (1 << i)) == 0) ? '.' : 'x';
			b.append(val + " ");
		}
		
		return b.toString();
	}
	
	@Override
	public String toString( )
	{
		StringBuilder b = new StringBuilder();
		b.append("  ");
		for( int x=0; x<getWidth(); x++ )
		{
			b.append(String.format("%2d", x));
		}
		b.append("\n");
		
		for( int y=0; y<getHeight(); y++ )
		{
			b.append(String.format("%2d ", y));
			for( int colBlock=0; colBlock<(getWidth() / BLOCK_SIZE); colBlock++ )
			{
				int block = y * (getWidth() / BLOCK_SIZE) + colBlock;
				b.append(blockToString(this.data[block]));
			}
			b.append("\n");
		}
		
		return b.toString();
	}
}
