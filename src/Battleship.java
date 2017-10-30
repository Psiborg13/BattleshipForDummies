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
	final static String HIT = "!";
	final static String MISS = "X";
	static String[][] playerShips = new String[10][10];
	static String[][] playerBoard = new String[10][10];
	static String[][] enemyShips = new String[10][10];
	static String[][] enemyBoard = new String[10][10];
	static int playerShipsSunk = 0;
	static int enemyShipsSunk = 0;

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
		System.out.println();
		while(!(playerShipsSunk == 5 || enemyShipsSunk == 5))
		{
			System.out.println("Your Turn:");
			printBoard(playerBoard);
			playerShot();
			enemyShipsSunk = shipsSunk(enemyShips);
			if(enemyShipsSunk!=5)
			{
				System.out.println("\nEnemy Turn:");
				enemyShot();
				playerShipsSunk = shipsSunk(playerShips);
			}
			System.out.println();
		}
		if(playerShipsSunk == 5)
		{
			System.out.println("The enemy sank your battleship!");
		}
		else
		{
			System.out.println("You win! You sank their battleship!");
		}
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

	public static void playerShot()
	{
		Scanner scan = new Scanner(System.in);
		boolean takingShot = true;
		int x = -1;
		int y = -1;
		while(takingShot)
		{
			x = -1;
			y = -1;
			System.out.println();
			while(x < 0 || x > 10)
			{
				System.out.println("Enter the row for your shot: 0 through 9");
				x = scan.nextInt();
			}
			while(y < 0 || y > 10)
			{
				System.out.println("Enter the column for your shot: 0 through 9");
				y = scan.nextInt();
			}
			if(playerBoard[x][y].equals(BLANK))
			{
				if(!enemyShips[x][y].equals(BLANK)&&!enemyShips[x][y].equals(HIT)
						&&!enemyShips[x][y].equals(MISS))
				{
					playerBoard[x][y] = HIT;
					enemyShips[x][y] = HIT;
					System.out.println("It's a hit!");
				}
				else
				{
					playerBoard[x][y] = MISS;
					enemyShips[x][y] = MISS;
					System.out.println("It's a miss!");
				}
				printBoard(playerBoard);
				takingShot = false;
			}
		}
	}

	public static void enemyShot()
	{
		Random rand = new Random();
		boolean takingShot = true;
		int x = -1;
		int y = -1;
		while(takingShot)
		{
			boolean hasTarget = false;
			for(int i = 0; i < 10; i++)
			{
				for(int j = 0; j < 10; j++)
				{
					if(enemyBoard[i][j].equals(HIT))
					{
						if(i>0&&enemyBoard[i-1][j].equals(BLANK))
						{
							hasTarget = true;
							x = i-1;
							y = j;
							i = 11;
							j = 11;
						}
						else if(j>0&&enemyBoard[i][j-1].equals(BLANK))
						{
							hasTarget = true;
							x = i;
							y = j-1;
							i = 11;
							j = 11;
						}
						else if(i<9&&enemyBoard[i+1][j].equals(BLANK))
						{
							hasTarget = true;
							x = i+1;
							y = j;
							i = 11;
							j = 11;
						}
						else if(j<9&&enemyBoard[i][j+1].equals(BLANK))
						{	
							hasTarget = true;
							x = i;
							y = j+1;
							i = 11;
							j = 11;
						}
					}
				}
			}
			if(!hasTarget)
			{
				x = rand.nextInt(10);
				y = rand.nextInt(10);
			}
			if(enemyBoard[x][y].equals(BLANK))
			{
				if(!playerShips[x][y].equals(BLANK)&&!playerShips[x][y].equals(HIT)
						&&!playerShips[x][y].equals(MISS))
				{
					enemyBoard[x][y] = HIT;
					playerShips[x][y] = HIT;
					System.out.println("It's a hit!");
				}
				else
				{
					enemyBoard[x][y] = MISS;
					playerShips[x][y] = MISS;
					System.out.println("It's a miss!");
				}
				printBoard(enemyBoard);
				takingShot = false;
			}
		}
	}

	public static int shipsSunk(String[][] board)
	{
		int shipsSunk = 0;
		for (int a = 1; a < 6; a++) {
			boolean shipOnBoard = false;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if(board[i][j].equals(a+""))
					{
						shipOnBoard = true;
						i = 11;
						j = 11;
					}
				}
			}
			if(!shipOnBoard)
			{
				shipsSunk++;
			}
		}
		return shipsSunk;
	}
}
