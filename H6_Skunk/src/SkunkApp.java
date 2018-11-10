
public class SkunkApp
{
	public SkunkUI skunkUI;
	public SkunkController skunkDomain;
	public int numberOfPlayers;
	public String[] playerNames;
	
	public SkunkApp()
	{
		Dice dice = new Dice();
		skunkUI = new SkunkUI();
		skunkDomain = new SkunkController(skunkUI,dice);
		skunkUI.setDomain(skunkDomain); 
		this.numberOfPlayers = 0;
		this.playerNames = new String[20];
		
	}

	/**
	 * Runs the app within an event loop
	 * @return
	 */
	public boolean run()
	{
		return skunkDomain.run();	
	}
	
	public static void main(String[] args)
	{
		new SkunkApp().run();
	}
	
	

}
