package world;

import item.Item;
import item.ItemGenerator;

import java.awt.Color;
import java.util.ArrayList;

import unit.Unit;
import unit.character.Character;
import unit.monster.Monster;

public class Encounter {
	
	//encounter id
	private int id;
	
	//combat grid
	private CombatSquare[][] grid;
	
	//mobs
	private ArrayList<Unit> mobs;
	
	//loot
	//no idea, list of items?
	
	//victory condition
	private String victoryCondition;
	private int victoryConditionValue;

	public Encounter() {
		// TODO random generation?
		this.id = 0;
		this.mobs = new ArrayList<Unit>();
		this.grid = new CombatSquare[19][19];
		for(int i = 0; i < 19; i++){
			for(int j = 0; j < 19; j++){
				this.grid[i][j] = new CombatSquare(i, j);
				this.grid[i][j].setBackground(Color.LIGHT_GRAY);
			}
		}
	}
	
	public Encounter(Character character){
		this();
		this.grid[9][0].add(character);
		character.setCombatRow(9);
		character.setCombatCol(0);
		
		Monster mob = new Monster();
		mob.setName("Test Mob");
		mob.getBaseStats().setConstitution(3.0);
		mob.getBaseStats().setStrength(3.0);
		mob.getBaseStats().setIntelligence(3.0);
		mob.getBaseStats().setSpirit(3.0);
		mob.getBaseStats().setStamina(3.0);
		ItemGenerator gen = new ItemGenerator();
		Item temp = gen.getNewItem("wieldableItem");
		mob.equipItem(temp);
		
		mobs.add(mob);
		
		this.grid[9][18].add(mob);
		mob.setCombatRow(9);
		mob.setCombatCol(18);
	}

	public Encounter(int id, CombatSquare[][] grid, ArrayList<Unit> mobs,
			String victoryCondition, int victoryConditionValue) {
		super();
		this.id = id;
		this.grid = grid;
		this.mobs = mobs;
		this.victoryCondition = victoryCondition;
		this.victoryConditionValue = victoryConditionValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CombatSquare[][] getGrid() {
		return grid;
	}

	public void setGrid(CombatSquare[][] grid) {
		this.grid = grid;
	}

	public ArrayList<Unit> getMobs() {
		return mobs;
	}

	public void setMobs(ArrayList<Unit> mobs) {
		this.mobs = mobs;
	}

	public String getVictoryCondition() {
		return victoryCondition;
	}

	public void setVictoryCondition(String victoryCondition) {
		this.victoryCondition = victoryCondition;
	}

	public int getVictoryConditionValue() {
		return victoryConditionValue;
	}

	public void setVictoryConditionValue(int victoryConditionValue) {
		this.victoryConditionValue = victoryConditionValue;
	}
	
	

}
