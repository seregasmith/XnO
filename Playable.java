
public abstract class Playable {
	
	protected Unit role;
	
	public Playable (Unit unit){
		role = unit;
	}
	
	public Unit role(){
		return role;
	}
	
	public abstract void printWhoIAm();
	
	public abstract Integer[] makeStep() throws Exception;
}
