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
	static String[][] hiddenBoard = new String[10][10];
	static String[][] visibleBoard = new String[10][10];
	int p1ShipsSunk = 0;
	int p2ShipsSunk = 0;
	public static void main(String[] args)
	{	
		for (int i = 0; i < hiddenBoard.length; i++) {
			for (int j = 0; j < hiddenBoard.length; j++) {
				hiddenBoard[i][j] = BLANK;
			}
		}
		for (int i = 0; i < visibleBoard.length; i++) {
			for (int j = 0; j < visibleBoard.length; j++) {
				visibleBoard[i][j] = BLANK;
			}
		}
		placeShip(5, "1");
		placeShip(4, "2");
		placeShip(3, "3");
		placeShip(3, "4");
		placeShip(2, "5");
		placeEnemyShip(5, "6");
		placeEnemyShip(4, "7");
		placeEnemyShip(3, "8");
		placeEnemyShip(3, "9");
		placeEnemyShip(2, "A");
		//for testing
		System.out.println();
		printHiddenBoard();
	}
	
	public static void printVisibleBoard()
	{
		for (int i = 0; i < hiddenBoard.length; i++) {
			for (int j = 0; j < hiddenBoard.length; j++) {
				System.out.print(hiddenBoard[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void printHiddenBoard()
	{
		for (int i = 0; i < hiddenBoard.length; i++) {
			for (int j = 0; j < hiddenBoard.length; j++) {
				System.out.print(hiddenBoard[i][j]+" ");
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
							": It must be between 0 inclusive and "
							+"10 exclusive.");
					x = scan.nextInt();
				}
				while(!(min <= y && max >= y))
				{
					System.out.println("Please select the column for the"+
							" beginning of your ship of length "+length+
							": It must be between "+min+" inclusive and "
							+max+" inclusive.");
					y = scan.nextInt();
				}
				boolean placed = true;
				String[][] tempBoard = new String[10][10];
				for (int i = 0; i < tempBoard.length; i++) {
					for (int j = 0; j < tempBoard.length; j++) {
						tempBoard[i][j] = hiddenBoard[i][j];
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
					hiddenBoard = tempBoard;
					placing = false;
				}
			}
			else
			{
				while(!(min <= x && max >= x))
				{
					System.out.println("Please select the row for the"+
							" beginning of your ship of length "+length+
							": It must be between "+min+" inclusive and "
							+max+" inclusive.");
					x = scan.nextInt();
				}
				while(!(0 <= y && 10 > y))
				{
					System.out.println("Please select the column for the"+
							" beginning of your ship of length "+length+
							": It must be between 0 inclusive and "
							+"10 exclusive.");
					y = scan.nextInt();
				}
				boolean placed = true;
				String[][] tempBoard = new String[10][10];
				for (int i = 0; i < tempBoard.length; i++) {
					for (int j = 0; j < tempBoard.length; j++) {
						tempBoard[i][j] = hiddenBoard[i][j];
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
					hiddenBoard = tempBoard;
					placing = false;
				}
			}
			printHiddenBoard();
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
						tempBoard[i][j] = hiddenBoard[i][j];
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
					hiddenBoard = tempBoard;
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
						tempBoard[i][j] = hiddenBoard[i][j];
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
					hiddenBoard = tempBoard;
					placing = false;
				}
			}
		}
	}
}
