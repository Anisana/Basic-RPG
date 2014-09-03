package unit;

import blocks.StatBlock;
import core.DataStore;
import path.Path;

public abstract class Unit {
	
	private String name;
	
	//the Unit's path
	private Path path;
	
	//variables for use during combat
	//holds the location of the unit on the grid
	private int combatRow;
	private int combatCol;
	
	public Unit(){
		this.name = "";
		this.path = new Path();
	}
	
	public Unit(String name, Path path){
		this.name = name;
		this.path = path;
	}
	
	public void updatePath(DataStore data){
		String tempName = this.path.getName();
		this.path = data.getPath(tempName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public int getCombatRow() {
		return combatRow;
	}

	public void setCombatRow(int combatRow) {
		this.combatRow = combatRow;
	}

	public int getCombatCol() {
		return combatCol;
	}

	public void setCombatCol(int combatCol) {
		this.combatCol = combatCol;
	}

	@Override
	public String toString() {
		return "Unit [name=" + name + "]";
	}
	
	//public abstract StatBlock getModifiedStats();
	public abstract double getDamage(int skillNumber);
	public abstract boolean takeDamage(double baseDamage);
	public abstract void updateVSPanel();
	public abstract boolean giveExperience(int exp);
	public abstract void regenResources();
	public abstract void cooldownSkills();
}
