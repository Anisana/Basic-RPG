package blocks;

import item.Item;
import item.WearableItem;
import item.WieldableItem;

import java.util.Arrays;

//TODO unequip item methods
public class EquipmentBlock {
	
	private Item[] equipment = new Item[14];
	
	/* Full list of all slots on the character's body
	WearableItem helm		= body[0];
	WearableItem shoulder 	= body[1];
	WearableItem amulet 	= body[2];
	WearableItem chest		= body[3];
	WearableItem bracer 	= body[4];
	WearableItem glove 		= body[5];
	WearableItem belt 		= body[6];
	WearableItem grieves	= body[7];
	WearableItem boot 		= body[8];
	WearableItem ring 1 	= body[9];
	WearableItem ring 2 	= body[10]; //always sent as slot 9
	
	WieldableItem both		= hands[0 - 11];
	WieldableItem main Hand	= hands[1 - 12];
	WieldableItem off Hand	= hands[2 - 13];*/
	
	
	
	public EquipmentBlock(){
		for(int i = 0; i < equipment.length; i++){
			equipment[i] = null;
		}
	}
	
	public StatBlock getEquipmentStats(){
		double str = 0, sta = 0, con = 0, intel = 0, spi = 0;
		
		for(int x = 0; x < equipment.length; x++){
			if(equipment[x] != null){
				str += equipment[x].getStats().getStrength();
				sta += equipment[x].getStats().getStamina();
				con += equipment[x].getStats().getConstitution();
				intel += equipment[x].getStats().getIntelligence();
				spi += equipment[x].getStats().getSpirit();
			}
		}
		
		return new StatBlock(str, sta, con, intel, spi, 1);
	}
	
	public DefenceBlock getDefenceBlock(){
		DefenceBlock block = new DefenceBlock();
		WearableItem tempItem;
		for(Item i : equipment){
			if(i != null){
				try{
					tempItem = (WearableItem) i;
					block.addArmour(tempItem.getStats().getArmourValue());
					block.addResist(tempItem.getStats().getResistType(), tempItem.getStats().getResistValue());
				} catch(ClassCastException e){
					
				}
			}
		}
		
		return block;
	}
	
	public boolean equipItem(Item item){
		try{
			return equipItem((WearableItem) item);
		} catch (ClassCastException e){
			return equipItem((WieldableItem) item);
		}
	}
	
	//body
	public boolean equipItem(WearableItem newItem){
		int slotNum = newItem.getSlotNum();
		if(slotNum == 9 && equipment[slotNum] != null){
			if(equipment[10] == null){
				equipment[10] = newItem;
				return true;
			}
		}
		if(slotNum == 13 && equipment[11] == null && equipment[slotNum] == null){
			equipment[slotNum] = newItem;
			return true;
		}
		if(equipment[slotNum] == null){
			equipment[slotNum] = newItem;
			return true;
		}
		return false;
	}
	
	
	//hands	
	public boolean equipItem(WieldableItem newItem){
		int slotNum = newItem.getSlotNum();
		if(slotNum == 11 && equipment[11] == null){
			if(equipment[12] == null && equipment[13] == null){
				equipment[11] = newItem;
				return true;
			}
		}
		else{
			if(equipment[11] == null){
				if(equipment[slotNum] == null){
					equipment[slotNum] = newItem;
					return true;
				}
			}
		}
		return false;
	}
	
	public Item[] getEquipment(){
		return this.equipment;
	}
	
	public WieldableItem getWeapon(){
		if(equipment[11] != null){
			return (WieldableItem) equipment[11];
		}
		return (WieldableItem) equipment[12];
	}

	@Override
	public String toString() {
		return "EquipmentBlock [\n\t\tbody = [" + bodyString() + "],\n\t\thands = ["
				+ handsString() + "]\n\t]";
	}
	
	private String bodyString(){
		String retVal = "";
		Item i;
		for(int count = 0; count < 11; count++){
			i = equipment[count];
			if(!retVal.equals("")){
				retVal += ", ";
			}
			if(i != null) retVal += i.getSlotNum() + "=" + i.getName();
		}
		return retVal;
	}
	
	private String handsString(){
		String retVal = "";
		Item i;
		for(int count = 11; count < equipment.length; count++){
			i = equipment[count];
			if(!retVal.equals("")){
				retVal += ", ";
			}
			if(i != null) retVal += i.getSlotNum() + "=" + i.getName();
		}
		return retVal;
	}
	
	
	
}