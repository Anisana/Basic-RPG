package item;

import blocks.StatBlock;
import enums.ItemType;

public abstract class Item {
	
	/*
	 * public static final ints used to represent slot numbers
	 */
	//wearable
	public static final int HELM = 0;
	public static final int SHOULDERS = 1;
	public static final int AMULET = 2;
	public static final int CHEST = 3;
	public static final int BRACERS = 4;
	public static final int GLOVES = 5;
	public static final int BELT = 6;
	public static final int GRIEVES = 7;
	public static final int BOOTS = 8;
	public static final int RING = 9;
	public static final int SHIELD = 10;
	
	//wieldable
	public static final int BOW = 0;
	public static final int SWORD = 1;
	public static final int MACE = 2;
	public static final int STAFF = 3;
	public static final int WAND = 4;
	public static final int DAGGER = 5;
	public static final int CROSSBOW = 6;
	
	private static final ItemType[] wearableType = {ItemType.HELM, ItemType.SHOULDERS, ItemType.AMULET, 
		ItemType.CHEST, ItemType.BRACERS, ItemType.GLOVES, 
		ItemType.BELT, ItemType.GRIEVES, ItemType.BOOTS, ItemType.RING, null, null, null, ItemType.SHIELD};
	
	private static final int[] wearableSlotNum = {0, 1, 2, 3, 4, 5 ,6 , 7, 8, 9, 13};
	
	private static final String[] wearableString = {"Helm", "Shoulders", "Amulet", 
		"Chest", "Bracers", "Gloves", "Belt", "Grieves", "Boots", "Ring", null, null, null, "Shield"};
	
	private static final ItemType[] wieldableType = { ItemType.BOW, ItemType.SWORD, 
		ItemType.MACE, ItemType.STAFF, ItemType.WAND, ItemType.DAGGER, ItemType.CROSSBOW};
	
	private static final int wieldableSlotNum[] = {11, 12, 12, 11, 12, 12, 11};
	
	private static final int handsRequired[] = {2, 1, 1, 2, 1, 1, 2};
	
	private static final String[] wieldableString = {"Bow", "Sword", 
		"Mace", "Staff", "Wand", "Dagger", "Crossbow"};

	private String name;
	private int slotNum;
	
	public Item(){
		this.name = " ";
		this.slotNum = 0;
	}

	public Item(String name, int slotNum) {
		this.name = name;
		this.slotNum = slotNum;
	}

	public String getName() {
		return name;
	}
	
	public int getSlotNum() {
		return slotNum;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSlotNum(int slotNum) {
		this.slotNum = slotNum;
	}

	@Override
	public String toString() {
		return "Item [name = " + name + ", slotNum = " + slotNum + "]";
	}
	
	/*
	 * Static methods
	 */
	public static ItemType getWearableType(int type){
		if(type == 10 || type == 11 || type == 12) type = 13;
		return wearableType[type];
	}
	
	public static int getWearableSlotNum(int type){
		return wearableSlotNum[type];
	}
	
	public static String getWearableString(int type){
		return wearableString[type];
	}
	
	
	public static ItemType getWieldableType(int type){
		return wieldableType[type];
	}
	
	public static int getHandsRequired(int type){
		return handsRequired[type];
	}
	
	public static int getWieldableSlotNum(int type){
		return wieldableSlotNum[type];
	}
	
	public static String getWieldableString(int type){
		return wieldableString[type];
	}

	
	
	public abstract StatBlock getStats();
	
}
