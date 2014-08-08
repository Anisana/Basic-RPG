package item;

import java.util.Random;

import enums.ArmourType;
import enums.DamageType;
import enums.ItemType;
import enums.ResistType;

/**
 * Item Generation class
 * Used once per combat, where it is initialised with certain constants
 * that bound the overall loot for the combat.
 * @author Sam Launchbury
 *
 */
public class ItemGenerator {
	
	//Determines the overall scale of the item stats
	private int level;
	
	private double levelScaling;
	
	//The remaining number of items left in the generator
	private int remainingItems;

	//Default constructor, will enable generation of a single level 1 item
	public ItemGenerator() {
		this.level = 1;
		calcLevelScaling();
		this.remainingItems = 1;
	}

	public ItemGenerator(int level, int remainingItems) {
		super();
		this.level = level;
		calcLevelScaling();
		this.remainingItems = remainingItems;
	}
	
	public void setLevel(int level){
		this.level = level;
		calcLevelScaling();
	}
	
	public int getLevel(){
		return this.level;
	}
	
	private void calcLevelScaling(){
		//convert level into a double for the calculation
		double lvl = new Integer(this.level).doubleValue();
		this.levelScaling = 1.0 + (lvl/10.0);
	}
	
	public Item getNewItem(){
		if(remainingItems == 0){
			return null;
		}
		Random rand = new Random();
		Item item;
		
		if(rand.nextInt(2) == 0){
			item = new WearableItem();
			generateStats((WearableItem) item);
		}
		else{
			item = new WieldableItem();
			generateStats((WieldableItem) item);
		}
		
		/*WearableItem item = new WearableItem();
		generateStats(item)*/
		
		remainingItems--;
		return item;
	}
	
	public Item getNewItem(String type){
		if(remainingItems == 0){
			return null;
		}
		
		Item item;
		
		if(type.equals("wearableItem")){
			item = new WearableItem();
			generateStats((WearableItem) item);
		}
		else{
			item = new WieldableItem();
			generateStats((WieldableItem) item);
		}
		
		remainingItems--;
		return item;
	}
	
	private void generateStats(WearableItem item){
		Random rand = new Random();
		int temp = rand.nextInt(11);
		ItemType type = Item.getWearableType(temp);
		int slotNum = Item.getWearableSlotNum(temp);
		ArmourType armourMaterial = ArmourType.LEATHER;
		ResistType resistType = ResistType.NONE;
		
		switch (rand.nextInt(3)){
		case 0: armourMaterial = ArmourType.METAL; break;
		case 1: armourMaterial = ArmourType.LEATHER; break;
		case 2: armourMaterial = ArmourType.CLOTH; break;
		}
		
		item.setSlotNum(slotNum);
		item.getStats().setLevel(this.level);
		item.getStats().setType(type);
		item.getStats().setArmourMaterial(armourMaterial);
		item.getStats().calcArmourValue(rand.nextDouble()*10*levelScaling);
		
		switch (rand.nextInt(10)){
		case 0: resistType = ResistType.ALL; break;
		case 1: resistType = ResistType.PHYSICAL; break;
		case 2: resistType = ResistType.MAGIC; break;
		case 3: resistType = ResistType.PIERCING; break;
		case 4: resistType = ResistType.BLUDGEONING; break;
		case 5: resistType = ResistType.SLASHING; break;
		case 6: resistType = ResistType.FIRE; break;
		case 7: resistType = ResistType.COLD; break;
		case 8: resistType = ResistType.ARCANE; break;
		case 9: resistType = ResistType.NONE; break;
		}
		
		item.getStats().setResistType(resistType);
		item.getStats().setResistValue(rand.nextDouble()*5*levelScaling);
		
		item.getStats().setStrength(getStat(rand));
		item.getStats().setStamina(getStat(rand));
		item.getStats().setConstitution(getStat(rand));
		item.getStats().setIntelligence(getStat(rand));
		item.getStats().setSpirit(getStat(rand));
		
		item.getStats().setWeight(0.0); //TODO change to be realistic
		
		item.setName(ItemNameGenerator.genName(item));
	}
	
	private void generateStats(WieldableItem item){
		Random rand = new Random();
		int temp = rand.nextInt(7);
		int slotNum = Item.getWieldableSlotNum(temp);
		ItemType type = Item.getWieldableType(temp);
		DamageType damageType = DamageType.PIERCING;
		
		switch (rand.nextInt(7)){
		case 0: damageType = DamageType.PIERCING; break;
		case 1: damageType = DamageType.BLUDGEONING; break;
		case 2: damageType = DamageType.SLASHING; break;
		case 3: damageType = DamageType.FIRE; break;
		case 4: damageType = DamageType.COLD; break;
		case 5: damageType = DamageType.ARCANE; break;
		}
		
		item.setSlotNum(slotNum);
		item.getStats().setLevel(this.level);
		item.getStats().setType(type);
		
		item.getStats().setDamageType(damageType);
		
		double min = (rand.nextDouble()+0.1)*7.5*levelScaling;
		
		item.getStats().setMinDamage(min);
		
		double max = rand.nextDouble()*10.0*levelScaling;
		
		while(max < min){
			max = rand.nextDouble()*10.0*levelScaling;
		}
		
		item.getStats().setMaxDamage(max);
		
		item.getStats().setHandsRequired(Item.getHandsRequired(temp));
		
		item.getStats().setStrength(getStat(rand));
		item.getStats().setStamina(getStat(rand));
		item.getStats().setConstitution(getStat(rand));
		item.getStats().setIntelligence(getStat(rand));
		item.getStats().setSpirit(getStat(rand));
		
		item.getStats().setWeight(0.0); //TODO change to be realistic
		
		item.setName(ItemNameGenerator.genName(item));
	}
	
	private double getStat(Random rand){
		//random number, skewed towards 0
		int tempInt = rand.nextInt(11) - 5;
		//scale the number according to level
		double tempDouble = (new Integer(tempInt).doubleValue()) * levelScaling;
		//truncate to 1 or 2 decimal places
		return Math.floor(tempDouble * 100) / 100;
	}

}
