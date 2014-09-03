package unit.monster;

import path.Path;
import enums.NPCActionType;
import gui.EnemyVitalStatisticsPanel;
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
	
	private EnemyVitalStatisticsPanel vsPanel;

	public Monster() {
		super();
		this.baseStats = new MonsterStatBlock();
		this.equipment = new EquipmentBlock();
		updateModifiedStats();
		this.vsPanel = new EnemyVitalStatisticsPanel(this);
	}
	
	public Monster(String name, int slotNum, double strength, double stamina, double constitution, double intelligence, double spirit, int level) {
		super(name, new Path());
		this.baseStats = new MonsterStatBlock(strength, stamina, constitution, intelligence, spirit, level);
		this.equipment = new EquipmentBlock();
		updateModifiedStats();
		this.vsPanel = new EnemyVitalStatisticsPanel(this);
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
	
	@Override
	public void updateVSPanel(){
		this.vsPanel.updateEnemyPane();
	}
	
	public EnemyVitalStatisticsPanel getVSPanel(){
		return this.vsPanel;
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

	//@Override
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
	public int getBaseExpValue(){
		return this.modifiedStats.getLevel() * 10;
	}

	@Override
	public NPCActionType getAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean takeDamage(double baseDamage) {
		
		//implement armour
		
		this.modifiedStats.setCurrentHealth(this.modifiedStats.getCurrentHealth() - baseDamage);
		
		if(this.modifiedStats.getCurrentHealth() <= 0.0){
			this.modifiedStats.setCurrentHealth(0.0);
			return false; //dead
		}
		
		return true; //alive
	}

	//temp
	@Override
	public double getDamage(int skillNumber){
		if(this.equipment.getWeapon() != null){
			double damage = Math.random() * this.equipment.getWeapon().getStats().getDamageRange();
			damage += this.equipment.getWeapon().getStats().getMinDamage();
			damage *= (1 + this.modifiedStats.getStrength()/100);
			
			return damage;
		}
		System.out.println("Current monster has no weapon");
		return Math.random();
	}

	@Override
	public boolean giveExperience(int exp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void regenResources() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void cooldownSkills(){
		// TODO Auto-generated method stub
	}
	

}
