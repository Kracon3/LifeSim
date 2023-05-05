package sim.model;

/*
 * the Creature class holds a lot of unused data like intelligence, diet, scariness, and speed.
 * the data that is actually used consists of position, home position, and hunger 
 */
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
	
	/*
	 * this unused constructor initializes all data
	 */
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
	
	/*
	 * this constructor is used and is much simpler
	 */
	public Creature(int fullness,  int xPosition, int yPosition, boolean findingFood, boolean reproducable)
	{
		this.fullness = fullness;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.xHomePosition = xPosition;
		this.yHomePosition = yPosition;
		this.findingFood = findingFood;
		this.reproducable = reproducable;
	}
	
	/*
	 * sets speed
	 */
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	/*
	 * sets intelligence
	 */
	public void setIntelligence(int intelligence)
	{
		this.intelligence = intelligence;
	}
	
	/*
	 * sets scariness
	 */
	public void setScariness(int scariness)
	{
		this.scariness = scariness;
	}
	
	/*
	 * sets diet
	 */
	public void setIsCarnivore(boolean isCarnivorous)
	{
		this.eatsMeat = isCarnivorous;
	}
	
	/*
	 * adds nourishment from a food source into fullness
	 */
	public void eat(int nourishment)
	{
		this.fullness += nourishment;
	}
	
	/*
	 * decides if a creature is still finding food
	 */
	public void setFindingFood(boolean findingFood)
	{
		this.findingFood = findingFood;
	}
	
	/*
	 * sets reproducable
	 */
	public void setReproducable(boolean reproducable)
	{
		this.reproducable = reproducable;
	}
	
	/*
	 * adds to the creature's current x and y position
	 */
	public void move(int x, int y)
	{
		xPosition += x;
		yPosition += y;
	}
	
	/*
	 * gets speed
	 */
	public int getSpeed()
	{
		return speed;
	}
	
	/*
	 * gets intelligence
	 */
	public int getIntelligence()
	{
		return intelligence;
	}
	
	/*
	 * gets scariness
	 */
	public int getScariness()
	{
		return scariness;
	}
	
	/*
	 * gets diet
	 */
	public boolean getIsCarnivore()
	{
		return eatsMeat;
	}
	
	/*
	 * gets fullness
	 */
	public int getFullness()
	{
		return fullness;
	}
	
	/*
	 * gets findingFood
	 */
	public boolean isFindingFood()
	{
		return findingFood;
	}
	
	/*
	 * gets reproducable
	 */
	public boolean isReproducable()
	{
		return reproducable;
	}
	
	/*
	 * gets xPosition
	 */
	public int getXPosition()
	{
		return xPosition;
	}
	
	/*
	 * gets yPosition
	 */
	public int getYPosition()
	{
		return yPosition;
	}
	
	/*
	 * gets the x position of the creature's home
	 */
	public int getXHomePosition()
	{
		return xHomePosition;
	}
	
	/*
	 * gets the y position of the creature's home
	 */
	public int getYHomePosition()
	{
		return yHomePosition;
	}
	
}
