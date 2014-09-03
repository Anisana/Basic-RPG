package item;

public class Bag {
	
	private int maxSize = 20; //TODO change when multiple bags are a thing
	//private WearableItem []armour = new WearableItem[20];
	//private WieldableItem []weapons = new WieldableItem[20];
	private Item[] bag = new Item[20];

	public Bag() {
		//initArmour(armour);
		//initWeapons(weapons);
		
		for(int i = 0; i < this.bag.length; i++){
			this.bag[i] = null;
		}
		
	}
	
	/*private void initArmour(WearableItem[] block){
		for(int i = 0; i < block.length; i++){
			block[i] = null;
		}
	}
	
	private void initWeapons(WieldableItem[] block){
		for(int i = 0; i < block.length; i++){
			block[i] = null;
		}
	}

	public boolean placeItem(WearableItem newItem){
		boolean success = false;
		for(int x = 0; x < armour.length; x++){
			if(armour[x] == null){
				armour[x] = newItem;
				success = true;
				x = 20;
			}
		}
		return success;
	}

	public boolean placeItem(WieldableItem newItem){
		boolean success = false;
		for(int x = 0; x < weapons.length; x++){
			if(weapons[x] == null){
				weapons[x] = newItem;
				success = true;
				x = 20;
			}
		}
		return success;
	}*/
	
	public boolean placeItem(Item newItem){
		boolean success = false;
		for(int x = 0; x < bag.length; x++){
			if(bag[x] == null){
				bag[x] = newItem;
				success = true;
				x = 20;
			}
		}
		return success;
	}
	
	public void removeItem(int index){
		this.bag[index] = null;
	}
	
	public Item getItem(int index) {
		if(index >= this.bag.length){
			return null;
		}
		return this.bag[index];
	}
	
	public int getSize(){
		return this.maxSize;
	}
	
	public void setSize(int size){
		this.maxSize = size;
	}
	
	@Override
	public String toString(){
		String retVal = "Bag [";
		int x = 0;
		for(Item i : bag){
			if(!retVal.equals("Bag [")){
				retVal += ", ";
			}
			if(i != null) retVal += x + "=" + i.getName();
			x++;
		}
		return retVal + "]";
	}

	

	/*@Override
	public String toString() {
		return "Bag [\n\t\tarmour = [" + armourString() + "],\n\t\tweapons = ["
				+ weaponString() + "]\n\t]";
	}
	
	private String armourString(){
		String retVal = "";
		int x = 0;
		for(WearableItem i : armour){
			if(!retVal.equals("")){
				retVal += ", ";
			}
			if(i != null) retVal += x + "=" + i.getName();
			x++;
		}
		return retVal;
	}
	
	private String weaponString(){
		String retVal = "";
		int x = 0;
		for(WieldableItem i : weapons){
			if(!retVal.equals("")){
				retVal += ", ";
			}
			if(i != null) retVal += x + "=" + i.getName();
			x++;
		}
		return retVal;
	}*/
	
	
}
