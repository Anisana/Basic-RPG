package blocks;

public class MonsterStatBlock extends StatBlock {
	
	private double health;

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
		this.health = (getLevel()*10)+(getConstitution()*10);
		this.health = Math.floor(health * 100) / 100;
	}

	@Override
	public String toString() {
		return "MonsterStatBlock [\n\t\t" + super.toString() + ",\n\t\thealth=" + health + "\n\t]";
	}

	
	
	

}
