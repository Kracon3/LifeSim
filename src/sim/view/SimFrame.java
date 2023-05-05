package sim.view;

import sim.controller.Controller;
import javax.swing.JFrame;

/*
 * extends JFrame, is a set size, and uses a SimPanel for a panel
 */
public class SimFrame extends JFrame
{
	private Controller app;
	private SimPanel panel;
	
	/*
	 * calls super() from JFrame and initializes both a Controller and a SimPanel
	 */
	public SimFrame(Controller app)
	{
		super();
		
		this.app = app;
		this.panel = new SimPanel();
		
		setupFrame();
	}
	
	/*
	 * Frame is titled "Simulation" with a set size and a SimPanel inside it
	 */
	private void setupFrame()
	{
		this.setTitle("Simulation");
		this.setSize(1200, 938);
		this.setResizable(false);
		this.setContentPane(panel);
		
		this.setVisible(true);
	}
	
}