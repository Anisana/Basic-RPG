package core;

import item.Bag;
import item.Item;
import item.WearableItem;
import item.WieldableItem;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import unit.character.Character;
import blocks.CharacterStatBlock;
import blocks.EquipmentBlock;
import blocks.StatBlock;
import enums.ArmourType;
import enums.DamageType;
import enums.ItemType;
import enums.ResistType;

public class CharXMLReader extends DefaultHandler{

	private SAXParser saxParser;
	private XMLReader xmlReader;

	private Character character;
	private CharacterStatBlock currStatBlock;
	private EquipmentBlock equipment;
	private Bag bag;
	private WearableItem currWearable;
	private WieldableItem currWieldable;

	private String mainPhase;
	private String equipPhase;
	private String bagPhase;
	
	private boolean done = false;

	public CharXMLReader() {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			saxParser = spf.newSAXParser();
			xmlReader = saxParser.getXMLReader();

			xmlReader.setContentHandler(this);
			xmlReader.setErrorHandler(new CharXMLReaderErrorHandler());

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public Character parse(String filepath){
		try {
			xmlReader.parse(filepath);
			if(done) return this.character;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void startDocument() throws SAXException{

	}

	@Override
	public void endDocument() throws SAXException{

	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException{
		/*System.out.print("qName = " + qName);
		if(atts.getLength() > 0){
			System.out.println("\tatts.value(0) = " + atts.getValue(0));
		}*/

		switch(qName){
		case "character":
			this.character = new Character();
			this.mainPhase = "character";
			return;
		case "baseStats":
			this.currStatBlock = new CharacterStatBlock();
			this.mainPhase = "baseStats";
			return;
		case "modifiedStats":
			this.currStatBlock = new CharacterStatBlock();
			this.mainPhase = "modifiedStats";
			return;
		case "equipment":
			this.equipment = new EquipmentBlock();
			this.mainPhase = "equipment";
			return;
		case "bag":
			this.bag = new Bag();
			this.mainPhase = "bag";
			return;
		case "wearableItem":
			if(mainPhase.equals("equipment")) this.equipPhase = "wearableItem";
			else if(mainPhase.equals("bag")) this.bagPhase = "wearableItem";
			this.currWearable = new WearableItem();
			return;
		case "wieldableItem":
			if(mainPhase.equals("equipment")) this.equipPhase = "wieldableItem";
			else if(mainPhase.equals("bag")) this.bagPhase = "wieldableItem";
			this.currWieldable = new WieldableItem();
			return;
		//The following list exists to ensure backwards compatibility when
		//old tags are no longer used.
		case "name":
		case "strength":
		case "stamina":
		case "constitution":
		case "intelligence":
		case "spirit":
		case "level":
		case "totalHealth":
		case "currentHealth":
		case "healthRegenRate":
		case "totalMana":
		case "currentMana":
		case "manaRegenRate":
		case "totalFocus":
		case "currentFocus":
		case "focusRegenRate":
		case "carryCapacity":
		case "experience":
		case "levelBoundary":
		case "size":
		case "slotNum":
		case "type":
		case "weight":
		case "armourMaterial":
		case "armourValue":
		case "resistType":
		case "resistValue":
		case "handsRequired":
		case "damageType":
		case "minDamage":
		case "maxDamage":
			break;
		default:
			System.out.println("Old tag found: " + qName);
			return;
		}

		//do something depending upon phase
		switch(mainPhase){
		case "character": addCharValue(qName, atts.getValue(0)); break;
		case "baseStats": addCharStat(qName, atts.getValue(0)); break;
		case "modifiedStats": addCharStat(qName, atts.getValue(0)); break;
		case "equipment": addEquipStat(qName, atts.getValue(0)); break;
		case "bag": addBagStat(qName, atts.getValue(0)); break;
		default:

		}



	}

	private void addCharValue(String stat, String value){
		switch(stat){
		case "name":
			this.character.setName(value);
			break;
		default:

		}
	}

	private void addCharStat(String stat, String value){
		switch(stat){
		case "strength": this.currStatBlock.setStrength(Double.parseDouble(value)); break;
		case "stamina": this.currStatBlock.setStamina(Double.parseDouble(value)); break;
		case "constitution": this.currStatBlock.setConstitution(Double.parseDouble(value)); break;
		case "intelligence": this.currStatBlock.setIntelligence(Double.parseDouble(value)); break;
		case "spirit": this.currStatBlock.setSpirit(Double.parseDouble(value)); break;
		case "level": this.currStatBlock.setLevel(Integer.parseInt(value));
		case "totalHealth": this.currStatBlock.setTotalHealth(Double.parseDouble(value)); break;
		case "currentHealth": this.currStatBlock.setCurrentHealth(Double.parseDouble(value)); break;
		case "healthRegenRate": this.currStatBlock.setHealthRegenRate(Double.parseDouble(value)); break;
		case "totalMana": this.currStatBlock.setTotalMana(Double.parseDouble(value)); break;
		case "currentMana": this.currStatBlock.setCurrentMana(Double.parseDouble(value)); break;
		case "manaRegenRate": this.currStatBlock.setManaRegenRate(Double.parseDouble(value)); break;
		case "totalFocus": this.currStatBlock.setTotalFocus(Double.parseDouble(value)); break;
		case "currentFocus": this.currStatBlock.setCurrentFocus(Double.parseDouble(value)); break;
		case "focusRegenRate": this.currStatBlock.setFocusRegenRate(Double.parseDouble(value)); break;
		case "carryCapacity": this.currStatBlock.setCarryCapacity(Double.parseDouble(value)); break;
		case "experience": this.currStatBlock.setExperience(Integer.parseInt(value)); break;
		case "levelBoundary": this.currStatBlock.setLevelBoundary(Integer.parseInt(value)); break;
		default:

		}
	}

	private void addEquipStat(String stat, String value){
		if(equipPhase.equals("wearableItem")){
			addWearableStat(stat, value);
		}
		if(equipPhase.equals("wieldableItem")){
			addWieldableStat(stat, value);
		}
	}
	
	private void addBagStat(String stat, String value){
		switch(stat){
		case "size": this.bag.setSize(Integer.parseInt(value)); return;
		default:
			
		}
		switch(bagPhase){
		case "wearableItem": addWearableStat(stat, value); break;
		case "wieldableItem": addWieldableStat(stat, value); break;
		default:
			
		}
	}

	private void addWearableStat(String stat, String value){
		switch(stat){
		case "name": this.currWearable.setName(value); break;
		case "slotNum": this.currWearable.setSlotNum(Integer.parseInt(value)); break;
		case "strength": this.currWearable.getStats().setStrength(Double.parseDouble(value)); break;
		case "stamina": this.currWearable.getStats().setStamina(Double.parseDouble(value)); break;
		case "constitution": this.currWearable.getStats().setConstitution(Double.parseDouble(value)); break;
		case "intelligence": this.currWearable.getStats().setIntelligence(Double.parseDouble(value)); break;
		case "spirit": this.currWearable.getStats().setSpirit(Double.parseDouble(value)); break;
		case "level": this.currWearable.getStats().setLevel(Integer.parseInt(value));
		case "weight": this.currWearable.getStats().setWeight(Double.parseDouble(value)); break;
		case "type": this.currWearable.getStats().setType(Item.getWearableType(currWearable.getSlotNum())); break;
		case "armourMaterial": this.currWearable.getStats().setArmourMaterial(convertToArmourType(value)); break;
		case "armourValue": this.currWearable.getStats().setArmourValue(Double.parseDouble(value)); break;
		case "resistType": this.currWearable.getStats().setResistType(convertToResistType(value)); break;
		case "resistValue": this.currWearable.getStats().setResistValue(Double.parseDouble(value)); break;
		default:

		}

	}

	private void addWieldableStat(String stat, String value){
		switch(stat){
		case "name": this.currWieldable.setName(value); break;
		case "slotNum": this.currWieldable.setSlotNum(Integer.parseInt(value)); break;
		case "strength": this.currWieldable.getStats().setStrength(Double.parseDouble(value)); break;
		case "stamina": this.currWieldable.getStats().setStamina(Double.parseDouble(value)); break;
		case "constitution": this.currWieldable.getStats().setConstitution(Double.parseDouble(value)); break;
		case "intelligence": this.currWieldable.getStats().setIntelligence(Double.parseDouble(value)); break;
		case "spirit": this.currWieldable.getStats().setSpirit(Double.parseDouble(value)); break;
		case "level": this.currWieldable.getStats().setLevel(Integer.parseInt(value));
		case "weight": this.currWieldable.getStats().setWeight(Double.parseDouble(value)); break;
		case "type": this.currWieldable.getStats().setType(convertToWieldableType(value)); break;
		case "handsRequired": this.currWieldable.getStats().setHandsRequired(Integer.parseInt(value)); break;
		case "damageType": this.currWieldable.getStats().setDamageType(convertToDamageType(value)); break;
		case "minDamage": this.currWieldable.getStats().setMinDamage(Double.parseDouble(value)); break;
		case "maxDamage": this.currWieldable.getStats().setMaxDamage(Double.parseDouble(value)); break;
		default:

		}
	}

	private ArmourType convertToArmourType(String type){
		switch(type){
		case "METAL": return ArmourType.METAL;
		case "LEATHER": return ArmourType.LEATHER;
		case "CLOTH": return ArmourType.CLOTH;
		}
		return null;
	}

	private ResistType convertToResistType(String type){
		switch(type){
		case "ALL": return ResistType.ALL;
		case "PHYSICAL": return ResistType.PHYSICAL;
		case "MAGIC": return ResistType.MAGIC;
		case "PIERCING": return ResistType.PIERCING;
		case "BLUDGEONING": return ResistType.BLUDGEONING;
		case "SLASHING": return ResistType.SLASHING;
		case "FIRE": return ResistType.FIRE;
		case "COLD": return ResistType.COLD;
		case "ARCANE": return ResistType.ARCANE;
		case "NONE": return ResistType.NONE;
		}
		return null;
	}

	private DamageType convertToDamageType(String type){
		switch(type){
		case "PIERCING": return DamageType.PIERCING;
		case "BLUDGEONING": return DamageType.BLUDGEONING;
		case "SLASHING": return DamageType.SLASHING;
		case "FIRE": return DamageType.FIRE;
		case "COLD": return DamageType.COLD;
		case "ARCANE": return DamageType.ARCANE;
		}
		return null;
	}
	
	private ItemType convertToWieldableType(String type){
		switch(type){
		case "BOW": return ItemType.BOW;
		case "SWORD": return ItemType.SWORD;
		case "MACE": return ItemType.MACE;
		case "STAFF": return ItemType.STAFF;
		case "WAND": return ItemType.WAND;
		case "DAGGER": return ItemType.DAGGER;
		case "CROSSBOW": return ItemType.CROSSBOW;
		}
		return null;
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException{
		switch(qName){
		case "character":
			done = true;
			return;
		case "baseStats":
			this.character.setBaseStats(currStatBlock);
			return;
		case "modifiedStats":
			this.character.setModifiedStats(currStatBlock);
			return;
		case "equipment":
			this.character.setEquipment(equipment);
			return;
		case "bag":
			this.character.setBag(bag);
			return;
		case "body":

			return;
		case "hands":

			return;
		case "wearableItem":
			switch(mainPhase){
			case "equipment": this.equipment.equipItem(currWearable); break;
			case "bag": this.bag.placeItem(currWearable); break;
			default:
				
			}
			return;
		case "wieldableItem":
			switch(mainPhase){
			case "equipment": this.equipment.equipItem(currWieldable); break;
			case "bag": this.bag.placeItem(currWieldable); break;
			default:
				
			}
			return;
		default:

		}
	}

	@Override
	public void characters(char chars[], int start, int length){

	}

	private class CharXMLReaderErrorHandler implements ErrorHandler{

		@Override
		public void error(SAXParseException arg0) throws SAXException {
			System.out.println("\n\t!! SAXParseException Error: " + arg0);
		}

		@Override
		public void fatalError(SAXParseException arg0) throws SAXException {
			System.out.println("\n\t!! SAXParseException Fatal Error: " + arg0);
		}

		@Override
		public void warning(SAXParseException arg0) throws SAXException {
			System.out.println("\n\t!! SAXParseException Warning: " + arg0);
		}

	}

}
