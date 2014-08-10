package character;

import item.Bag;
import item.Item;
import item.WearableItem;
import item.WieldableItem;
import blocks.CharacterStatBlock;
import blocks.EquipmentBlock;
import blocks.StatBlock;


public class Character {
	
	//Character's name
	private String name;
	
	//Character's base stats
	private CharacterStatBlock baseStats;
	
	//Character's total (aka, modified) stats
	//used for everything combat etc... related
	private CharacterStatBlock modifiedStats;
	
	//Character's equipment
	private EquipmentBlock equipment;
	
	//Character's bag
	private Bag bag;
	
	
	//default constructor
	//use for character gen?
	public Character(){
		this.name = "";
		this.baseStats = new CharacterStatBlock();
		this.equipment = new EquipmentBlock();
		updateModifiedStats();
		this.bag = new Bag();
	}
	
	//full argument constructor
	//used when loading a character from storage?
	public Character(String name, CharacterStatBlock baseStats,
			CharacterStatBlock modifiedStats, EquipmentBlock equipment, Bag bag) {
		super();
		this.name = name;
		this.baseStats = baseStats;
		this.modifiedStats = modifiedStats;
		this.equipment = equipment;
		this.bag = bag;
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
		
		modifiedStats = new CharacterStatBlock(str, sta, con, intel, spi, baseStats.getLevel(), baseStats.getExperience(), baseStats.getLevelBoundry());
	}
	
	/*public boolean placeInBag(WearableItem item){
		return this.bag.placeItem(item);
	}
	
	public boolean placeInBag(WieldableItem item){
		return this.bag.placeItem(item);
	}*/
	
	public boolean placeInBag(Item item){
		return this.bag.placeItem(item);
	}
	
	/*public boolean equipItem(WearableItem item){
		return this.equipment.equipItem(item);
	}
	
	public boolean equipItem(WieldableItem item){
		return this.equipment.equipItem(item);
	}*/
	
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
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
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

	@Override
	public String toString() {
		return "Character [\n\tname = " + name + ",\n\tbaseStats = " + baseStats
				+ ",\n\tmodifiedStats = " + modifiedStats + ",\n\tequipment = "
				+ equipment + ",\n\tbag = " + bag + "\n]\n" + equipment.getDefenceBlock().toString();
	}
	
	
	
}
