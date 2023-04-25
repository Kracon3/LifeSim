package sim.model;

public class Creature
{
	private int speed;
	private int intelligence;
	private int scariness;
	private int fullness;
	private int nourishment;
	private boolean eatsMeat;
	private int xPosition;
	private int yPosition;
	
	public Creature(int speed, int intelligence, int scariness, int fullness, int nourishment, boolean eatsMeat, int xPosition, int yPosition)
	{
		this.speed = speed;
		this.intelligence = intelligence;
		this.scariness = scariness;
		this.fullness = fullness;
		this.eatsMeat = eatsMeat;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public Creature(int fullness,  int xPosition, int yPosition)
	{
		this.fullness = fullness;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	public void setIntelligence(int intelligence)
	{
		this.intelligence = intelligence;
	}
	
	public void setScariness(int scariness)
	{
		this.scariness = scariness;
	}
	
	public void setIsCarnivore(boolean isCarnivorous)
	{
		this.eatsMeat = isCarnivorous;
	}
	
	public void consume(int nourishment)
	{
		this.fullness += nourishment;
	}
	
	public void move(int x, int y)
	{
		xPosition += x;
		yPosition += y;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public int getIntelligence()
	{
		return intelligence;
	}
	
	public int getScariness()
	{
		return scariness;
	}
	
	public boolean getIsCarinvore()
	{
		return eatsMeat;
	}
	
	public int getFullness()
	{
		return fullness;
	}
	
	public int[] getPosition()
	{
		int[] position = {xPosition, yPosition};
		return position;
	}
	
}
