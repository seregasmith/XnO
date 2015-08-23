import java.util.ArrayList;

public class Grid {

	Cell[][] cells = new Cell[3][3];

	private class WinSet {
		Cell[] cells = new Cell[3];

		public int countWeight() {
			int weight = 0;
			for (Cell cell : cells) {
				if (!cell.isEmpty())
					try {
						if (cell.content().equals(Unit.X))
							weight += 1;
						else
							weight += 3;
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			return weight;
		}

		public boolean isFilled() {
			boolean fl = true; // stay true if all is filled
			for (Cell cell : cells) {
				if (cell.isEmpty()) {
					fl = false;
					break;
				}
			}
			return fl;
		}

		public boolean isFinished() {
			if (isFilled()) {
				int weight = countWeight();
				if (weight == 3 || weight == 6)
					return true;
			}
			return false;
		}

		WinSet(Cell[] cells_set) throws Exception {
			if (cells_set.length != 3)
				throw new Exception("WinSet can have only 3 Cells");
			cells = cells_set; // it can be easy-to-use, when register step
		}
	}

	ArrayList<WinSet> win_sets = new ArrayList();
	
	public Cell[] getWinSetWithWeight(int weight){
		for(WinSet ws : win_sets){
			if (ws.countWeight() == weight)
				return ws.cells;
		}
		return null;
	}

	public void init() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cells[i][j] = new Cell(i + 1, j + 1); // WARN! the nums don't
														// equal.
			}
		}
		addHorizontalWinSets();
		addVerticalWinSets();
		addDiagonalWinSets();
	}
	
	

	private void addHorizontalWinSets() {
		for (int i = 0; i < 3; i++) {
			WinSet ws = null;
			for (int j = 0; j < 3; j++) {
				try {
					ws = new WinSet(cells[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			win_sets.add(ws);
		}
	}

	public void registerStep(Integer[] step, Unit unit) throws Exception {
		int x = step[0] - 1;
		int y = step[1] - 1;

		try {
			cells[x][y].setContent(unit);
		} catch (Exception e) {
			throw e;
		}
	}

	public void addVerticalWinSets() {
		for (int k = 0; k < 3; k++) {
			WinSet ws = null;
			Cell[] set = new Cell[3];
			for (int j = 0; j < 3; j++) {
				for (int i = 0; i < 3; i++) {
					set[i] = cells[i][k];
				}
			}
			try {
				ws = new WinSet(set);
			} catch (Exception e) {
				e.printStackTrace();
			}
			win_sets.add(ws);
		}
	}

	public ArrayList<Cell> getFreeCells() {
		ArrayList<Cell> free_cells = new ArrayList<Cell>();
		for (Cell[] cell_raw : cells) {
			for (Cell cell : cell_raw) {
				if (cell.isEmpty())
					free_cells.add(cell);
			}
		}
		return free_cells;
	}

	public void addDiagonalWinSets() {
		Cell[] setD = new Cell[3]; // diagonal
		Cell[] setID = new Cell[3]; // inverse diagonal
		for (int i = 0; i < 3; i++) {
			setD[i] = cells[i][i];
			setID[i] = cells[3 - 1 - i][i];
		}
		try {
			win_sets.add(new WinSet(setD));
			win_sets.add(new WinSet(setID));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void print() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cells[i][j].printContent();
				System.out.print("|");
			}
			System.out.print("\n------------\n");
		}
		if (Main.DEBUG)
			for (WinSet ws : win_sets) {
				for (Cell cell : ws.cells) {
					cell.printContent();
					System.out.print("|");
				}
				System.out.println();
			}
	}

	public boolean hasFinishedWinSet() {
		for (WinSet ws : win_sets) {
			if (ws.isFinished())
				return true;
		}
		return false;
	}
}
