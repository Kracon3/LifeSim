package sim.model;

/*
 * The Plant class stores position data and the amount of nourishment each plant is worth
 */
public class Plant
{
	private int nourishment;
	private int xPosition;
	private int yPosition;
	
	/*
	 * initializes nourishment, as well as x and y position
	 */
	public Plant(int nourishment, int xPosition, int yPosition)
	{
		this.nourishment = nourishment;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	/*
	 * returns nourishment value as an int
	 */
	public int getNourishment()
	{
		return nourishment;
	}
	
	/*
	 * returns xPosition as an int
	 */
	public int getXPosition()
	{
		return xPosition;
	}
	
	/*
	 * returns yPosition as an int
	 */
	public int getYPosition()
	{
		return yPosition;
	}

}
