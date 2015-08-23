import java.util.Scanner;

/**
 * 
 * @author smith
 *
 */

public class Main {
	/**
	 * @param args
	 */
	public static boolean DEBUG = false;
	
	public static void main(String[] args) {
		System.out.println("Tic Tac Toe (vs Bot)");
		Game game = new Game();

		System.out.print("Player chooses (X or O): ");
		Scanner in = new Scanner(System.in);
		String input = in.next();
		input = input.toUpperCase();
		Unit player_unit = Unit.X;
		if( input.equals("X") )
			player_unit = Unit.X;
		else
			if( input.equals("O") )
				player_unit = Unit.O;
			else{
				System.err.println("Error. Wrong input");
				System.exit(-1);
			}
		game.addPlayer(player_unit);
		
		try {
			game.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Unit winUnit = game.winnerOfGame();
		
		if(winUnit == null){
			System.out.println("TIE");
			System.exit(0);
		}
		if(winUnit.equals(player_unit))
			System.out.println("PLAYER WON BOT");
		else
			System.out.println("BOT WON PLAYER");
	}

}
