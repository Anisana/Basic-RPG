package unit.monster;

import unit.NPC;
import unit.Unit;
import item.Item;
import blocks.CharacterStatBlock;
import blocks.EquipmentBlock;
import blocks.MonsterStatBlock;
import blocks.StatBlock;

public class Monster extends Unit implements NPC{
	
	private MonsterStatBlock baseStats;
	private MonsterStatBlock modifiedStats;
	private EquipmentBlock equipment;

	public Monster() {
		super();
		this.baseStats = new MonsterStatBlock();
		this.equipment = new EquipmentBlock();
		updateModifiedStats();
	}
	
	public Monster(String name, int slotNum, double strength, double stamina, double constitution, double intelligence, double spirit, int level) {
		super(name);
		this.baseStats = new MonsterStatBlock(strength, stamina, constitution, intelligence, spirit, level);
		this.equipment = new EquipmentBlock();
		updateModifiedStats();
	}
	
	public void updateModifiedStats(){
		StatBlock temp = equipment.getEquipmentStats();
		double str = baseStats.getStrength() + temp.getStrength();
		double sta = baseStats.getStamina() + temp.getStamina();
		double con = baseStats.getConstitution() + temp.getConstitution();
		double intel = baseStats.getIntelligence() + temp.getIntelligence();
		double spi = baseStats.getSpirit() + temp.getSpirit();
		
		modifiedStats = new MonsterStatBlock(str, sta, con, intel, spi, baseStats.getLevel());
	}
	
	public boolean equipItem(Item item){
		if(this.equipment.equipItem(item)){
			updateModifiedStats();
			return true;
		}
		return false;
	}

	/*public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}*/

	public MonsterStatBlock getBaseStats() {
		return baseStats;
	}

	public void setBaseStats(MonsterStatBlock stats) {
		this.baseStats = stats;
	}

	public MonsterStatBlock getModifiedStats() {
		return modifiedStats;
	}

	public void setModifiedStats(MonsterStatBlock modifiedStats) {
		this.modifiedStats = modifiedStats;
	}

	public EquipmentBlock getEquipment() {
		return equipment;
	}

	public void setEquipment(EquipmentBlock equipment) {
		this.equipment = equipment;
	}

	@Override
	public String toString() {
		return "Monster [\n\t" + super.toString() + ",\n\tbaseStats = " + baseStats
				+ ",\n\tmodifiedStats = " + modifiedStats + ",\n\tequipment = "
				+ equipment + "\n]";
	}

	@Override
	public boolean isNPC() {
		return true;
	}

	
	
	
	
	

}
