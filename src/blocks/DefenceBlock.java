package blocks;

import java.util.HashMap;

import enums.ResistType;

public class DefenceBlock {
	
	private double armourValue;
	private HashMap<ResistType, Double> resists;

	public DefenceBlock() {
		this.armourValue = 0.0;
		this.resists = new HashMap<>();
	}
	
	public void addArmour(double armour){
		this.armourValue += armour;
		this.armourValue = Math.floor(this.armourValue * 100) / 100;
	}
	
	public void addResist(ResistType resist, double value){
		if(!resists.containsKey(resist)){
			resists.put(resist, value);
			return;
		}
		double newVal = resists.get(resist) + value;
		newVal = Math.floor(newVal * 100) / 100;
		resists.put(resist, newVal);
	}

	public double getArmourValue() {
		return armourValue;
	}

	public HashMap<ResistType, Double> getResists() {
		return resists;
	}
	
	public String getGUIResistString(){
		String temp = "";
		if(resists.containsKey(ResistType.ALL)){
			temp += ResistType.ALL + ": " + resists.get(ResistType.ALL) + "\n";
		}
		if(resists.containsKey(ResistType.PHYSICAL)){
			temp += ResistType.PHYSICAL + ": " + resists.get(ResistType.PHYSICAL) + "\n";
		}
		if(resists.containsKey(ResistType.BLUDGEONING)){
			temp += ResistType.BLUDGEONING + ": " + resists.get(ResistType.BLUDGEONING) + "\n";
		}
		if(resists.containsKey(ResistType.SLASHING)){
			temp += ResistType.SLASHING + ": " + resists.get(ResistType.SLASHING) + "\n";
		}
		if(resists.containsKey(ResistType.PIERCING)){
			temp += ResistType.PIERCING + ": " + resists.get(ResistType.PIERCING) + "\n";
		}
		if(resists.containsKey(ResistType.MAGIC)){
			temp += ResistType.MAGIC + ": " + resists.get(ResistType.MAGIC) + "\n";
		}
		if(resists.containsKey(ResistType.ARCANE)){
			temp += ResistType.ARCANE + ": " + resists.get(ResistType.ARCANE) + "\n";
		}
		if(resists.containsKey(ResistType.COLD)){
			temp += ResistType.COLD + ": " + resists.get(ResistType.COLD) + "\n";
		}
		if(resists.containsKey(ResistType.FIRE)){
			temp += ResistType.FIRE + ": " + resists.get(ResistType.FIRE) + "\n";
		}
		
		return temp;
	}

	@Override
	public String toString() {
		return "DefenceBlock [armourValue=" + armourValue + ", resists="
				+ resists + "]";
	}
	
	

}
