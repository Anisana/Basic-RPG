package blocks;

public class MonsterStatBlock extends StatBlock {
	
	private double totalHealth;
	private double currentHealth;

	public MonsterStatBlock() {
		super();
		this.calcHealth();
	}

	public MonsterStatBlock(double strength, double stamina,
			double constitution, double intelligence, double spirit, int level) {
		super(strength, stamina, constitution, intelligence, spirit, level);
		calcHealth();
	}
	
	private void calcHealth() {
		this.totalHealth = (getLevel()*10)+(getConstitution()*10);
		this.totalHealth = Math.floor(totalHealth * 100) / 100;
		this.currentHealth = totalHealth;
	}

	public double getTotalHealth() {
		return totalHealth;
	}

	public void setTotalHealth(double health) {
		this.totalHealth = health;
	}

	public double getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(double currentHealth) {
		this.currentHealth = Math.floor(currentHealth * 100) / 100;
	}

	@Override
	public String toString() {
		return "MonsterStatBlock [\n\t\t" + super.toString() + ",\n\t\ttotal health = " + totalHealth + ", current health = " + currentHealth + "\n\t]";
	}

	
	
	

}
