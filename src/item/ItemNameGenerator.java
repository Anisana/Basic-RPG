package item;

import enums.ItemType;

public class ItemNameGenerator {
	
	private static String prefix = "";
	private static String interfix = "";
	private static String suffix = "";

	public ItemNameGenerator() {
		
	}
	
	public static String genName(WearableItem item){
		switch(item.getStats().getArmourMaterial()){
		case METAL: prefix = "Metal"; break;
		case LEATHER: prefix = "Leather"; break;
		case CLOTH: prefix = "Cloth"; break;
		}
		
		/*if(item.getStats().getType() == ItemType.SHIELD){
			interfix = "Shield";
		}
		else{*/
			interfix = Item.getWearableString(item.getSlotNum());
		//}
		
		suffix = item.getStats().getHighestStatString();
		
		return prefix + " " + interfix + " of " + suffix;
	}
	
	public static String genName(WieldableItem item){
		switch(item.getStats().getDamageType()){
		case PIERCING: prefix = "Piercing"; break;
		case BLUDGEONING: prefix = "Bludgeoning"; break;
		case SLASHING: prefix = "Slashing"; break;
		case FIRE: prefix = "Fire"; break;
		case COLD: prefix = "Cold"; break;
		case ARCANE: prefix = "Arcane"; break;
		}
		
		//interfix = Item.getWieldableString(item.getSlotNum());
		switch(item.getStats().getType()){
		case BOW: interfix = "Bow"; break;
		case SWORD: interfix = "Sword"; break;
		case MACE: interfix = "Mace"; break;
		case STAFF: interfix = "Staff"; break;
		case WAND: interfix = "Wand"; break;
		case DAGGER: interfix = "Dagger"; break;
		case CROSSBOW: interfix = "Crossbow"; break;
		default: break;
		}
		
		//interfix = +"";
		
		suffix = item.getStats().getHighestStatString();
		
		return prefix + " " + interfix + " of " + suffix;
	}

}
