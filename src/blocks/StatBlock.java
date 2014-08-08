package blocks;

public class StatBlock {
	
	// Affects Focus total and mêlée damage (and carrying capacity?)
	private double strength;
	
	// Affects Focus regeneration rate
	private double stamina;
	
	// Affects Health total and regeneration rate
	private double constitution;
	
	// Affects Mana total and spell damage
	private double intelligence;
	
	// Affects Mana regeneration rate
	private double spirit;
	
	// The level of the character, monster or item
	// This is here because the level of something affects some/most of its stats
	private int level;

	public StatBlock(){
		this.strength = 0;
		this.stamina = 0;
		this.constitution = 0;
		this.intelligence = 0;
		this.spirit = 0;
		this.level = 1;
	}
	
	public StatBlock(double strength, double stamina, double constitution, double intelligence, double spirit, int level) {
		setStrength(strength);
		setStamina(stamina);
		setConstitution(constitution);
		setIntelligence(intelligence);
		setSpirit(spirit);
		this.level = level;
	}

	public double getStrength() {
		return strength;
	}

	public void setStrength(double strength) {
		if(strength <= 0){
			strength = 0;
		}
		this.strength = strength;
		this.strength = Math.floor(strength * 100) / 100;
	}

	public double getStamina() {
		return stamina;
	}

	public void setStamina(double stamina) {
		if(stamina <= 0){
			stamina = 0;
		}
		this.stamina = stamina;
		this.stamina = Math.floor(stamina * 100) / 100;
	}

	public double getConstitution() {
		return constitution;
	}

	public void setConstitution(double constitution) {
		if(constitution <= 0){
			constitution = 0;
		}
		this.constitution = constitution;
		this.constitution = Math.floor(constitution * 100) / 100;
	}

	public double getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(double intelligence) {
		if(intelligence <= 0){
			intelligence = 0;
		}
		this.intelligence = intelligence;
		this.intelligence = Math.floor(intelligence * 100) / 100;
	}

	public double getSpirit() {
		return spirit;
	}

	public void setSpirit(double spirit) {
		if(spirit <= 0){
			spirit = 0;
		}
		this.spirit = spirit;
		this.spirit = Math.floor(spirit * 100) / 100;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getHighestStatString(){
		double stat = this.strength;
		String statString = "Strength";
		
		if(this.stamina > stat){
			stat = this.stamina;
			statString = "Stamina";
		}
		if(this.constitution > stat){
			stat = this.constitution;
			statString = "Constitution";
		}
		if(this.intelligence > stat){
			stat = this.intelligence;
			statString = "Intelligence";
		}
		if(this.spirit > stat){
			stat = this.spirit;
			statString = "Spirit";
		}
		
		return statString;
	}
	
	public String getGUIStatString(){
		String temp = "";
		
		if(strength != 0.0){
			temp += "Strength: " + strength;
		}
		if(stamina != 0.0){
			temp += "\nStamina: " + stamina;
		}
		if(constitution != 0.0){
			temp += "\nConstitution: " + constitution;
		}
		if(intelligence != 0.0){
			temp += "\nIntelligence: " + intelligence;
		}
		if(spirit != 0.0){
			temp += "\nSpirit: " + spirit;
		}
		
		return temp;
	}

	@Override
	public String toString() {
		return "StatBlock [strength = " + strength + ", stamina = " + stamina
				+ ", constitution = " + constitution + ", intelligence = "
				+ intelligence + ", spirit = " + spirit + ", level = " + level
				+ "]";
	}
	
	
	
}
