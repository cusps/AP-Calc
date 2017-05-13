public class DerivationSolver {
	private String equation;
	private StringBuilder builder;

	public DerivationSolver() {
		builder = new StringBuilder();
	}

	public StringBuilder powerRule(String fx) {
		purgeOldData();
		int count = 0;
		int power = 1;
		String powerString = "";
		int tempBase = 1;
		String tempBaseString = "";
		
		if (fx.charAt(0) == '-')
			builder.append("-");
		
		do {
			// Finds the integer of each term
			if(Character.isDigit(fx.charAt(count)))
				tempBaseString += fx.charAt(count);
			
			// Saves the term's integer and resets the string for the next term
			if(fx.charAt(count) == 'x')
			{
				tempBase = Integer.parseInt(tempBaseString);
				tempBaseString = "";
				
				// Check to see if this is the last term in the sequence!
				// If it is, then just add on the base
				if(count == fx.length() - 1)
					builder.append(tempBase);
			}
			
			// Reads the power of the term
			if(fx.charAt(count) == '^')
			{
				// Advance the count because we want to read the power now
				count++;
				int tempcount = count;
						
				do{
					powerString += fx.charAt(tempcount);
					tempcount++;
				}while(tempcount < fx.length() && Character.isDigit(fx.charAt(tempcount)));
				
				// Catch the count variable "up to speed" with what we just scanned
				for(int i = tempcount; i > count; i--)
					count++;
				
				power = Integer.parseInt(powerString);
				
				//if(power - 1 > 1)
					builder.append((tempBase * power) + "x^" + (power - 1));
				//else
					//builder.append(tempBase * power);
				
				// Reset all data for the next term!!
				power = 1;
				powerString = "";
				tempBase = 1;
				tempBaseString = "";
				
			}
			
			// Check to make sure we aren't at the end of our string, because we only check at the END
			// of the while loop if we are in bounds, but our power checker advances count
			if(count == fx.length())
				break;
			
			// Add in our adding and subtracting signs
			if(fx.charAt(count) == '+')
				builder.append(" + ");
			if(fx.charAt(count) == '-')
				builder.append(" - ");
			
			count++;
		} while (count < fx.length());
		
		// This disaster of an if statement checks if a constant was at the end
		// My program sucks so it takes the derivative of a constant and doesn't even add anything
		// So this removes that last "fragment" thats left, which is either " - " or " + "
		if(builder.substring(builder.length() - 3, builder.length()).equals(" + ") || builder.substring(builder.length() - 3, builder.length()).equals(" - "))
			builder.delete(builder.length() - 3, builder.length());
			
		return builder;
	}

	public StringBuilder productRule(String fx, String gx) 
	{
		purgeOldData();
		builder.append(powerRule(fx) + "(" + gx + ")" + " + " + powerRule(gx) + "(" + fx + ")");
		return builder; //send to be print in frame class; also is printing weirdly rn so we will have to fix this
	}

	public void quotientRule(String fx, String gx) 
	{

	}
	
	public void purgeOldData()
	{
		builder.delete(0, builder.toString().length());
	}

}
