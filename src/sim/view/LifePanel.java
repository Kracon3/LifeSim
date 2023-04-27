package sim.view;

import sim.controller.Controller;
import sim.view.SimPanel;
import sim.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LifePanel extends JPanel
{
	private Controller app;
	
	private SimPanel simPanel;
	private BufferedImage field;
	
	public LifePanel(Controller app, SimPanel simPanel)
	{
		super();
		
		this.app = app;
		this.simPanel = simPanel;
		this.field = new BufferedImage(910, 910, BufferedImage.TYPE_INT_ARGB);
		
		setupPanel();
	}
	
	private void setupPanel()
	{
		this.setPreferredSize(new Dimension(910, 910));
		this.setBackground(Color.BLACK);
		
	}
	
	public void start()
	{
		clear();
		
		//Population Phase
		int creatureNum = simPanel.getCreatureNum();
		int plantNum = simPanel.getPlantNum();
		int dietRatio = simPanel.getDietRatio();
		
		Creature[] creatures = new Creature[creatureNum];
		Plant[] plants = new Plant[plantNum];
		
		populate(creatures, plants, creatureNum, plantNum);
		
		//Movement Phase
		movement(true, creatures, plants);
		
	}
	
	private void populate(Creature[] creatures, Plant[] plants, int creatureNum, int plantNum)
	{
		boolean canReproduce = false;
		
		for(int index = 0; index < creatureNum; index++)
		{
			//assigns canReproduce randomly
			if ((int) (Math.random()) == 1)
			{
				canReproduce = true;
			}
			
			creatures[index] = new Creature(0, 10, index * 15 + 5, true, canReproduce);
			draw(10, 10, creatures[index].getXPosition(), creatures[index].getYPosition(), Color.BLUE);
		}
		
		for(int index = 0; index < plantNum; index++)
		{
			plants[index] = new Plant((int) (Math.random()) * 10, (int) (Math.random() * 880 + 25), (int) (Math.random() * 900 + 5));
			draw(5, 5, plants[index].getXPosition(), plants[index].getYPosition(), Color.GREEN);
		}
	}
	
	private void draw(int width, int height, int x, int y, Color color)
	{
		Graphics2D myGraphics = field.createGraphics();
		
		myGraphics.setColor(color);
		myGraphics.drawRect(x, y, width, height);
		
		repaint();
	}
	
	private void movement(boolean running, Creature[] creatures, Plant[] plants)
	{
		int creaturesHome = 0;
		
		String[] nearestPlantArray;
		String[] homeDirectionArray;
		
		int creatureNum = creatures.length;
		int plantNum = plants.length;
		
		//loop
		while (running)
		{
			pause(10);
			clear();
			
			int nearestPlantIndex = 0;
			
			nearestPlantArray = findNearestPlantDirectionAll(creatures, plants);
			homeDirectionArray = findHomeDirectionAll(creatures);
			System.out.println("made arrays");
			
			
			for(int index = 0; index < creatureNum; index++)
			{
				if (creatures[index].isFindingFood())
				{
					if (nearestPlantArray[index].contains("up"))
					{
						creatures[index].move(0, -1);
					}
					if (nearestPlantArray[index].contains("left"))
					{
						creatures[index].move(-1, 0);
					}
					if (nearestPlantArray[index].contains("down"))
					{
						creatures[index].move(0, 1);
					}
					if (nearestPlantArray[index].contains("right"))
					{
						creatures[index].move(1, 0);
					}
				}
				
				else
				{
					if (homeDirectionArray[index].contains("up"))
					{
						creatures[index].move(0, -1);
					}
					if (homeDirectionArray[index].contains("left"))
					{
						creatures[index].move(-1, 0);
					}
					if (homeDirectionArray[index].contains("down"))
					{
						creatures[index].move(0, 1);
					}
					if (homeDirectionArray[index].contains("right"))
					{
						creatures[index].move(1, 0);
					}
					
				}
				
				//finds plant closest to creature
				for (int plantIndex = 1; plantIndex < plantNum; plantIndex++)
				{
					if(Math.abs(plants[plantIndex].getXPosition() - creatures[index].getXPosition()) < Math.abs(plants[nearestPlantIndex].getXPosition() - creatures[index].getXPosition()))
					{
						if(Math.abs(plants[plantIndex].getYPosition() - creatures[index].getYPosition()) < Math.abs(plants[nearestPlantIndex].getYPosition() - creatures[index].getYPosition()))
						{
							nearestPlantIndex = plantIndex;
						}
					}
				}
				
				//if plant and animal are on the same pixel, creature wants to go home
				if (creatures[index].getXPosition() == plants[nearestPlantIndex].getXPosition() && creatures[index].getYPosition() == plants[nearestPlantIndex].getYPosition())
				{
					creatures[index].setFindingFood(false);
				}

				draw(10, 10, creatures[index].getXPosition(), creatures[index].getYPosition(), Color.BLUE);
			}
		
			//redraws the plants
			for(int index = 0; index < plantNum; index++)
			{
				System.out.println("draw plant");
				draw(5, 5, plants[index].getXPosition(), plants[index].getYPosition(), Color.GREEN);
			}
			
			//checks to see how many creatures are home
			for(int index = 0; index < creatureNum; index++)
			{
				if (creatures[index].getXPosition() == creatures[index].getXHomePosition() && creatures[index].getYPosition() == creatures[index].getYHomePosition())
				{
					creaturesHome++;
					System.out.println("update count");
				}
			}
			
			//if all creatures are home, stop
			if (creaturesHome == creatureNum)
			{
				System.out.println("stop loop");
				running = false;
			}
			
			System.out.println("reset home");
			creaturesHome = 0;
		}
	}
	
	private void clear()
	{
		this.field = new BufferedImage(910, 910, BufferedImage.TYPE_INT_ARGB);
		repaint();
	}
	
	private void pause(int milliseconds)
	  {
	    try
	    {
	      Thread.sleep(milliseconds);
	    }
	    catch(InterruptedException e)
	    {
	      throw new RuntimeException(e);
	    }
	  }
	
	private String[] findNearestPlantDirectionAll(Creature[] creatures, Plant[] plants)
	{
		int creatureNum = simPanel.getCreatureNum();
		int plantNum = simPanel.getPlantNum();
		int closestPlantIndex = 0;
		
		String[] creatureToNearestPlant = new String[creatureNum];
		
		for (int creatureIndex = 0; creatureIndex < creatureNum; creatureIndex++)
		{
			creatureToNearestPlant[creatureIndex] = "";
			for (int plantIndex = 1; plantIndex < plantNum; plantIndex++)
			{
				if(Math.abs(plants[plantIndex].getXPosition() - creatures[creatureIndex].getXPosition()) < Math.abs(plants[closestPlantIndex].getXPosition() - creatures[creatureIndex].getXPosition()))
				{
					if(Math.abs(plants[plantIndex].getYPosition() - creatures[creatureIndex].getYPosition()) < Math.abs(plants[closestPlantIndex].getYPosition() - creatures[creatureIndex].getYPosition()))
					{
						closestPlantIndex = plantIndex;
					}
				}
			}
			
			if (plants[closestPlantIndex].getYPosition() < creatures[creatureIndex].getYPosition())
			{
				creatureToNearestPlant[creatureIndex] += "up";
			}
			else if (plants[closestPlantIndex].getYPosition() > creatures[creatureIndex].getYPosition())
			{
				creatureToNearestPlant[creatureIndex] += "down";
			}
			if (plants[closestPlantIndex].getXPosition() < creatures[creatureIndex].getXPosition())
			{
				creatureToNearestPlant[creatureIndex] += "left";
			}
			else if (plants[closestPlantIndex].getXPosition() > creatures[creatureIndex].getXPosition())
			{
				creatureToNearestPlant[creatureIndex] += "right";
			}
		}
		
		return creatureToNearestPlant;
	}
	
	private String[] findHomeDirectionAll(Creature[] creatures)
	{
		int creatureNum = simPanel.getCreatureNum();
		String[] creatureToHome = new String[creatureNum];
		
		for (int index = 0; index < creatureNum; index++)
		{
			creatureToHome[index] = "";
			
			if (creatures[index].getYHomePosition() < creatures[index].getYPosition())
			{
				creatureToHome[index] += "up";
			}
			else if (creatures[index].getYHomePosition() > creatures[index].getYPosition())
			{
				creatureToHome[index] += "down";
			}
			if (creatures[index].getXHomePosition() < creatures[index].getXPosition())
			{
				creatureToHome[index] += "left";
			}
			if (creatures[index].getXHomePosition() > creatures[index].getXPosition())
			{
				creatureToHome[index] += "right";
			}
		}
		
		return creatureToHome;
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.drawImage(field,  0,  0,  null);
	}
	
	
}
