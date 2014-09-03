package path;

import java.util.ArrayList;
import java.util.Arrays;

import unit.character.Character;
import enums.SkillSubType;
import enums.SkillType;

public class Skill {
	
	private String name;
	private String description;
	
	private SkillType type;
	private SkillSubType[] subTypes;
	
	//"focus", "mana" or "none"
	private String resource;
	private double resourceCost;
	
	private int levelRequirement;
	
	private int cooldown;
	private int cooldownRemaining;
	
	//shape of the skill: "circle", "cone" or "rectangle"
	private String shape;
	//for a single point, use "circle" and size 0
	private int shapeSize;
	
	private int range;
	
	private ArrayList<SkillEffect> effects;
	
	private SpawnedObject spawnedObject;
	
	public Skill() {
		this.name = "Basic Attack";
		this.description = "A basic melee attack";
		this.type = SkillType.ATTACK;
		this.subTypes = new SkillSubType[1];
		this.subTypes[0] = SkillSubType.DAMAGE;
		this.resource = "none";
		this.resourceCost = 0.0;
		this.levelRequirement = 0;
		this.cooldown = 0;
		this.cooldownRemaining = 0;
		this.shape = "circle";
		this.shapeSize = 1;
		this.range = 1;
		this.effects = null;
		this.spawnedObject = null;
	}

	public Skill(String name, String description, SkillType type,
			SkillSubType[] subTypes, String resource, double resourceCost,
			int levelRequirement, int cooldown, String shape, int shapeSize,
			int range, ArrayList<SkillEffect> effects, SpawnedObject spawnedObject) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
		this.subTypes = subTypes;
		this.resource = resource;
		this.resourceCost = resourceCost;
		this.levelRequirement = levelRequirement;
		this.cooldown = cooldown;
		this.cooldownRemaining = 0;
		this.shape = shape;
		this.shapeSize = shapeSize;
		this.range = range;
		this.effects = effects;
		this.spawnedObject = spawnedObject;
	}
	
	public boolean isOffCooldown(){
		System.out.println(this.name + "'s cooldownRemaining = " + this.cooldownRemaining);
		if(this.cooldownRemaining == 0) return true;
		return false;
	}
	
	public void placeOnCooldown(){
		this.cooldownRemaining = this.cooldown;
	}
	
	public void cooldown(){
		if(this.cooldownRemaining == 0) return;
		this.cooldownRemaining--;
	}
	
	public double rollDamage(Character character){
		double damage = Math.random() * character.getEquipment().getWeapon().getStats().getDamageRange();
		damage += character.getEquipment().getWeapon().getStats().getMinDamage();
		damage *= (1 + character.getModifiedStats().getStrength()/100);
		
		//take into account:
		//damage effectiveness (aka, actual skill effect)
		//character buffs and debuffs
		
		return damage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SkillType getType() {
		return type;
	}

	public void setType(SkillType type) {
		this.type = type;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public double getResourceCost() {
		return resourceCost;
	}

	public void setResourceCost(double resourceCost) {
		this.resourceCost = resourceCost;
	}

	public int getLevelRequirement() {
		return levelRequirement;
	}

	public void setLevelRequirement(int levelRequirement) {
		this.levelRequirement = levelRequirement;
	}

	public SkillSubType[] getSubTypes() {
		return subTypes;
	}

	public void setSubTypes(SkillSubType[] subTypes) {
		this.subTypes = subTypes;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public int getShapeSize() {
		return shapeSize;
	}

	public void setShapeSize(int shapeSize) {
		this.shapeSize = shapeSize;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public ArrayList<SkillEffect> getEffects() {
		return effects;
	}

	public void setEffects(ArrayList<SkillEffect> effects) {
		this.effects = effects;
	}

	public SpawnedObject getSpawnedObject() {
		return spawnedObject;
	}

	public void setSpawnedObject(SpawnedObject spawnedObject) {
		this.spawnedObject = spawnedObject;
	}

	@Override
	public String toString() {
		return "Skill [name=" + name + ", description=" + description
				+ ", type=" + type + ", subTypes=" + Arrays.toString(subTypes)
				+ ", resource=" + resource + ", resourceCost=" + resourceCost
				+ ", levelRequirement=" + levelRequirement + ", cooldown="
				+ cooldown + ", shape=" + shape + ", shapeSize=" + shapeSize
				+ ", range=" + range + ", effects=" + effects
				+ ", spawnedObject=" + spawnedObject + "]";
	}

	
}
