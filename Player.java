import java.util.Scanner;


public class Player extends Playable{

	public Player(Unit unit) {
		super(unit);
	}
	
	public  Integer[] makeStep() throws Exception{
		System.out.print("Player, make a step (\'1x1\' for example): ");
		Scanner in = new Scanner(System.in);
		String input = in.next();
		
		String[] words = input.split("x");
		if( words.length != 2 )
			throw new Exception("Wrong count of input words: " + words.length);
		Integer[] nums = new Integer[2];
		for(int i = 0; i < 2; i++){
			Integer num = new Integer(Integer.parseInt(words[i]));
			if (num > 3 || num < 1)
				throw new Exception("Wrong input word: not \'1\', \'2\' or \'3\'");
			nums[i] = num;
		}
		return nums;
	}
	
	public void printWhoIAm(){
		System.out.println("Player");
	}

}
