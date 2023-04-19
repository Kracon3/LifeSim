package sim.model;

public class Creature
{
	public int speed;
	public int intelligence;
	public int scariness;
	public boolean eatsMeat;
	public int xPosition;
	public int yPosition;
	
	public Creature(int speed, int intelligence, int scariness, boolean eatsMeat)
	{
		this.speed = speed;
		this.intelligence = intelligence;
		this.scariness = scariness;
		this.eatsMeat = eatsMeat;
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
	
	public int[] getPosition()
	{
		int[] position = {xPosition, yPosition};
		return position;
	}
	
}
