package item;

import enums.ArmourType;
import enums.ItemType;
import blocks.ArmourStatBlock;

public class WearableItem extends Item{
	
	private ArmourStatBlock stats;
	
	public WearableItem(){
		super();
		this.stats = new ArmourStatBlock();
	}
	
	public WearableItem(String name, int slotNum, double strength, double stamina, double constitution, double intelligence, double spirit, double weight, int level, ItemType type, ArmourType armourMaterial, double baseArmourValue) {
		super(name, slotNum);
		this.stats = new ArmourStatBlock(strength, stamina, constitution, intelligence, spirit, weight, level, type, armourMaterial, baseArmourValue);
	}

	public ArmourStatBlock getStats() {
		return this.stats;
	}

	public void setStats(ArmourStatBlock stats) {
		this.stats = stats;
	}

	@Override
	public String toString() {
		return "WearableItem [\n\t" + super.toString() + ",\n\tstats = " + stats + "\n]";
	}
	
	
	
}
