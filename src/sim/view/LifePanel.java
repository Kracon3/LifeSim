package sim.view;

import sim.controller.Controller;
import sim.view.SimPanel;
import sim.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
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
			creatures[index] = new Creature(0, 10, index * 10);
		}
		
		for(int index = 0; index < plantNum; index++)
		{
			plants[index] = new Plant((int) (Math.random()) * 10, (int) (Math.random() * 800), (int) (Math.random() * 800));
			
		}
		
	}
	
	
}
