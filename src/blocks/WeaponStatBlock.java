package blocks;

import enums.DamageType;
import enums.ItemType;

public class WeaponStatBlock extends ItemStatBlock {
	
	// The number of hands required to wield the weapon; either 1 or 2, dependent upon the weapon type
	private int handsRequired;
	
	// The type of damage done by the weapon
	private DamageType damageType;
	
	// The minimum base damage done by the weapon, affected by the weapon level
	private double minDamage;
	
	// The maximum base damage done by the weapon, affected by the weapon level
	private double maxDamage;

	public WeaponStatBlock(){
		super();
		this.handsRequired = 0;
		this.minDamage = 0.55d;
		this.maxDamage = 1.65d;
	}
	
	public WeaponStatBlock(double strength, double stamina, double constitution, double intelligence, double spirit, double weight, int level, ItemType type, int handsRequired, double minDamage, double maxDamage) {
		super(strength, stamina, constitution, intelligence, spirit, weight, level, type);
		this.handsRequired = handsRequired;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}

	public int getHandsRequired() {
		return handsRequired;
	}

	public void setHandsRequired(int handsRequired) {
		this.handsRequired = handsRequired;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}

	public double getMinDamage() {
		return minDamage;
	}

	public void setMinDamage(double minDamage) {
		this.minDamage = Math.floor(minDamage * 100) / 100;
	}

	public double getMaxDamage() {
		return maxDamage;
	}

	public void setMaxDamage(double maxDamage) {
		this.maxDamage = Math.floor(maxDamage * 100) / 100;
	}
	
	public double getDamageRange(){
		return this.maxDamage - this.minDamage;
	}

	@Override
	public String toString() {
		return "WeaponStatBlock [\n\t\t"+ super.toString() +",\n\t\thandsRequired=" + handsRequired
				+ ",\n\t\tdamageType=" + damageType + ",\n\t\tminDamage=" + minDamage
				+ ",\n\t\tmaxDamage=" + maxDamage + "\n\t]";
	}
	
	
	
}
