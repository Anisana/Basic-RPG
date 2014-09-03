package core;

import java.io.File;

import item.Bag;
import item.Item;
import item.WearableItem;
import item.WieldableItem;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import unit.character.Character;
import blocks.ArmourStatBlock;
import blocks.CharacterStatBlock;
import blocks.EquipmentBlock;
import blocks.ItemStatBlock;
import blocks.StatBlock;
import blocks.WeaponStatBlock;

public class CharXMLWriter {
	
	private Character character;
	private String filepath;

	public CharXMLWriter() {}
	
	public CharXMLWriter(Character character){
		this.character = character;
		this.filepath = "characters" + File.separator + character.getName()+".xml";
	}
	
	public boolean outputCharacter(){
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			//create root element "character"
			Element rootElement = doc.createElement("character");
			doc.appendChild(rootElement);
			
			//add name
			Element name = doc.createElement("name");
			name.setAttribute("value", character.getName());
			rootElement.appendChild(name);
			
			//create baseStats and fill
			Element baseStats = doc.createElement("baseStats");
			rootElement.appendChild(baseStats);
			outputCharStatBlock(doc, baseStats, character.getBaseStats());
			
			//create modifiedStats and fill
			Element modifiedStats = doc.createElement("modifiedStats");
			rootElement.appendChild(modifiedStats);
			outputCharStatBlock(doc, modifiedStats, character.getModifiedStats());
			
			//create equipment and fill
			Element equipment = doc.createElement("equipment");
			rootElement.appendChild(equipment);
			outputEquipment(doc, equipment, character.getEquipment());
			
			//create bag and fill
			Element bag = doc.createElement("bag");
			rootElement.appendChild(bag);
			outputBag(doc, bag, character.getBag());
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, result);
			
			return true;
			
		} catch(ParserConfigurationException e){
			e.printStackTrace();
			return false;
		} catch(TransformerException e){
			e.printStackTrace();
			return false;
		}
	}
	
	private void outputCharStatBlock(Document doc, Element parent, CharacterStatBlock statBlock){
		outputCoreStatBlock(doc, parent, statBlock);
		
		Element temp;
		
		temp = doc.createElement("totalHealth");
		temp.setAttribute("value", statBlock.getTotalHealth()+"");
		parent.appendChild(temp);
		
		temp = doc.createElement("currentHealth");
		temp.setAttribute("value", statBlock.getCurrentHealth()+"");
		parent.appendChild(temp);
		
		temp = doc.createElement("healthRegenRate");
		temp.setAttribute("value", statBlock.getHealthRegenRate()+"");
		parent.appendChild(temp);
		
		temp = doc.createElement("totalFocus");
		temp.setAttribute("value", statBlock.getTotalFocus()+"");
		parent.appendChild(temp);
		
		temp = doc.createElement("currentFocus");
		temp.setAttribute("value", statBlock.getCurrentFocus()+"");
		parent.appendChild(temp);
		
		temp = doc.createElement("focusRegenRate");
		temp.setAttribute("value", statBlock.getFocusRegenRate()+"");
		parent.appendChild(temp);
		
		temp = doc.createElement("totalMana");
		temp.setAttribute("value", statBlock.getTotalMana()+"");
		parent.appendChild(temp);
		
		temp = doc.createElement("currentMana");
		temp.setAttribute("value", statBlock.getCurrentMana()+"");
		parent.appendChild(temp);
		
		temp = doc.createElement("manaRegenRate");
		temp.setAttribute("value", statBlock.getManaRegenRate()+"");
		parent.appendChild(temp);
		
		temp = doc.createElement("carryCapacity");
		temp.setAttribute("value", statBlock.getCarryCapacity()+"");
		parent.appendChild(temp);
		
		temp = doc.createElement("experience");
		temp.setAttribute("value", statBlock.getExperience()+"");
		parent.appendChild(temp);
		
		temp = doc.createElement("levelBoundary");
		temp.setAttribute("value", statBlock.getLevelBoundary()+"");
		parent.appendChild(temp);
	}
	
	private void outputEquipment(Document doc, Element parent, EquipmentBlock equipmentBlock){
		outputEquipmentArray(doc, parent, equipmentBlock.getEquipment());
	}
	
	private void outputEquipmentArray(Document doc, Element parent, Item[] array){
		Element type, temp;
		Item tempItem;
		String itemType;
		for(int i = 0; i < array.length; i++){
			if(array[i] != null){
				try{
					tempItem = (WearableItem) array[i];
					itemType = "wearableItem";
				} catch(ClassCastException e){
					tempItem = (WieldableItem) array[i];
					itemType = "wieldableItem";
				}
				type = doc.createElement(itemType);
				parent.appendChild(type);
				
				temp = doc.createElement("name");
				temp.setAttribute("value", tempItem.getName());
				type.appendChild(temp);
				
				temp = doc.createElement("slotNum");
				temp.setAttribute("value", tempItem.getSlotNum()+"");
				type.appendChild(temp);
				
				switch(itemType){
				case "wearableItem": outputArmourStatBlock(doc, type, ((WearableItem) tempItem).getStats()); break;
				case "wieldableItem": outputWeaponStatBlock(doc, type, ((WieldableItem) tempItem).getStats()); break;
				}
			}
		}
	}
	
	private void outputArmourStatBlock(Document doc, Element parent, ArmourStatBlock block){
		outputItemStatBlock(doc, parent, block);
		
		Element armourMaterial = doc.createElement("armourMaterial");
		armourMaterial.setAttribute("value", block.getArmourMaterial()+"");
		parent.appendChild(armourMaterial);
		
		Element armourValue = doc.createElement("armourValue");
		armourValue.setAttribute("value", block.getArmourValue()+"");
		parent.appendChild(armourValue);
		
		Element resistType = doc.createElement("resistType");
		resistType.setAttribute("value", block.getResistType()+"");
		parent.appendChild(resistType);
		
		Element resistValue = doc.createElement("resistValue");
		resistValue.setAttribute("value", block.getResistValue()+"");
		parent.appendChild(resistValue);
	}
	
	private void outputWeaponStatBlock(Document doc, Element parent, WeaponStatBlock block){
		outputItemStatBlock(doc, parent, block);
		
		Element handsRequired = doc.createElement("handsRequired");
		handsRequired.setAttribute("value", block.getHandsRequired()+"");
		parent.appendChild(handsRequired);
		
		Element damageType = doc.createElement("damageType");
		damageType.setAttribute("value", block.getDamageType()+"");
		parent.appendChild(damageType);
		
		Element minDamage = doc.createElement("minDamage");
		minDamage.setAttribute("value", block.getMinDamage()+"");
		parent.appendChild(minDamage);
		
		Element maxDamage = doc.createElement("maxDamage");
		maxDamage.setAttribute("value", block.getMaxDamage()+"");
		parent.appendChild(maxDamage);
	}
	
	private void outputItemStatBlock(Document doc, Element parent, ItemStatBlock block){
		outputCoreStatBlock(doc, parent, block);
		
		Element weight = doc.createElement("weight");
		weight.setAttribute("value", block.getWeight()+"");
		parent.appendChild(weight);
		
		Element type = doc.createElement("type");
		type.setAttribute("value", block.getType()+"");
		parent.appendChild(type);
	}
	
	private void outputBag(Document doc, Element parent, Bag bag){
		int bagSize = bag.getSize();
		Item item;
		String itemType = "";
		Element type, temp;
		
		Element size = doc.createElement("size");
		size.setAttribute("value", bagSize+"");
		parent.appendChild(size);
		
		for(int i = 0; i < bagSize; i++){
			if(bag.getItem(i) != null){
				try{
					item = (WearableItem) bag.getItem(i);
					itemType = "wearableItem";
				} catch(ClassCastException e){
					item = (WieldableItem) bag.getItem(i);
					itemType = "wieldableItem";
				}
				type = doc.createElement(itemType);
				parent.appendChild(type);
				
				temp = doc.createElement("name");
				temp.setAttribute("value", item.getName());
				type.appendChild(temp);
				
				temp = doc.createElement("slotNum");
				temp.setAttribute("value", item.getSlotNum()+"");
				type.appendChild(temp);
				
				switch(itemType){
				case "wearableItem": outputArmourStatBlock(doc, type, ((WearableItem) item).getStats()); break;
				case "wieldableItem": outputWeaponStatBlock(doc, type, ((WieldableItem) item).getStats()); break;
				}
			}
		}
	}
	
	private void outputCoreStatBlock(Document doc, Element parent, StatBlock block){
		Element strength = doc.createElement("strength");
		strength.setAttribute("value", block.getStrength()+"");
		parent.appendChild(strength);
		
		Element stamina = doc.createElement("stamina");
		stamina.setAttribute("value", block.getStamina()+"");
		parent.appendChild(stamina);
		
		Element constitution = doc.createElement("constitution");
		constitution.setAttribute("value", block.getConstitution()+"");
		parent.appendChild(constitution);
		
		Element intelligence = doc.createElement("intelligence");
		intelligence.setAttribute("value", block.getIntelligence()+"");
		parent.appendChild(intelligence);
		
		Element spirit = doc.createElement("spirit");
		spirit.setAttribute("value", block.getSpirit()+"");
		parent.appendChild(spirit);
		
		Element level = doc.createElement("level");
		level.setAttribute("value", block.getLevel()+"");
		parent.appendChild(level);
	}

}
