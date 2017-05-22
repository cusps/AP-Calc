public class DerivationSolver {
	private String equation;
	private StringBuilder builder;

	public DerivationSolver() {
		builder = new StringBuilder();
	}

	public String powerRule(String fx) {
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
				
				// Actually do the math of power rule
				builder.append((tempBase * power) + "x^" + (power - 1));
								
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
		if(builder.toString().length() > 3)
			if(builder.substring(builder.length() - 3, builder.length()).equals(" + ") || builder.substring(builder.length() - 3, builder.length()).equals(" - "))
				builder.delete(builder.length() - 3, builder.length());
		
		// Purges data and saves our string
		String returnString = builder.toString();
		purgeOldData();
		
		return returnString;
	}

	public String productRule(String fx, String gx) 
	{
		purgeOldData();
		builder.append(powerRule(fx) + "(" + gx + ")" + " + " + powerRule(gx) + "(" + fx + ")");
		return builder.toString();
	}

	public String quotientRule(String fx, String gx) 
	{
		purgeOldData();
		String numerator = ("(" + gx + ") " + powerRule(fx) + " - " + fx + " (" + powerRule(gx) + ")");
		String denominator = "(" + gx + ")" + "^2";
		
		return numerator + "    /    " + denominator;
	}
	
	public void purgeOldData()
	{
		builder.delete(0, builder.toString().length());
	}

}
