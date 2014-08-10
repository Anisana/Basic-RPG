package unit;

public abstract class Unit {
	
	private String name;
	
	public Unit(){
		this.name = "";
	}
	
	public Unit(String name){
		this.name = name;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Unit [name=" + name + "]";
	}
	
	
	public abstract boolean isNPC();
	

}
