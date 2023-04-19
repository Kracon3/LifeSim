package sim.view;

import sim.controller.Controller;
import javax.swing.*;
import java.awt.*;

public class LifePanel extends JPanel
{
	private Controller app;
	
	public LifePanel(Controller app)
	{
		super();
		
		this.app = app;
		
		setupPanel();
	}
	
	private void setupPanel()
	{
		this.setPreferredSize(new Dimension(800, 800));
		this.setBackground(Color.BLACK);
	}

}
