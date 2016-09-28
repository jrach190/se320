/**----------------------------------------------------------------------
 * 
 * @author Jonathan Rach
 * This class handles all gameboard actions and logic related to the 
 * gameboard. 
 *
 *---------------------------------------------------------------------*/
public class GameRules 
{
	public static final int NUMBER_OF_ROWS = 7;
	public static final int NUMBER_OF_COLUMNS = 8;
	
	public int winner;
	
	private Integer[][] gameBoard = new Integer[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];  
	
	//Allow for instantiation
	public GameRules() 
	{
	}
	
	/**
	 * This method allows for the addition of a gamepiece onto the underlying data model 
	 * that powers the board.
	 * @param column is the passed column number that the player wants to add a checker to.
	 * @param playerNumber an int to identify which player is adding the checker.
	 * @return a String to inform the user that a game piece has been added successfully
	 * @throws ColumnFullException when the column of game pieces is already full and the addition would cause an Array IndexOutOfBounds exception
	 */
	public String addGamePiece(int column, int playerNumber) throws ColumnFullException
	{
		for (int i=0; i<NUMBER_OF_ROWS; i++)
		{
			if (gameBoard[i][column] == null)
			{
				gameBoard[i][column] = playerNumber;
				return "Game Piece added";
			}
		}
		throw new ColumnFullException();
	}
	
	/**
	 * This method loops through and clears all values that exist on the gameboard.
	 */
	public void clearBoard()
	{
		//Loop through gameboard array and clear all values
		for (int i=0; i<NUMBER_OF_ROWS; i++)
		{
			for (int j=0; j<NUMBER_OF_COLUMNS; j++)
			{
				gameBoard[i][j] = null;
			}
		}
	}
	
	/**
	 * This method checks horizontally, vertically, and diagonally to ensure win conditions are properly handled.
	 * This method also checks to make sure that if there is a tie it can be handled appropriately 
	 * @return
	 */
	public int checkWinConditions()
	{
		int win = 0;
		boolean tie = true;
		

		//Check for horizontal win
		for (int i=0; i<NUMBER_OF_ROWS; i++)
		{
			for (int j=0; j<(NUMBER_OF_COLUMNS-3); j++)
			{
				if ((gameBoard[i][j] == gameBoard[i][j+1]) && (gameBoard[i][j] == gameBoard[i][j+2]) && (gameBoard[i][j] == gameBoard[i][j+3]) && (gameBoard[i][j] != null)) 
				{
					winner = gameBoard[i][j];
					win = 1;
				}
				
			}
		}

		//Check for vertical win
		for (int i=0; i<(NUMBER_OF_ROWS-3); i++)
		{
			for (int j=0; j<NUMBER_OF_COLUMNS; j++)
			{
				if ((gameBoard[i][j] == gameBoard[i+1][j]) && (gameBoard[i][j] == gameBoard[i+2][j]) && (gameBoard[i][j] == gameBoard[i+3][j]) && (gameBoard[i][j] != null))
				{
					winner = gameBoard[i][j];
					win = 1;
				}	
			}
		}
		
		//Check for Diagonal-right win
		for (int i=0; i<(NUMBER_OF_ROWS-3); i++)
		{
			for (int j=0; j<(NUMBER_OF_COLUMNS-2); j++)
			{
				if ((gameBoard[i][j] != null) && (gameBoard[i][j] == gameBoard[i+1][j+1]) && (gameBoard[i][j] == gameBoard[i+2][j+2]) && (gameBoard[i][j] == gameBoard[i+3][j+3]))
				{
					winner = gameBoard[i][j];
					win = 1;
				}
			}
		}
		
		//Check for Diagonal-left win
		for (int i=NUMBER_OF_ROWS; i<3; i++)
		{
			for (int j=NUMBER_OF_COLUMNS; j<2; j++)
			{
				if ((gameBoard[i][j] != null) && (gameBoard[i][j] == gameBoard[i-1][j-1]) && (gameBoard[i][j] == gameBoard[i-2][j-2]) && (gameBoard[i][j] == gameBoard[i-3][j-3]))
				{
					winner = gameBoard[i][j];
					win = 1;
				}
			}
		}
		
		//Check for tie game
		if (win != 1)
		{
			for (int i=0; i<NUMBER_OF_ROWS; i++)
			{
				for (int j=0; j<NUMBER_OF_COLUMNS; j++)
				{
					if (gameBoard[i][j] == null)
					{
						tie = false;
					}
				}
			}
			if (tie)
			{
				win = -1;
			}
		}
		return win;
	}
	
	/**
	 * This method returns the game board array
	 * @return gameBoard array
	 */
	public Integer[][] getBoard() 
	{
		return gameBoard;
	}
	
	/**
	 * This method returns the winner of the game
	 * @return int winner
	 */
	public Integer getWinner()
	{
		return winner;
	}
	
}
