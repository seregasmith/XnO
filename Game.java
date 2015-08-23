public class Game {

	/**
	 * @param args
	 */
	private Playable player_x = null, player_o = null;
	private Grid grid = new Grid();
	private Unit winner_of_game = null;

	public void addPlayer(Unit unit) {
		Player new_player = new Player(unit);
		if (unit == Unit.X)
			player_x = new_player;
		else
			player_o = new_player;

	}
	
	public Unit winnerOfGame() {
		if(winner_of_game == null)
			return null; //
		return winner_of_game;
	}
	

	private void initBot() throws Exception {
		if (player_x != null){
			player_o = new Bot(Unit.O);
		}
		else if (player_o != null)
			player_x = new Bot(Unit.X);
		else
			throw new RuntimeException("Player still not inited");
	}

	public void printRegisteredPlayers() {
		System.out.print("X: ");
		player_x.printWhoIAm();
		System.out.print("O: ");
		player_o.printWhoIAm();
	}

	public void start() throws Exception {
		try {
			initBot();
		} catch (Exception e) {
			throw e;
		}
		printRegisteredPlayers();

		try {
			grid.init();
			if(player_o instanceof Bot)
				((Bot) player_o).setGrid(grid);
			if(player_x instanceof Bot)
				((Bot) player_x).setGrid(grid);
		} catch (Exception e) {
			throw e;
		}

		int steps = 0;

		while (steps < 9) {
			System.out.println("Step " + steps);
			grid.print();
			Integer[] step = new Integer[2];
			synchronized (grid) { // System.out.println() of grid executes in
									// other Thread
				try {
					if ((steps + 1) % 2 != 0) {
						step = player_x.makeStep();
						grid.registerStep(step, Unit.X);
					} else {
						step = player_o.makeStep();
						grid.registerStep(step, Unit.O);
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
					System.out.println("Make other step");
					Thread.sleep(2 * 1000);
					continue;
				}
			}

			if (grid.hasFinishedWinSet()) {
				
				if ((steps + 1) % 2 != 0){
					winner_of_game = Unit.X;
					}
				else
					winner_of_game = Unit.O;
				String winner = winner_of_game.equals(Unit.X)?"X":"O";
				System.out.println(winner + " is winner");
				grid.print();				
				break;
			}

			steps++;
		}
		grid.print();
	}
}
