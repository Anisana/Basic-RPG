package blocks;

import enums.ItemType;

public class ItemStatBlock extends StatBlock {

	// The weight of the item, affected by the material of the armour or the type of weapon
	private double weight;
	
	// The type of the item
	private ItemType type;
	
	public ItemStatBlock(){
		super();
		this.weight = 0;
		this.type = null;
	}
	
	public ItemStatBlock(double strength, double stamina, double constitution, double intelligence, double spirit, double weight, int level, ItemType type) {
		super(strength, stamina, constitution, intelligence, spirit, level);
		this.weight = weight;
		this.type = type;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ItemStatBlock [\n\t\t\t" + super.toString() + 
				",\n\t\t\tweight=" + weight + ",\n\t\t\ttype=" + type + "\n\t\t]";
	}
	
	
	
}
