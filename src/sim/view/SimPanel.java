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
	
	public SimPanel(Controller App)
	{
		super();
		
		this.app = app;
		this.layout = new SpringLayout();
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		
	}

}
