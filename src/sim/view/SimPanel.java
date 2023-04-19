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
	private Controller app;
	private SpringLayout layout;
	private LifePanel panel;
	private JButton startButton;
	
	public SimPanel(Controller App)
	{
		super();
		
		this.app = app;
		this.layout = new SpringLayout();
		this.panel = new LifePanel(app);
		this.startButton = new JButton("Start");
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		this.setLayout(layout);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(1200, 900));
		
		this.add(startButton);
		
		this.add(panel);
	}
	
	private void setupListeners()
	{
		startButton.addActionListener(click -> LifePanel.start());
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.WEST, panel, -750, SpringLayout.EAST, this);
		
		layout.putConstraint(SpringLayout.WEST, startButton, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, startButton, -50, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.SOUTH, startButton, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.NORTH, startButton, -50, SpringLayout.SOUTH, this);
	}

}
