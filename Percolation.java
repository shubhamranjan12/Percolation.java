import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	final private WeightedQuickUnionUF uf;
	final private int top;
	final private int bottom;
	private boolean []site;
	final private int size;
	private int openSites;
	
	public Percolation(int n)
	{
		
		size = n;
		if (size <= 0)
			throw new IllegalArgumentException();
		
		uf = new WeightedQuickUnionUF(n * n + 2);
		top = n * n;
		bottom = n * n + 1;
		site = new boolean[size * size];
		
		
	}
	
	private  boolean checkerbounds(final int i, final int j)
	{
		 
		if (i-1 < 0 || i-1 > size || j-1 < 0 || j-1 > size || size <= 0 || j-1 == size || i-1 == size)
		{
			throw new IllegalArgumentException();
		}
		return true;
	}
	
	public void open(int i, int j)
	
	{

		checkerbounds(i, j);
		
		int co = convert2dTo1dCoOrd(i, j);
		
		
		if (!isOpen(i, j))
			numberOfOpenSites();
		site[co] = true;
		// connect to right
		if (j < size) {
			
		 if (site[co+1])
			uf.union(co, convert2dTo1dCoOrd(i, j+1));
		}
		// connect to left
		if (j > 1)
		{	
		if (site[co-1])
			uf.union(co, convert2dTo1dCoOrd(i, j-1));
		}
		// connect to top
		if (i > 1) {
		if (site[co-size])
			uf.union(co, convert2dTo1dCoOrd(i-1, j));
		}
		// connect to bottom
		if (i < size)
		{
		if (site[co+size])
			uf.union(co, convert2dTo1dCoOrd(i+1, j));
		}	
		// connect to top virtual
		
		if (i == 1)
		{
			
				uf.union(co, top);
		}
		 if (i == size)
			uf.union(co, bottom);	
					
	}
	
	public boolean isOpen(int i, int j)
	{
		checkerbounds(i, j);
		
		return site[convert2dTo1dCoOrd(i, j)];
			
		
	}
	public boolean isFull(int i, int j)
	{
		checkerbounds(i, j);
		
		checkerbounds(i, j); 
        return uf.connected(convert2dTo1dCoOrd(i, j) ,top);
	}
	
	public int numberOfOpenSites()
	{
		int count =0;
		for(int z=0;z<site.length;++z)
			if(site[z])
				++count;
			
		return count;
	}
	 
	public boolean percolates()
	{
		if (uf.connected(top, bottom))
			return true;
		return false;
		
	}
	
	private  int convert2dTo1dCoOrd(int i, int j)
    {
		
        int pos = (i-1) * size + (j-1);
        return pos;
    }
	
}
