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
		this.field = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
		
		setupPanel();
	}
	
	private void setupPanel()
	{
		this.setPreferredSize(new Dimension(800, 800));
		this.setBackground(Color.BLACK);
		
	}
	
	public void start()
	{
		clear();
		populate();
	}
	
	private void populate()
	{
		int creatureNum = simPanel.getCreatureNum();
		int plantNum = simPanel.getPlantNum();
		int dietRatio = simPanel.getDietRatio();
		
		Creature[] creatures = new Creature[creatureNum];
		Plant[] plants = new Plant[plantNum];
		
		for(int index = 0; index < creatureNum; index++)
		{
			creatures[index] = new Creature(0, 10, index * 15 + 5);
			draw(10, 10, creatures[index].getXPosition(), creatures[index].getYPosition(), Color.BLUE);
		}
		
		for(int index = 0; index < plantNum; index++)
		{
			plants[index] = new Plant((int) (Math.random()) * 10, (int) (Math.random() * 800), (int) (Math.random() * 800));
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
	
	public void clear()
	{
		this.field = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.drawImage(field,  0,  0,  null);
	}
	
	
}
