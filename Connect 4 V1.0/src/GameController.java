/**
 * @author jonathanrach
 * 
 * This class is created to control the game of Connect 4 and to handle
 * input and output for the game. 
 */

import java.util.Random;

public class GameController {
	
	public static int NUMBER_OF_PLAYERS = 2;

	public static void main(String[] args) 
	{
		boolean continueGame = true;
		GameRules gameRules = new GameRules();
		
		String[] players = new String[NUMBER_OF_PLAYERS];
		
		System.out.println("Welcome to Connect 4!");
		
		while (continueGame)
		{
			for (int i=0; i<NUMBER_OF_PLAYERS; i++)
			{
				System.out.println("Please enter the name of player " + (i+1));
				String input = System.console().readLine();
				players[i] = input;
			}
			
			//run code handling the actual turn-by-turn play of the game
			playGame(gameRules, players);
			
			//End-of-game tie handling
			if (gameRules.checkWinConditions() == -1)
			{
				
				System.out.println("Tie! Would you like to play again?(y/n) >>");
				String input = System.console().readLine();
				
				switch(input)
				{
				case ("y"): gameRules.clearBoard(); break;
				
				case ("n"): 
					System.out.println("Thanks for playing, see you next time!");
					continueGame=false;
					break;
					
				default: 
					System.out.println("Bad entry. Exiting game now. See you next time!"); 
					continueGame = false;
					break;
				}
			}
			//End-of-game win handling
			else if (gameRules.checkWinConditions() == 1)
			{
				
				System.out.println("The winner is " + players[gameRules.getWinner()]);
				System.out.println("Would you like to play again?(y/n) >>");
				String input = System.console().readLine();
				
				switch(input)
				{
				case ("y"): gameRules.clearBoard(); break;
				
				case ("n"): 
					System.out.println("Thanks for playing, see you next time!"); 
					continueGame = false;
					break;
					
				default: 
					System.out.println("Bad entry. Exiting game now. See you next time!"); 
					continueGame = false;
					break;
				}
			}
		}
	}
	
	/**
	 * This method has code to actually play the game with two user inputs.
	 * @param g is an object of type GameRules containing the gameboard and the gameboard logic necessary to 'play' Connect 4
	 * @param players is an array of strings containing the players of the game
	 * @return integer containing the number of the player that won
	 */
	public static int playGame(GameRules g, String[] players)
	{
		Random random = new Random();
		int turnCounter = random.nextInt(2);
		
		while (g.checkWinConditions() == 0)
		{
			int i, j;
			
			Integer[][] gameBoard = g.getBoard();
			
			int playerNumber = turnCounter%NUMBER_OF_PLAYERS;
			int column=0;
			turnCounter++;
			
			System.out.println("It is " + players[playerNumber] + "'s turn");
			
			for (i=g.NUMBER_OF_ROWS-1; i>=0; i--)
			{
				for (j=0; j<g.NUMBER_OF_COLUMNS; j++)
				{
					if (gameBoard[i][j] == null){
						System.out.printf("|_|");
					}
					else if (gameBoard[i][j].equals(0))
					{
						System.out.printf("|X|");
					}
					else if (gameBoard[i][j].equals(1))
					{
						System.out.printf("|O|");
					}
				}
				System.out.println("");
			}
			
			System.out.printf(" 1  2  3  4  5  6  7  8\n");
			System.out.println("Enter the number of the column you wish to add a checker. You can press \"r\" to restart or \"q\" to quit.>>");
			String input = System.console().readLine();
			
			switch (input)
			{
				case("1"): column=1; break;
				case("2"): column=2; break;
				case("3"): column=3; break;
				case("4"): column=4; break;
				case("5"): column=5; break;
				case("6"): column=6; break;
				case("7"): column=7; break;
				case("8"): column=8; break;
				case("r"): g.clearBoard(); break; 
				case("q"): System.exit(1);
				default: column=-1; turnCounter--; break;
			}
			
			try
			{
				if (column != -1 && column !=0)
				{
					g.addGamePiece(column-1, playerNumber);
				}
			} catch (ColumnFullException e)
			{
				System.out.println("Error: Column already full.");
				turnCounter--;
			}
		}
		return g.getWinner();
	}

}
