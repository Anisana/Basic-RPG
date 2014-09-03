package unit.character;

import java.util.ArrayList;

import enums.SkillType;
import path.Path;
import path.Skill;
import gui.CharacterVitalStatisticsPanel;
import item.Bag;
import item.Item;
import unit.Unit;
import blocks.CharacterStatBlock;
import blocks.EquipmentBlock;
import blocks.StatBlock;


public class Character extends Unit{
	
	//Character's base stats
	private CharacterStatBlock baseStats;
	
	//Character's total (aka, modified) stats
	//used for everything combat etc... related
	private CharacterStatBlock modifiedStats;
	
	//Character's equipment
	private EquipmentBlock equipment;
	
	//Character's bag
	private Bag bag;
	
	//active skills
	private ArrayList<Skill> activeSkills;
	
	private CharacterVitalStatisticsPanel vsPanel;
	
	//default constructor
	//use for character gen?
	public Character(){
		super();
		//this.name = "";
		this.baseStats = new CharacterStatBlock();
		this.equipment = new EquipmentBlock();
		updateModifiedStats();
		this.bag = new Bag();
		this.activeSkills = new ArrayList<Skill>();
		this.vsPanel = new CharacterVitalStatisticsPanel(this);
	}
	
	//full argument constructor
	//used when loading a character from storage?
	/*public Character(String name, CharacterStatBlock baseStats,
			CharacterStatBlock modifiedStats, EquipmentBlock equipment, Bag bag) {
		super(name);
		//this.name = name;
		this.baseStats = baseStats;
		this.modifiedStats = modifiedStats;
		this.equipment = equipment;
		this.bag = bag;
		this.vsPanel = new CharacterVitalStatisticsPanel(this);
	}*/
	
	public Character(String name, CharacterStatBlock baseStats,
			CharacterStatBlock modifiedStats, EquipmentBlock equipment,
			Bag bag, Path path, ArrayList<Skill> activeSkills) {
		super(name, path);
		this.baseStats = baseStats;
		this.modifiedStats = modifiedStats;
		this.equipment = equipment;
		this.bag = bag;
		this.activeSkills = activeSkills;
		this.vsPanel = new CharacterVitalStatisticsPanel(this);
	}


	//pulls all the stats from the equipment and adds them
	//to the base stats
	public void updateModifiedStats(){
		StatBlock temp = equipment.getEquipmentStats();
		double str = baseStats.getStrength() + temp.getStrength();
		double sta = baseStats.getStamina() + temp.getStamina();
		double con = baseStats.getConstitution() + temp.getConstitution();
		double intel = baseStats.getIntelligence() + temp.getIntelligence();
		double spi = baseStats.getSpirit() + temp.getSpirit();
		
		modifiedStats = new CharacterStatBlock(str, sta, con, intel, spi, baseStats.getLevel(), baseStats.getExperience(), 
				baseStats.getLevelBoundary(), baseStats.getMovementSpeed());
	}
	
	public boolean placeInBag(Item item){
		return this.bag.placeItem(item);
	}
	
	public boolean equipItem(Item item){
		if(this.equipment.equipItem(item)){
			updateModifiedStats();
			return true;
		}
		return false;
	}
	
	public void setBag(Bag bag){
		this.bag = bag;
	}
	
	public Bag getBag(){
		return this.bag;
	}
	
	public CharacterStatBlock getBaseStats(){
		return this.baseStats;
	}
	
	public void setBaseStats(CharacterStatBlock block){
		this.baseStats = block;
	}
	
	public CharacterStatBlock getModifiedStats(){
		return this.modifiedStats;
	}
	
	public void setModifiedStats(CharacterStatBlock block){
		this.modifiedStats = block;
	}
	
	public EquipmentBlock getEquipment(){
		return this.equipment;
	}
	
	public void setEquipment(EquipmentBlock block){
		this.equipment = block;
	}
	
	public ArrayList<Skill> getActiveSkills() {
		return activeSkills;
	}

	public void setActiveSkills(ArrayList<Skill> activeSkills) {
		this.activeSkills = activeSkills;
	}

	public CharacterVitalStatisticsPanel getVSPanel(){
		return this.vsPanel;
	}
	
	public void setActiveSkills(/*list of skills*/){
		this.activeSkills = new ArrayList<Skill>();
		int i = 0;
		for(Skill s : this.getPath().getSkills()){
			if(s.getType() == SkillType.ATTACK || s.getType() == SkillType.SPELL){
				//System.out.println("Adding skill: " + s);
				this.activeSkills.add(s);
				i++;
				
				if(i == 10) return;
			}
		}
	}

	@Override
	public String toString() {
		return "Character [\n\t"+ super.toString() + ",\n\tbaseStats = " + baseStats
				+ ",\n\tmodifiedStats = " + modifiedStats + ",\n\tequipment = "
				+ equipment + ",\n\tbag = " + bag + "\n]\n" + equipment.getDefenceBlock().toString();
	}

	
//Combat related methods
	public boolean hasSufficientResources(int skillNumber){
		switch(activeSkills.get(skillNumber).getResource()){
		case "focus":
			if(modifiedStats.getCurrentFocus() >= activeSkills.get(skillNumber).getResourceCost()) return true;
		case "mana":
			if(modifiedStats.getCurrentMana() >= activeSkills.get(skillNumber).getResourceCost()) return true;
		case "none":
			return true;
		}
		return false;
	}
	
	public void useResources(int skillNumber){
		switch(activeSkills.get(skillNumber).getResource()){
		case "focus":
			modifiedStats.setCurrentFocus(modifiedStats.getCurrentFocus() - activeSkills.get(skillNumber).getResourceCost());
			break;
		case "mana":
			modifiedStats.setCurrentMana(modifiedStats.getCurrentMana() - activeSkills.get(skillNumber).getResourceCost());
			break;
		}
	}
	
	@Override
	public double getDamage(int skillNumber){
		Skill tempSkill = activeSkills.get(skillNumber);
		double damage = 0;
		
		if(tempSkill != null){
			damage = tempSkill.rollDamage(this);
		}
		
		
		return damage;
	}

	@Override
	public boolean takeDamage(double baseDamage) {
		//TODO implement armour
		
		this.modifiedStats.setCurrentHealth(this.modifiedStats.getCurrentHealth() - baseDamage);
		
		if(this.modifiedStats.getCurrentHealth() <= 0.0){
			this.modifiedStats.setCurrentHealth(0.0);
			return false; //dead
		}
		
		return true; //alive
	}
	
	@Override
	public void updateVSPanel(){
		this.vsPanel.updateCharSheet();
	}

	@Override
	public boolean giveExperience(int exp) {
		boolean temp = this.baseStats.giveExp(exp);
		this.modifiedStats.giveExp(exp);
		//updateModifiedStats();
		return temp;
	}

	@Override
	public void regenResources() {
		//take into account any fast regen things?
		
		this.modifiedStats.setCurrentHealth(this.modifiedStats.getCurrentHealth() + this.modifiedStats.getHealthRegenRate());
		this.modifiedStats.setCurrentFocus(this.modifiedStats.getCurrentFocus() + this.modifiedStats.getFocusRegenRate());
		this.modifiedStats.setCurrentMana(this.modifiedStats.getCurrentMana() + this.modifiedStats.getManaRegenRate());
	}
	
	@Override
	public void cooldownSkills(){
		for(Skill s : activeSkills){
			s.cooldown();
		}
	}
}
