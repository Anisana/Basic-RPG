package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import monster.Monster;
import item.Item;
import item.ItemGenerator;
import item.WearableItem;
import item.WieldableItem;
import character.Character;

public class Console {

	private ArrayList<Character> characters;
	private ArrayList<Item> items = new ArrayList<Item>();

	public Console(ArrayList<Character> characters) {
		this.characters = characters;
		String input = "";
		System.out.println("Console ready for input");
		
		Monster mob = new Monster();
		mob.setName("Test Mob");
		ItemGenerator gen = new ItemGenerator();
		Item temp = gen.getNewItem("wieldableItem");
		System.out.println(temp);
		mob.equipItem(temp);
		System.out.println(mob+"\n");
		
		
		while(true){
			try{
				input = (new java.io.BufferedReader(new java.io.InputStreamReader(System.in))).readLine();
				parseInput(input);
			}
			catch(Exception e){
				e.printStackTrace();
				input = "";
			}
		}

	}


	private void parseInput(String input){
		try{
			if(input.charAt(0) != '/'){
				System.out.println("Invalid command, type /help for help");
				return;
			}
			input = input.replaceFirst("/", " ").trim();
			String[] command = input.split(" ");

			switch(command[0]){
			case "quit": System.out.println("Exiting..."); System.exit(0);
			case "create": create(command); break;
			case "edit": edit(command); break;
			case "print": print(command); break;
			case "help": help(command); break;
			case "give": give(command); break;
			case "equip": equip(command); break;
			case "save": save(command); break;
			case "load": load(command); break;
			default: System.out.println("Error. Type /help <command> for help.");
			}
		} catch(NumberFormatException e){
			System.out.println("Not a valid number");
		} catch(IndexOutOfBoundsException e){
			System.out.println("Invalid index");
			e.printStackTrace();
		}
	}

	/*
	 * Create section
	 */
	private void create(String[] command) throws NumberFormatException{

		switch(command[1]){
		case "char":
		case "character": createCharacter(command); break;
		case "item": createItem(command); break;
		default:
		}

	}

	private void createCharacter(String[] command){
		Character newChar = new Character();
		boolean successful = true;
		if(command.length == 2){
			characters.add(newChar);
			System.out.println("Character created");
			return;
		}
		for(int i = 2; i < command.length; i++){
			switch(command[i]){
			case "-n": newChar.setName(command[++i]); break;
			default:
				System.out.println("Invalid option: '" + command[i++] + "'"); 
				successful = false;
			}
		}

		if(successful){
			characters.add(newChar);
			System.out.println("Character '" + newChar.getName() + "' created");
		}
	}

	private void createItem(String[] command) throws NumberFormatException{
		boolean successful = true;
		ItemGenerator ig = new ItemGenerator();
		if(command.length == 2){
			items.add(ig.getNewItem());
			System.out.println("Item created");
			return;
		}

		for(int i = 2; i < command.length; i++){
			switch(command[i]){
			case "-l": ig.setLevel(Integer.parseInt(command[++i])); break;
			default:
				System.out.println("Invalid option: '" + command[i++] + "'"); 
				successful = false;
			}
		}

		if(successful){
			items.add(ig.getNewItem());
			System.out.println("Item created");
		}
	}

	/*
	 * Edit section
	 */
	private void edit(String[] command){
		//TODO /edit <item/char> <-i index/-n name <-b/-eb/h -i index>> <-s stat> <-r result>
		System.out.println("Not yet Implemented.");
	}

	/*
	 * Print section
	 */
	private void print(String[] command) throws NumberFormatException, IndexOutOfBoundsException{

		switch(command[1]){
		case "char":
		case "character": printChar(command); break;
		case "item": printItem(command); break;
		default:
		}

	}

	private void printChars(){
		int size = characters.size();
		System.out.print("[");
		for(int i = 0; i < size; i++){
			System.out.print(i + "=" + characters.get(i).getName());
			if(i < size-1){
				System.out.print(", ");
			}
		}
		System.out.print("]\n");
	}

	private void printChar(String[] command) throws NumberFormatException, IndexOutOfBoundsException{
		if(command.length == 2){
			System.out.println("/print <char/character> needs options");
			return;
		}
		for(int i = 2; i < command.length; i++){
			switch(command[i]){
			case "-a": printChars(); break;
			case "-i": System.out.println(characters.get(Integer.parseInt(command[++i])).toString()); break;
			case "-n": 
				Character temp = getChar(command[++i]);
				if(temp != null) System.out.println(temp.toString());
				else { System.out.println("No such character"); }
				break;
			default:
				System.out.println("Invalid option: '" + command[i++] + "'"); 
			}
		}
	}

	private Character getChar(String name){
		for(Character c : characters){
			if(c.getName().equals(name)){
				return c;
			}
		}
		System.out.println("Error: Character '" + name + "' not found.");
		return null;
	}

	private void printItems(){
		int size = items.size();
		System.out.print("[");
		for(int i = 0; i < size; i++){
			System.out.print(i + "=" + items.get(i).getName());
			if(i < size-1){
				System.out.print(", ");
			}
		}
		System.out.print("]\n");
	}

	private void printItem(String[] command) throws NumberFormatException, IndexOutOfBoundsException{
		Character currChar = null;
		int index = 0;
		if(command.length == 2){
			System.out.println("/print item needs options");
			return;
		}
		for(int i = 2; i < command.length; i++){
			switch(command[i]){
			case "-a": printItems(); break;
			case "-b": 
				currChar = getChar(command[++i]);
				if(currChar != null){
					if(command[++i].equals("-i")){
						System.out.println(currChar.getBag().getItem(Integer.parseInt(command[++i])).toString());
					}
				}
				break;
			case "-i": System.out.println(items.get(Integer.parseInt(command[++i])).toString()); break;
			/*case "-n": 
				Character temp = getChar(command[++i]);
				if(temp != null) System.out.println(temp.toString());
				else { System.out.println("No such item"); }
				break;*/
			default:
				System.out.println("Invalid option: '" + command[i++] + "'"); 
			}
		}
	}

	/*
	 * Give section
	 */
	private void give(String[] command) throws NumberFormatException, IndexOutOfBoundsException{
		Character tempChar = null;
		Item tempItem = null;
		if(command.length != 5){
			System.out.println("/give is missing options");
			return;
		}
		switch(command[1]){
		case "-i": tempChar = characters.get(Integer.parseInt(command[2])); break;
		case "-n": tempChar = getChar(command[2]); break; 
		default:
			System.out.println("Invalid option: '" + command[1] + "'"); 
		}
		switch(command[3]){
		case "-i": tempItem = items.get(Integer.parseInt(command[4])); break;
		//case "-n": implement for item name later?
		default:
			System.out.println("Invalid option: '" + command[1] + "'");
		}

		if(tempChar != null && tempItem != null){
			/*try{
				tempChar.placeInBag((WearableItem) tempItem);

			} catch(ClassCastException e){
				tempChar.placeInBag((WieldableItem) tempItem);
			}*/
			if(tempChar.placeInBag(tempItem)){
				items.remove(tempItem);
				System.out.println("'" + tempChar.getName() + "' successfully given item '" + tempItem.getName() + "'");
			}
			else{
				System.out.println("Item move unsuccessful, check destination bag space");
			}
		}

	}

	/*
	 * Equip section
	 */
	private void equip(String[] command) throws NumberFormatException{
		Character equipChar = null;
		Character bagChar = null;
		Item tempItem = null;
		if(!(command.length == 5 || command.length == 7)){
			System.out.println("/equip is missing options");
			return;
		}

		switch(command[1]){
		case "-i": equipChar = characters.get(Integer.parseInt(command[2])); break;
		case "-n": equipChar = getChar(command[2]); break;
		default:
			System.out.println("Invalid option: '" + command[1] + "'"); 
		}

		if(equipChar == null){
			System.out.println("Error: Target character doesn't exist");
			return;
		}

		switch(command[3]){
		case "-b":
			bagChar = getChar(command[4]);
			if(bagChar != null){
				if(command[5].equals("-i")){
					int itemIndex = Integer.parseInt(command[6]);
					tempItem = bagChar.getBag().getItem(itemIndex);
					if(equipChar.equipItem(tempItem)){
						bagChar.getBag().removeItem(itemIndex);
						System.out.println("'" + equipChar.getName() + "' successfully equipped item '" + tempItem.getName() + "'");
					}
					else{
						System.out.println("Item equip unsuccessful, check destination equipment block");
					}
				}
			}
			break;
		case "-i":
			tempItem = items.get(Integer.parseInt(command[4]));
			if(equipChar.equipItem(tempItem)){
				items.remove(tempItem);
				System.out.println("'" + equipChar.getName() + "' successfully equipped item '" + tempItem.getName() + "'");
			}
			else{
				System.out.println("Item equip unsuccessful, check destination equipment block");
			}
			break;
		default:
			System.out.println("Invalid option: '" + command[3] + "'");
		}

	}

	/*
	 * Save section
	 */
	private void save(String[] command) throws NumberFormatException, IndexOutOfBoundsException{
		Character tempChar = null;
		if(command.length != 3){
			System.out.println("Error: /save requires parameters");
			return;
		}

		switch(command[1]){
		case "-i": tempChar = characters.get(Integer.parseInt(command[2])); break;
		case "-n": tempChar = getChar(command[2]); break;
		default:
			System.out.println("Invalid option: '" + command[1] + "'");
		}


		if(tempChar != null){
			CharXMLWriter writer = new CharXMLWriter(tempChar);
			if(writer.outputCharacter()){
				System.out.println("'" + tempChar.getName() + "' saved");
			}
			else{
				System.out.println("Error '" + tempChar.getName() + "' not saved");
			}
		}
	}

	/*
	 * Load section
	 */
	private void load(String[] command){
		if(command.length != 2){
			System.out.println("Error: /load requires a caracter name");
			return;
		}

		File file = new File("characters" + File.separator + command[1] + ".xml");
		CharXMLReader parser = new CharXMLReader();
		
		Character temp = parser.parse(file.getAbsolutePath());
		characters.add(temp);
		
		System.out.println("Successfully loaded '" + temp.getName() + "'");
	}

	/*
	 * Help section
	 */
	private void help(String[] command){
		String com = "";
		boolean specific = false;
		boolean done = false;
		boolean found = false;
		if(command.length == 2){
			com = command[1];
			specific = true;
		}
		try(BufferedReader br = new BufferedReader(new FileReader("Help"))){
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			if(specific){
				while(line != null && !found){
					if(line.equals("/"+com)){
						found = true;
						sb.append("---\n\n/"+com);
						line = br.readLine();
					}
					else{
						line = br.readLine();
					}
				}
				while(line != null && !done){
					if(!line.equals("---")){
						sb.append(line);
						sb.append("\n");
						line = br.readLine();
					}
					else{
						sb.append("---");
						done = true;
					}
				}
			}
			else{
				while(line != null){
					sb.append(line);
					sb.append("\n");
					line = br.readLine();
				}
			}

			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
