package core;

import unit.character.Character;
import blocks.CharacterStatBlock;

public class CharacterCreationLogic {
	
	private CharacterStatBlock stats;
	private String name;
	private String path; //TODO implement
	private int remainingPoints;

	public CharacterCreationLogic() {
		this.stats = new CharacterStatBlock();
		this.remainingPoints = 15;
	}
	
	//returns true if another increment can be made afterwards, false if not.
	public boolean incrementStat(String stat){
		remainingPoints--;
		
		switch(stat){
		case "strength": stats.setStrength(stats.getStrength()+1.0); return remainingPoints == 0 ? false : true;
		case "stamina": stats.setStamina(stats.getStamina()+1.0); return remainingPoints == 0 ? false : true;
		case "constitution": stats.setConstitution(stats.getConstitution()+1.0); return remainingPoints == 0 ? false : true;
		case "intelligence": stats.setIntelligence(stats.getIntelligence()+1.0); return remainingPoints == 0 ? false : true;
		case "spirit": stats.setSpirit(stats.getSpirit()+1.0); return remainingPoints == 0 ? false : true;
		}
		
		return true;
	}
	
	//returns true if another decrement can be made afterwards, false if not.
	public boolean decrementStat(String stat){
		remainingPoints++;
		
		switch(stat){
		case "strength": stats.setStrength(stats.getStrength()-1.0); return stats.getStrength() == 0.0 ? false : true;
		case "stamina": stats.setStamina(stats.getStamina()-1.0); return stats.getStamina() == 0.0 ? false : true;
		case "constitution": stats.setConstitution(stats.getConstitution()-1.0); return stats.getConstitution() == 0.0 ? false : true;
		case "intelligence": stats.setIntelligence(stats.getIntelligence()-1.0); return stats.getIntelligence() == 0.0 ? false : true;
		case "spirit": stats.setSpirit(stats.getSpirit()-1.0); return stats.getSpirit() == 0.0 ? false : true;
		}
		
		return true;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public int getRemainingPoints(){
		return this.remainingPoints;
	}
	
	public double getStat(String stat){
		switch(stat){
		case "strength": return stats.getStrength();
		case "stamina": return stats.getStamina();
		case "constitution": return stats.getConstitution();
		case "intelligence": return stats.getIntelligence();
		case "spirit": return stats.getSpirit();
		default:
			return 0.0;
		}
	}
	
	public Character getCharacter(){
		this.stats.updateDependableStats();
		Character temp = new Character();
		temp.setBaseStats(stats);
		temp.updateModifiedStats();
		temp.setName(name);
		return temp;
	}
	
	public boolean isReady(){
		if(!this.name.equals("") && !this.path.equals("") && remainingPoints == 0){
			return true;
		}
		return false;
	}

}
