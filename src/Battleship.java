/*Class of ship	Size
	1	Carrier		5
	2	Battleship	4
	3	Cruiser		3
	4	Submarine	3
	5	Destroyer	2*/

import java.util.Scanner;
import java.util.Random;

public class Battleship 
{
	final static String BLANK = "~";
	static String[][] playerShips = new String[10][10];
	static String[][] playerBoard = new String[10][10];
	static String[][] enemyShips = new String[10][10];
	static String[][] enemyBoard = new String[10][10];
	int p1ShipsSunk = 0;
	int p2ShipsSunk = 0;
	public static void main(String[] args)
	{	
		for (int i = 0; i < playerShips.length; i++) {
			for (int j = 0; j < playerShips.length; j++) {
				playerShips[i][j] = BLANK;
			}
		}
		for (int i = 0; i < playerBoard.length; i++) {
			for (int j = 0; j < playerBoard.length; j++) {
				playerBoard[i][j] = BLANK;
			}
		}
		for (int i = 0; i < enemyShips.length; i++) {
			for (int j = 0; j < enemyShips.length; j++) {
				enemyShips[i][j] = BLANK;
			}
		}
		for (int i = 0; i < enemyBoard.length; i++) {
			for (int j = 0; j < enemyBoard.length; j++) {
				enemyBoard[i][j] = BLANK;
			}
		}
		placeShip(5, "1");
		placeShip(4, "2");
		placeShip(3, "3");
		placeShip(3, "4");
		placeShip(2, "5");
		placeEnemyShip(5, "1");
		placeEnemyShip(4, "2");
		placeEnemyShip(3, "3");
		placeEnemyShip(3, "4");
		placeEnemyShip(2, "5");
		//for testing
		System.out.println();
		printBoard(enemyShips);
	}
	
	public static void printBoard(String[][] board)
	{
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void placeShip(int length, String symbol)
	{
		Scanner scan = new Scanner(System.in);
		boolean placing = true;
		while(placing){
			int x = -1;
			int y = -1;
			String direction = "";
			int min = 0;
			int max = 10 - length;
			while(!(direction.equals("horizontal")
					||direction.equals("vertical")))
			{
				System.out.println("Do you want this ship to be "+
						"horizontal or vertical?");
				direction = scan.nextLine();
			}
			if(direction.equals("horizontal"))
			{
				while(!(0 <= x && 10 > x))
				{
					System.out.println("Please select the row for the"+
							" beginning of your ship of length "+length+
							": It must be between 0 and "
							+"9 inclusive.");
					x = scan.nextInt();
				}
				while(!(min <= y && max >= y))
				{
					System.out.println("Please select the column for the"+
							" beginning of your ship of length "+length+
							": It must be between "+min+" and "
							+max+" inclusive.");
					y = scan.nextInt();
				}
				boolean placed = true;
				String[][] tempBoard = new String[10][10];
				for (int i = 0; i < tempBoard.length; i++) {
					for (int j = 0; j < tempBoard.length; j++) {
						tempBoard[i][j] = playerShips[i][j];
					}
				}
				for (int i = y; i < y+length; i++) {
					if(!tempBoard[x][i].equals(BLANK))
					{
						placed = false;
						i = 11;
					}
					else
					{
						tempBoard[x][i] = ""+symbol;
					}
				}
				if(placed)
				{
					playerShips = tempBoard;
					placing = false;
				}
			}
			else
			{
				while(!(min <= x && max >= x))
				{
					System.out.println("Please select the row for the"+
							" beginning of your ship of length "+length+
							": It must be between "+min+" and "
							+max+" inclusive.");
					x = scan.nextInt();
				}
				while(!(0 <= y && 10 > y))
				{
					System.out.println("Please select the column for the"+
							" beginning of your ship of length "+length+
							": It must be between 0 and "
							+"9 inclusive.");
					y = scan.nextInt();
				}
				boolean placed = true;
				String[][] tempBoard = new String[10][10];
				for (int i = 0; i < tempBoard.length; i++) {
					for (int j = 0; j < tempBoard.length; j++) {
						tempBoard[i][j] = playerShips[i][j];
					}
				}
				for (int i = x; i < x+length; i++) {
					if(!tempBoard[i][y].equals(BLANK))
					{
						placed = false;
						i = 11;
					}
					else
					{
						tempBoard[i][y] = ""+symbol;
					}
				}
				if(placed)
				{
					playerShips = tempBoard;
					placing = false;
				}
			}
			printBoard(playerShips);
		}
	}

	public static void placeEnemyShip(int length, String symbol)
	{
		Random rand = new Random();
		boolean placing = true;
		while(placing){
			int x = -1;
			int y = -1;
			int min = 0;
			int max = 10 - length;
			boolean direction = rand.nextBoolean();
			if(direction)
			{
				x = rand.nextInt(10);
				y = rand.nextInt(max + 1 - min)+min;
				boolean placed = true;
				String[][] tempBoard = new String[10][10];
				for (int i = 0; i < tempBoard.length; i++) {
					for (int j = 0; j < tempBoard.length; j++) {
						tempBoard[i][j] = enemyShips[i][j];
					}
				}
				for (int i = y; i < y+length; i++) {
					if(!tempBoard[x][i].equals(BLANK))
					{
						placed = false;
						i = 11;
					}
					else
					{
						tempBoard[x][i] = ""+symbol;
					}
				}
				if(placed)
				{
					enemyShips = tempBoard;
					placing = false;
				}
			}
			else
			{
				x = rand.nextInt(max + 1 - min)+min;
				y = rand.nextInt(10);
				boolean placed = true;
				String[][] tempBoard = new String[10][10];
				for (int i = 0; i < tempBoard.length; i++) {
					for (int j = 0; j < tempBoard.length; j++) {
						tempBoard[i][j] = enemyShips[i][j];
					}
				}
				for (int i = x; i < x+length; i++) {
					if(!tempBoard[i][y].equals(BLANK))
					{
						placed = false;
						i = 11;
					}
					else
					{
						tempBoard[i][y] = ""+symbol;
					}
				}
				if(placed)
				{
					enemyShips = tempBoard;
					placing = false;
				}
			}
		}
	}
}
