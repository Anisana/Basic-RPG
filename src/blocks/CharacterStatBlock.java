package blocks;

public class CharacterStatBlock extends StatBlock {
	
	// Health total
	private double totalHealth;
	private double currentHealth;
	
	private double healthRegenRate;

	// Resource pool used for mêlée skills
	private double totalFocus;
	private double currentFocus;
	
	private double focusRegenRate;

	// Resource pool used for casting spells
	private double totalMana;
	private double currentMana;
	
	private double manaRegenRate;
	
	// Total carrying capacity (includes worn items and everything in the character's bag)
	private double carryCapacity;
	
	// Total experience points
	private int experience;
	
	// The amount of experience that will cause the character to level up
	private int levelBoundry;

	public CharacterStatBlock(){
		super();
		updateDependableStats();
		this.experience = 0;
		this.levelBoundry = 100;
	}
	
	public CharacterStatBlock(double strength, double stamina, double constitution, double intelligence, double spirit, int level, int experience, int levelBoundry) {
		super(strength, stamina, constitution, intelligence, spirit, level);
		updateDependableStats();
		this.experience = experience;
		this.levelBoundry = levelBoundry;
	}
	
	public void updateDependableStats(){
		calcMana();
		calcFocus();
		calcHealth();
		calcRegens();
		calcCarryCapacity();
	}

	private void calcMana() {
		this.totalMana = (getLevel()*5)+(getIntelligence()*5);
		this.totalMana = Math.floor(totalMana * 100) / 100;
		this.currentMana = totalMana;
	}

	private void calcFocus() {
		this.totalFocus = (getLevel()*5)+(getStrength()*5);
		this.totalFocus = Math.floor(totalFocus * 100) / 100;
		this.currentFocus = totalFocus;
	}

	private void calcHealth() {
		this.totalHealth = (getLevel()*10)+(getConstitution()*10);
		this.totalHealth = Math.floor(totalHealth * 100) / 100;
		this.currentHealth = totalHealth;
	}
	
	private void calcRegens(){
		this.manaRegenRate = (getLevel()*5)+(getSpirit()*0.2);
		this.manaRegenRate = Math.floor(manaRegenRate * 100) / 100;
		
		this.focusRegenRate = (getLevel()*5)+(getStamina()*0.2);
		this.focusRegenRate = Math.floor(focusRegenRate * 100) / 100;
		
		this.healthRegenRate = (getLevel()*5)+(getConstitution()*0.2);
		this.healthRegenRate = Math.floor(healthRegenRate * 100) / 100;
	}
	
	private void calcCarryCapacity() {
		
	}
	
	public void giveExp(double exp){
		experience += exp;
		if(experience >= levelBoundry){
			levelUp();
			levelBoundry += getLevel()*100;
		}
	}
	
	private void levelUp(){
		setLevel(getLevel()+1);
	}

//getters and setters	

	public double getTotalHealth() {
		return totalHealth;
	}

	public void setTotalHealth(double totalHealth) {
		this.totalHealth = Math.floor(totalHealth * 100) / 100;
	}

	public double getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(double currentHealth) {
		this.currentHealth = Math.floor(currentHealth * 100) / 100;
	}

	public double getTotalFocus() {
		return totalFocus;
	}

	public void setTotalFocus(double totalFocus) {
		this.totalFocus = Math.floor(totalFocus * 100) / 100;
	}

	public double getCurrentFocus() {
		return currentFocus;
	}

	public void setCurrentFocus(double currentFocus) {
		this.currentFocus = Math.floor(currentFocus * 100) / 100;
		
	}

	public double getTotalMana() {
		return totalMana;
	}

	public void setTotalMana(double totalMana) {
		this.totalMana = Math.floor(totalMana * 100) / 100;
	}

	public double getCurrentMana() {
		return currentMana;
	}

	public void setCurrentMana(double currentMana) {
		this.currentMana = Math.floor(currentMana * 100) / 100;
	}

	public double getHealthRegenRate() {
		return healthRegenRate;
	}

	public void setHealthRegenRate(double healthRegenRate) {
		this.healthRegenRate = healthRegenRate;
	}

	public double getFocusRegenRate() {
		return focusRegenRate;
	}

	public void setFocusRegenRate(double focusRegenRate) {
		this.focusRegenRate = focusRegenRate;
	}

	public double getManaRegenRate() {
		return manaRegenRate;
	}

	public void setManaRegenRate(double manaRegenRate) {
		this.manaRegenRate = manaRegenRate;
	}

	public double getCarryCapacity() {
		return carryCapacity;
	}

	public void setCarryCapacity(double carryCapacity) {
		this.carryCapacity = Math.floor(carryCapacity * 100) / 100;
	}
	
	public int getExperience(){
		return this.experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public int getLevelBoundry(){
		return this.levelBoundry;
	}

	public void setLevelBoundry(int levelBoundry) {
		this.levelBoundry = levelBoundry;
	}

	@Override
	public String toString() {
		return "CharacterStatBlock [\n\t\t" + super.toString()
				+ ",\n\t\ttotal health = " + totalHealth + ", current health = " + currentHealth + ", healthRegenRate = " + healthRegenRate
				+ ",\n\t\ttotal focus = " + totalFocus + ", current focus = " + currentFocus + ", focusRegenRate = " + focusRegenRate
				+ ",\n\t\ttotal mana = " + totalMana + ", current mana = " + currentMana + ", manaRegenRate = " + manaRegenRate
				+ ",\n\t\tcarryCapacity = " + carryCapacity
				+ ",\n\t\texperience = " + experience + ", levelBoundry = "
				+ levelBoundry + "\n\t]";
	}
	
}
