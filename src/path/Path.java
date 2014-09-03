package path;

import java.util.ArrayList;

public class Path {
	
	private String name;
	private String description;
	private ArrayList<Skill> skills;
	
	//the intended user of the Path, current used values:
	//all, player
	private String owner;

	//default Path with a single default Skill
	public Path() {
		this.name = "Default Path";
		this.description = "Default Path, only used when something went wrong";
		this.owner = "all";
		this.skills = new ArrayList<Skill>();
		this.skills.add(new Skill());
	}
	
	public Path(String name, int numSkills, String owner){
		this.name = name;
		this.description = "A description";
		this.owner = owner;
		this.skills = new ArrayList<Skill>(numSkills);
	}
	
	public void addSkill(Skill skill){
		this.skills.add(skill);
	}
	
	public Skill getSkill(int i){
		return this.skills.get(i);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	/*@Override
	public String toString() {
		return "Path [name=" + name + ", description=" + description
				+ ", skills=" + skills + ", owner=" + owner + "]";
	}*/
	
	@Override
	public String toString(){
		return this.name;
	}
}
