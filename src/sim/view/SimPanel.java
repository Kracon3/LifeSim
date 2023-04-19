package sim.view;

import sim.controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class SimPanel extends JPanel
{
	private Controller app;
	private SpringLayout layout;
	private LifePanel panel;
	
	public SimPanel(Controller App)
	{
		super();
		
		this.app = app;
		this.layout = new SpringLayout();
		this.panel = new LifePanel();
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		this.setLayout(layout);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(1200, 900));
		
		this.add(panel);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		
	}

}
