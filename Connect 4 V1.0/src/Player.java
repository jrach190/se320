/**----------------------------------------------------------------------
 * 
 * @author Jonathan Rach
 *
 * This class handles all characteristics associated with the players of
 * the Connect 4 game. While this class does not have a large amount of 
 * functionality at present, it is included to allow for future expansion. 
 *---------------------------------------------------------------------*/
public class Player 
{
	private String playerName; 
	private Integer playerNumber;
	
	//Protect from instantiation without arguments
	private Player(){}
	
	public Player(String name, Integer playerNumber)
	{
		this.playerName = name;
		this.playerNumber = playerNumber;
	}
	
	public String getPlayerName()
	{
		return playerName;
	}
	
	public Integer getPlayerNumber()
	{
		return playerNumber;
	}
	
	public void setPlayerName(String name)
	{
		this.playerName = name;
	}

}
