
public class Term {

	int power;
	int base;
	boolean negative;
	
	public Term()
	{
		
	}
	
	// Standard "polynomial" term
	public Term(int base, int power, boolean negative)
	{
		this.base = base;
		this.power = power;
		this.negative = negative;
	}
	
	// Standard "polynomial" term
	public void createPolynomialTerm(int base, int power, boolean negative)
	{
		this.base = base;
		this.power = power;
		this.negative = negative;
	}
	
	public void setPower(int power)
	{
		this.power = power;
	}
	
	public void setBase(int base)
	{
		this.base = base;
	}
	
	public void setNegative(boolean negative)
	{
		this.negative = negative;
	}
	
	public int getPower()
	{
		return power;
	}
	
	public int getBase()
	{
		return base;
	}
	
	public boolean isNegative()
	{
		return negative;
	}
	
	public String toString()
	{
		return base + "x" + "^" + power;
	}
}
