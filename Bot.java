import java.util.ArrayList;

public class Bot extends Playable {

	private int counter = 0;
	private Grid grid = null;

	public Bot(Unit unit) {
		super(unit);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void printWhoIAm() {
		System.out.println("Bot");
	}

	public Integer[] makeStep() throws Exception {
		if (role.equals(Unit.X))
			return xAlgorihtm();
		if (role.equals(Unit.O))
			return oAlgorihtm();
		throw new Exception();
	}

	public void setGrid(Grid src) {
		this.grid = src;
	}

	public Integer[] xAlgorihtm() {
		Cell[] set = grid.getWinSetWithWeight(2);
		if (set != null)
			return fillSet(set);

		set = grid.getWinSetWithWeight(6);
		if (set != null)
			return fillSet(set);

		set = grid.getWinSetWithWeight(1);
		if (set != null)
			return trySetThroughOne(set);
		try {
			return trySetToAngleOrMiddle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Integer[] trySetToAngleOrMiddle() throws Exception {
		ArrayList<Cell> free_cells = grid.getFreeCells();
		if (free_cells.isEmpty())
			throw new Exception("There's no one free cell.");

		// find angle
		for (Cell cell : free_cells) {
			Integer[] coords = cell.getCoordinates();
			if (coords[0] != 2 && coords[1] != 2)
				return coords;
		}

		// find middle
		for (Cell cell : free_cells) {
			Integer[] coords = cell.getCoordinates();
			if (!(coords[0] == 2 && coords[1] == 2)) // not a center
				return coords;
		}

		return free_cells.get(0).getCoordinates();
	}

	private Integer[] fillSet(Cell[] set) {
		for (Cell cell : set) {
			if (cell.isEmpty())
				return cell.getCoordinates();
		}
		return null;
	}

	private Integer[] trySetThroughOne(Cell[] set) {
		int pointer_of_filled_cell = -1;
		for (int i = 0; i < 3; i++) {
			if (!set[i].isEmpty()) {
				pointer_of_filled_cell = i;
				break;
			}
		}
		if (pointer_of_filled_cell == 0) {
			if (set[2].isEmpty())
				return set[2].getCoordinates();
			if (set[1].isEmpty())
				return set[1].getCoordinates();
		}

		if (pointer_of_filled_cell == 1) {
			if (set[0].isEmpty())
				return set[0].getCoordinates();
			if (set[2].isEmpty())
				return set[2].getCoordinates();
		}

		if (pointer_of_filled_cell == 2) {
			if (set[0].isEmpty())
				return set[0].getCoordinates();
			if (set[1].isEmpty())
				return set[1].getCoordinates();
		}

		return null;
	}

	public Integer[] oAlgorihtm() throws Exception {
		Cell[] set = grid.getWinSetWithWeight(6);
		if (set != null)
			return fillSet(set);

		set = grid.getWinSetWithWeight(2);
		if (set != null)
			return fillSet(set);

		ArrayList<Cell> free_cells = grid.getFreeCells();
		if (free_cells.isEmpty())
			throw new Exception("There's no one free cell.");

		// find center
		for (Cell cell : free_cells) {
			Integer[] coords = cell.getCoordinates();
			if (coords[0] == 2 && coords[1] == 2)
				return coords;
		}

		// find middle
		for (Cell cell : free_cells) {
			Integer[] coords = cell.getCoordinates();
			if (!(coords[0] == 2 && coords[1] == 2) &&	// not a center
					(coords[0] == 2 || coords[1] == 2)) 
				return coords;
		}


		return free_cells.get(0).getCoordinates();
	}
}