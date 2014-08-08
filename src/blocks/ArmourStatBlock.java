package blocks;

import enums.ArmourType;
import enums.ItemType;
import enums.ResistType;

public class ArmourStatBlock extends ItemStatBlock {

	// The material the armour is made from
	private ArmourType armourMaterial;
	
	// The armour value of the piece, affected by the material and the level, reduces all damage taken by a low(er) amount
	private double armourValue;
	
	// The type of damage the armour is particularly effective against
	private ResistType resistType;
	
	// The resist value of the piece, reduces damage of the resist type by a high(er) amount
	private double resistValue;
	
	public ArmourStatBlock(){
		super();
		this.armourMaterial = null;
		this.armourValue = 0;
	}
	
	public ArmourStatBlock(double strength, double stamina, double constitution, double intelligence, double spirit, double weight, int level, ItemType type, ArmourType armourMaterial, double baseArmourValue) {
		super(strength, stamina, constitution, intelligence, spirit, weight, level, type);
		this.armourMaterial = armourMaterial;
		calcArmourValue(baseArmourValue);
	}

	public ArmourType getArmourMaterial() {
		return armourMaterial;
	}

	public void setArmourMaterial(ArmourType armourMaterial) {
		this.armourMaterial = armourMaterial;
	}

	public double getArmourValue() {
		return armourValue;
	}

	public void setArmourValue(double armourValue) {
		this.armourValue = armourValue;
	}
	
	public ResistType getResistType() {
		return resistType;
	}

	public void setResistType(ResistType resistType) {
		this.resistType = resistType;
	}

	public double getResistValue() {
		return resistValue;
	}

	public void setResistValue(double resistValue) {
		//this.resistValue = resistValue*((new Integer(getLevel()).doubleValue())/10);
		this.resistValue = Math.floor(resistValue * 100) / 100;
	}

	public void calcArmourValue(double baseArmourValue){
		double lvl = new Integer(getLevel()).doubleValue();
		
		switch (this.armourMaterial){
		case METAL: this.armourValue = baseArmourValue*1.5;  break;
		case LEATHER: this.armourValue = baseArmourValue; break;
		case CLOTH: this.armourValue = baseArmourValue*0.5; break;
		}
		
		this.armourValue = Math.floor(this.armourValue * 100) / 100;
	}

	@Override
	public String toString() {
		return "ArmourStatBlock [\n\t\t" + super.toString() + ",\n\t\tarmourMaterial=" + armourMaterial
				+ ",\n\t\tarmourValue=" + armourValue + ",\n\t\tresistType=" + resistType
				+ ",\n\t\tresistValue=" + resistValue + "\n\t]";
	}
	
	
	
}
