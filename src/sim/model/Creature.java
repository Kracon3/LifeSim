package sim.model;

public class Creature
{
	private int speed;
	private int intelligence;
	private int scariness;
	private int fullness;
	private int nourishment;
	private boolean eatsMeat;
	private boolean findingFood;
	private boolean reproducable;
	private int xPosition;
	private int yPosition;
	private int xHomePosition;
	private int yHomePosition;
	
	public Creature(int speed, int intelligence, int scariness, int fullness, int nourishment, boolean eatsMeat, int xPosition, int yPosition, boolean findingFood, boolean reproducable)
	{
		this.speed = speed;
		this.intelligence = intelligence;
		this.scariness = scariness;
		this.fullness = fullness;
		this.eatsMeat = eatsMeat;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.xHomePosition = xPosition;
		this.yHomePosition = yPosition;
		this.findingFood = findingFood;
		this.reproducable = reproducable;
	}
	
	public Creature(int fullness,  int xPosition, int yPosition, boolean findingFood, boolean reproducable)
	{
		this.fullness = fullness;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.findingFood = findingFood;
		this.reproducable = reproducable;
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
	
	public void eat(int nourishment)
	{
		this.fullness += nourishment;
	}
	
	public void setFindingFood(boolean findingFood)
	{
		this.findingFood = findingFood;
	}
	public void setReproducable(boolean reproducable)
	{
		this.reproducable = reproducable;
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
	
	public boolean getIsCarnivore()
	{
		return eatsMeat;
	}
	
	public int getFullness()
	{
		return fullness;
	}
	
	public boolean isFindingFood()
	{
		return findingFood;
	}
	public boolean isReproducable()
	{
		return reproducable;
	}
	
	public int getXPosition()
	{
		return xPosition;
	}
	
	public int getYPosition()
	{
		return yPosition;
	}
	
	public int getXHomePosition()
	{
		return xHomePosition;
	}
	
	public int getYHomePosition()
	{
		return yHomePosition;
	}
	
}
