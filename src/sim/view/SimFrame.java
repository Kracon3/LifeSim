package sim.view;

import sim.controller.Controller;
import javax.swing.JFrame;

public class SimFrame extends JFrame
{
	private Controller app;
	private SimPanel panel;
	
	public SimFrame(Controller app)
	{
		super();
		
		this.app = app;
		this.panel = new SimPanel(this.app);
		
		setupFrame();
	}
	
	
	private void setupFrame()
	{
		this.setTitle("Simulation");
		this.setSize(1040, 828);
		this.setResizable(false);
		this.setContentPane(panel);
		
		this.setVisible(true);
	}
}