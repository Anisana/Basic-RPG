package core;

import java.util.ArrayList;

import path.Path;
import unit.character.Character;
import world.Encounter;

public class DataStore {
	
	private ArrayList<Character> characters;
	private ArrayList<Path> paths;
	private ArrayList<Encounter> encounters;

	public DataStore() {
		this.characters = new ArrayList<Character>();
		this.paths = new ArrayList<Path>();
		this.encounters = new ArrayList<Encounter>();
	}
	
	public void addCharacter(Character character){
		this.characters.add(character);
	}

	public ArrayList<Character> getCharacters() {
		return characters;
	}
	
	public void addPath(Path path){
		this.paths.add(path);
	}
	
	public Path getPath(String pathName){
		for(Path p : paths){
			if(p.getName().equals(pathName)){
				return p;
			}
		}
		return new Path();
	}

	public ArrayList<Path> getPaths() {
		return paths;
	}
	
	public ArrayList<Encounter> getEncounters(){
		return encounters;
	}
	
	public void addEncounter(Encounter encounter){
		this.encounters.add(encounter);
	}
	
	public Encounter getEncounter(int id){
		for(Encounter e : encounters){
			if(e.getId() == id){
				return e;
			}
		}
		return new Encounter();
	}

}
