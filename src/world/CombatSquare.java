package world;

import java.awt.Color;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import path.SpawnedObject;
import unit.Unit;

public class CombatSquare extends JButton {
	
	int row;
	int col;
	
	private Unit unit;
	private Scenery scenery;
	private SpawnedObject spawnedObject;
	private GroundEffect groundEffect;

	public CombatSquare() {
		this.row = 0;
		this.col = 0;
	}

	public CombatSquare(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public CombatSquare(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public CombatSquare(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public CombatSquare(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}
	
	public CombatSquare(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public boolean containsSomething(){
		if(unit != null){
			return true;
		}
		else if(scenery != null){
			return true;
		}
		else if(spawnedObject != null){
			return true;
		}
		else if(groundEffect != null){
			return true;
		}
		return false;
	}
	
	public boolean containsUnit(){
		if(unit != null){
			return true;
		}
		return false;
	}
	
	public Unit getUnit(){
		return this.unit;
	}
	
	public boolean add(Unit u){
		if(this.unit != null){
			return false;
		}
		this.unit = u;
		//setBackground(Color.blue); //in reality, set to the Unit's icon
		//setToolTipText(u.getName());
		setIcon(new ImageIcon("icons/" + u.getName() + ".png"));
		return true;
	}
	
	public boolean remove(Unit u){
		if(this.unit != null){
			this.unit = null;
			setIcon(null);
			return true;
		}
		return false;
	}
	

}
