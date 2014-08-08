package item;

import enums.ItemType;
import blocks.WeaponStatBlock;

public class WieldableItem extends Item{

	private WeaponStatBlock stats;
	
	public WieldableItem(){
		super();
		this.stats = new WeaponStatBlock();
	}
	
	public WieldableItem(String name, int slotNum, double strength, double stamina, double constitution, double intelligence, double spirit, double weight, int level, ItemType type, int handsRequired, double minDamage, double maxDamage) {
		super(name, slotNum);
		this.stats = new WeaponStatBlock(strength, stamina, constitution, intelligence, spirit, weight, level, type, handsRequired, minDamage, maxDamage);
	}

	public WeaponStatBlock getStats() {
		return this.stats;
	}

	public void setStats(WeaponStatBlock stats) {
		this.stats = stats;
	}

	@Override
	public String toString() {
		return "WieldableItem [\n\t" + super.toString() + ",\n\tstats=" + stats + "\n]";
	}
	
	
	
}
