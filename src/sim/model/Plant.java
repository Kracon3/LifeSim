package sim.model;

public class Plant
{
	private int nourishment;
	private int xPosition;
	private int yPosition;
	
	public Plant(int nourishment, int xPosition, int yPosition)
	{
		this.nourishment = nourishment;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public int getNourishment()
	{
		return nourishment;
	}
	
	public int getXPosition()
	{
		return xPosition;
	}
	public int getYPosition()
	{
		return yPosition;
	}

}
