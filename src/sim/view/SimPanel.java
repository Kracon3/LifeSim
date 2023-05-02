package sim.view;

import sim.controller.Controller;
import sim.view.LifePanel;
import java.util.Hashtable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class SimPanel extends JPanel
{
	//data members
	private Controller app;
	private SpringLayout layout;
	private LifePanel panel;
	
	private JPanel buttonPanel;
	private JLabel numSliderLabel;
	private JLabel dietSliderLabel;
	private JLabel plantSliderLabel;
	
	private JButton startButton;
	
	private JSlider numSlider;
	private JSlider plantSlider;
	private final int MINIMUM_NUM = 0;
	private final int MAXIMUM_NUM = 60;
	private int creatureNum = 60;
	private int plantNum = 60;
	
	private JSlider dietSlider;
	private final int MINIMUM_RATIO = 1;
	private final int MAXIMUM_RATIO = 100;
	private int dietRatio = 50;
	
	//constructor
	public SimPanel(Controller App)
	{
		super();
		
		this.app = app;
		this.layout = new SpringLayout();
		this.panel = new LifePanel(app, this);
		
		this.buttonPanel = new JPanel(new GridLayout(0, 1));
		this.numSliderLabel = new JLabel("Number of animals");
		this.dietSliderLabel = new JLabel("Herbivorous : Carnivorous");
		this.plantSliderLabel = new JLabel("Number of plants");
		
		this.startButton = new JButton("Start");
		
		this.numSlider = new JSlider(MINIMUM_NUM, MAXIMUM_NUM, creatureNum);
		this.dietSlider = new JSlider(MINIMUM_RATIO, MAXIMUM_RATIO, dietRatio);
		this.plantSlider = new JSlider(MINIMUM_NUM, MAXIMUM_NUM * 2, plantNum);
		
		setupSliders();
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	//panel setup methods
	private void setupPanel()
	{
		this.setLayout(layout);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(1200, 938));
		
		this.add(buttonPanel);
		buttonPanel.add(numSliderLabel);
		buttonPanel.add(numSlider);
		buttonPanel.add(plantSliderLabel);
		buttonPanel.add(plantSlider);
		
		this.add(startButton);
		
		this.add(panel);
	}
	
	private void setupListeners()
	{
		numSlider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if (!numSlider.getValueIsAdjusting())
				{
					creatureNum = numSlider.getValue();
				}
			}
		});
		
		dietSlider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if (!dietSlider.getValueIsAdjusting())
				{
					dietRatio = dietSlider.getValue();
				}
			}
		});
		
		plantSlider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if (!plantSlider.getValueIsAdjusting())
				{
					plantNum = plantSlider.getValue();
				}
			}
		});
		
		startButton.addActionListener(click -> panel.start());
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, startButton, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, startButton, -50, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.SOUTH, startButton, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.NORTH, startButton, -50, SpringLayout.SOUTH, this);
	
		layout.putConstraint(SpringLayout.NORTH, buttonPanel, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, buttonPanel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, buttonPanel, -25, SpringLayout.WEST, panel);
	}
	
	private void setupSliders()
	{
		//numSlider setup
		Hashtable<Integer, JLabel> numLabels = new Hashtable<Integer, JLabel>();

		numSlider.setLabelTable(numLabels);
		numSlider.setSnapToTicks(true);
		numSlider.setMajorTickSpacing(3);
		numSlider.setMinorTickSpacing(1);
		numSlider.setPaintTicks(true);
		
		//dietSlider setup
		Hashtable<Integer, JLabel> dietLabels = new Hashtable<Integer, JLabel>();

		dietSlider.setLabelTable(dietLabels);
		dietSlider.setSnapToTicks(true);
		dietSlider.setMajorTickSpacing(3);
		dietSlider.setMinorTickSpacing(1);
		dietSlider.setPaintTicks(true);
		
		//plantSlider setup
		Hashtable<Integer, JLabel> plantLabels = new Hashtable<Integer, JLabel>();

		plantSlider.setLabelTable(plantLabels);
		plantSlider.setSnapToTicks(true);
		plantSlider.setMajorTickSpacing(3);
		plantSlider.setMinorTickSpacing(1);
		plantSlider.setPaintTicks(true);
	}
	
	//setters
	public void setCreatureNum(int creatureNum)
	{
		this.creatureNum = creatureNum;
	}
	
	public void setPlantNum(int plantNum)
	{
		this.plantNum = plantNum;
	}
	
	public void setDietRatio(int dietRatio)
	{
		this.dietRatio = dietRatio;
	}
	
	//getters
	public int getCreatureNum()
	{
		return this.creatureNum;
	}
	
	public int getPlantNum()
	{
		return this.plantNum;
	}
	
	public int getDietRatio()
	{
		return this.dietRatio;
	}

}
