
public class Cell {
	
	private boolean is_empty = true;
	private Unit content = null;
	private int x, y;
	
	public Cell(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Integer[] getCoordinates(){
		Integer [] p = new Integer [] {x,y};
		return p;
	}
	
	public void printContent(){
		String s = "";
		if (is_empty || content == null)
			s = x+"x"+y;
		else{
			if ( content == Unit.X)
				s = " X ";
			if ( content == Unit.O)
				s = " O ";
		}
		System.out.print(s);
	}
	
	public boolean isEmpty(){
		return is_empty;
	}
	
	public Unit content() throws Exception{
		if(content == null)
			throw new Exception("The cell is empty, check isEmpty() before calling this method");
		return content;
	}
	
	public void setContent(Unit unit) throws Exception{
		if(!is_empty)
			throw new Exception("Rewriting of no empty cell is not allowed");
		content = unit;
		is_empty = false;
	}
}
