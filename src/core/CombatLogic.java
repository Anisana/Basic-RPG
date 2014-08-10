package core;

import java.util.ArrayList;

import unit.NPC;
import unit.Unit;
import unit.character.Character;
import unit.monster.Monster;

public class CombatLogic {

	private ArrayList<Unit> units;
	private int turnOwner;
	
	//grid
	
	CombatLogic(){
		
	}
	
	public CombatLogic(Character character, Monster mob){
		this.turnOwner = 0;
		
		//roll initiatives?
		units = new ArrayList<Unit>();
		units.add(character);
		units.add(mob);
	}
	
	public void passTurn(){
		this.turnOwner++;
		if(turnOwner == units.size()){
			this.turnOwner = 0;
		}
		
		if(units.get(turnOwner).getClass().getInterfaces().length > 0){
			if(units.get(turnOwner).getClass().getInterfaces()[0] == NPC.class){
				takeTurn();
			}
		}
	}
	
	private void takeTurn(){
		
	}
	
	
}
