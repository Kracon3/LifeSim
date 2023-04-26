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
		boolean running = true;
		int creaturesHome = 0;
		
		clear();
		
		//Population Phase
		int creatureNum = simPanel.getCreatureNum();
		int plantNum = simPanel.getPlantNum();
		int dietRatio = simPanel.getDietRatio();
		boolean canReproduce = false;
		
		Creature[] creatures = new Creature[creatureNum];
		Plant[] plants = new Plant[plantNum];
		
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
		
		//Movement Phase
		String[] nearestPlantArray;
		String[] homeDirectionArray;
		
		while (running)
		{
			try 
			{
				Thread.sleep(1000);
			} 
			
			catch (InterruptedException exception) 
			{
				exception.printStackTrace();
			}
			
			clear();
			
			nearestPlantArray = findNearestPlantDirectionAll(creatures, plants);
			homeDirectionArray = findNearestPlantDirectionAll(creatures, plants);
			for(int index = 0; index < creatureNum; index++)
			{
				if (creatures[index].isFindingFood())
				{
					findPlants(index, creatures,  nearestPlantArray);
				}
				
				else
				{
					findHome(index, creatures, homeDirectionArray);
				}
			
				draw(10, 10, creatures[index].getXPosition(), creatures[index].getYPosition(), Color.BLUE);
			}
		
			//redraws the plants
			for(int index = 0; index < plantNum; index++)
			{
				draw(5, 5, plants[index].getXPosition(), plants[index].getYPosition(), Color.GREEN);
			}
			
			//checks to see how many creatures are home
			for(int index = 0; index < creatureNum; index++)
			{
				if (creatures[index].getXPosition() == creatures[index].getXHomePosition() && creatures[index].getYPosition() == creatures[index].getYHomePosition())
				{
					creaturesHome++;
				}
			}
			
			//if all creatures are home, stop
			if (creaturesHome == creatureNum)
			{
				running = false;
			}
			
			creaturesHome = 0;
		}
	}
	
	private void draw(int width, int height, int x, int y, Color color)
	{
		Graphics2D myGraphics = field.createGraphics();
		
		myGraphics.setColor(color);
		myGraphics.drawRect(x, y, width, height);
		
		repaint();
	}
	
	public void clear()
	{
		this.field = new BufferedImage(910, 910, BufferedImage.TYPE_INT_ARGB);
		repaint();
	}
	
	public void findPlants(int index, Creature[] creatures, String[] nearestPlantArray)
	{
		if (nearestPlantArray[index] == "up")
		{
			creatures[index].move(0, -1);
		}
		else if (nearestPlantArray[index] == "left")
		{
			creatures[index].move(-1, 0);
		}
		else if (nearestPlantArray[index] == "down")
		{
			creatures[index].move(0, 1);
		}
		else if (nearestPlantArray[index] == "right")
		{
			creatures[index].move(1, 0);
		}
		else if (nearestPlantArray[index] == "upleft")
		{
			creatures[index].move(-1, -1);
		}
		else if (nearestPlantArray[index] == "upright")
		{
			creatures[index].move(-1, 1);
		}
		else if (nearestPlantArray[index] == "downleft")
		{
			creatures[index].move(1, -1);
		}
		else if (nearestPlantArray[index] == "downright")
		{
			creatures[index].move(1, 1);
		}
	}
	
	public void findHome(int index, Creature[] creatures, String[] homeDirectionArray)
	{
		if (homeDirectionArray[index] == "up")
		{
			creatures[index].move(0, -1);
		}
		else if (homeDirectionArray[index] == "left")
		{
			creatures[index].move(-1, 0);
		}
		else if (homeDirectionArray[index] == "down")
		{
			creatures[index].move(0, 1);
		}
		else if (homeDirectionArray[index] == "right")
		{
			creatures[index].move(1, 0);
		}
		else if (homeDirectionArray[index] == "upleft")
		{
			creatures[index].move(-1, -1);
		}
		else if (homeDirectionArray[index] == "upright")
		{
			creatures[index].move(-1, 1);
		}
		else if (homeDirectionArray[index] == "downleft")
		{
			creatures[index].move(1, -1);
		}
		else if (homeDirectionArray[index] == "downright")
		{
			creatures[index].move(1, 1);
		}
	}
	
	public String[] findNearestPlantDirectionAll(Creature[] creatures, Plant[] plants)
	{
		int creatureNum = simPanel.getCreatureNum();
		int plantNum = simPanel.getPlantNum();
		int closestPlantIndex = 0;
		
		String[] creatureToNearestPlant = new String[creatureNum];
		
		for (int creatureIndex = 0; creatureIndex < creatureNum; creatureIndex++)
		{
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
			if (plants[closestPlantIndex].getXPosition() > creatures[creatureIndex].getXPosition())
			{
				creatureToNearestPlant[creatureIndex] += "right";
			}
		}
		
		return creatureToNearestPlant;
	}
	
	public String[] findHomeDirectionAll(Creature[] creatures)
	{
		int creatureNum = simPanel.getCreatureNum();
		String[] creatureToHome = new String[creatureNum];
		
		for (int index = 0; index < creatureNum; index++)
		{
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
