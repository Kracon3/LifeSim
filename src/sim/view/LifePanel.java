package sim.view;

import sim.controller.Controller;
import sim.view.SimPanel;
import sim.model.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LifePanel extends JPanel
{
	private Controller app;
	
	private SimPanel simPanel;
	private BufferedImage field;
	private Timer timer;
	
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
		movement(creatures, plants);
		
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
	
	private void movement(Creature[] creatures, Plant[] plants)
	{
		String[] nearestPlantArray = {};
		String[] homeDirectionArray = {};
		
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		
		for(int index = 0; index < plants.length; index++)
		{
			plantList.add(plants[index]);
		}
		
		int creatureNum = creatures.length;
		int plantNum = plants.length;
		
		//timer
		timer = new javax.swing.Timer(1, listener -> 
		{
			step(creatures, plantList, creatureNum, plantNum, nearestPlantArray, homeDirectionArray);

			boolean running = true;
			int creaturesHome = 0;
			
			//checks to see how many creatures are home
			for(int index = 0; index < creatureNum; index++)
			{
				if (creatures[index].getXPosition() == creatures[index].getXHomePosition() && creatures[index].getYPosition() == creatures[index].getYHomePosition())
				{
					creaturesHome++;
					System.out.println("Creature is home");
				}
			}
					
			//if all creatures are home, stop
			if (creaturesHome == creatureNum)
			{
				System.out.println("stop loop");
				running = false;
			}
					
			//reset creaturesHome counter
			System.out.println("reset counter");
			creaturesHome = 0;
			
			if (!running)
			{
				timer.stop();
			}
		});

		timer.stop();
		timer.start();
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
	
	private void step(Creature[] creatures, ArrayList<Plant> plants, int creatureNum, int plantNum, String[] nearestPlantArray, String[] homeDirectionArray)
	{
		pause(10);
		clear();
		
		int nearestPlantIndex = 0;
		
		nearestPlantArray = findNearestPlantDirectionAll(creatures, plants);
		homeDirectionArray = findHomeDirectionAll(creatures);
		System.out.println("found nearest plants");
		
		//changes creature position and draws creatures
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
			for (int plantIndex = 1; plantIndex < plants.size(); plantIndex++)
			{
				if(Math.abs(plants.get(plantIndex).getXPosition() - creatures[index].getXPosition()) < Math.abs(plants.get(nearestPlantIndex).getXPosition() - creatures[index].getXPosition()))
				{
					if(Math.abs(plants.get(plantIndex).getYPosition() - creatures[index].getYPosition()) < Math.abs(plants.get(nearestPlantIndex).getYPosition() - creatures[index].getYPosition()))
					{
						nearestPlantIndex = plantIndex;
					}
				}
			}
			
			//if plant and animal are on the same pixel, creature wants to go home. also removes plant
			if (creatures[index].getXPosition() == plants.get(nearestPlantIndex).getXPosition() && creatures[index].getYPosition() == plants.get(nearestPlantIndex).getYPosition())
			{
				creatures[index].setFindingFood(false);
				creatures[index].eat(plants.get(nearestPlantIndex).getNourishment());
				plants.remove(nearestPlantIndex);
			}

			//Draws Creature
			draw(10, 10, creatures[index].getXPosition(), creatures[index].getYPosition(), Color.BLUE);
		}
	
		//redraws the plants
		for(int index = 0; index < plants.size(); index++)
		{
			System.out.println("draw plant");
			draw(5, 5, plants.get(index).getXPosition(), plants.get(index).getYPosition(), Color.GREEN);
		}
		
		
	}
	
	private String[] findNearestPlantDirectionAll(Creature[] creatures, ArrayList<Plant> plants)
	{
		int creatureNum = creatures.length;
		int plantNum = plants.size() - 1;
		int closestPlantIndex = 0;
		
		String[] creatureToNearestPlant = new String[creatureNum];
		
		for (int creatureIndex = 0; creatureIndex < creatureNum; creatureIndex++)
		{
			creatureToNearestPlant[creatureIndex] = "";
			
			//determines closest plant's index
			for (int plantIndex = 1; plantIndex < plantNum; plantIndex++)
			{
				if(Math.abs(plants.get(plantIndex).getXPosition() - creatures[creatureIndex].getXPosition()) < Math.abs(plants.get(closestPlantIndex).getXPosition() - creatures[creatureIndex].getXPosition()))
				{
					if(Math.abs(plants.get(plantIndex).getYPosition() - creatures[creatureIndex].getYPosition()) < Math.abs(plants.get(closestPlantIndex).getYPosition() - creatures[creatureIndex].getYPosition()))
					{
						closestPlantIndex = plantIndex;
					}
				}
			}
			
			//determines nearest plant's direction compared to creature
			if (plants.get(closestPlantIndex).getYPosition() < creatures[creatureIndex].getYPosition())
			{
				creatureToNearestPlant[creatureIndex] += "up";
			}
			else if (plants.get(closestPlantIndex).getYPosition() > creatures[creatureIndex].getYPosition())
			{
				creatureToNearestPlant[creatureIndex] += "down";
			}
			if (plants.get(closestPlantIndex).getXPosition() < creatures[creatureIndex].getXPosition())
			{
				creatureToNearestPlant[creatureIndex] += "left";
			}
			else if (plants.get(closestPlantIndex).getXPosition() > creatures[creatureIndex].getXPosition())
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
