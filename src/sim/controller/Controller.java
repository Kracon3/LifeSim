package sim.controller;

import sim.view.SimFrame;

/**
 * 
 * @author kbec5283
 *	Controller declares and initializes a SimFrame
 */
public class Controller 
{
	private SimFrame window;
	
	/*
	 * initializes window
	 */
	public Controller()
	{
		this.window = new SimFrame(this);
	}
	
	/*
	 * stub start that starts the program
	 */
	public void start()
	{
		
	}

}
