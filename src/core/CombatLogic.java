package core;

import java.awt.Color;
import java.util.ArrayList;

import enums.SkillSubType;
import enums.SkillType;
import path.Skill;
import unit.NPC;
import unit.Unit;
import unit.character.Character;
import unit.monster.Monster;
import world.CombatSquare;
import world.Encounter;

public class CombatLogic{

	//turn owner variables
	private ArrayList<Unit> party;
	private ArrayList<Unit> mobs;
	private ArrayList<Unit> units;
	private int turnOwner;
	private int turnNumber;
	
	//general turn variables
	private String phase; //either "move" or "attack"
	private boolean validMove;
	private boolean validAttack;
	private boolean hasMoved;
	
	//character turn variables
	private int currAction; //
	
	//end of combat String
	private String combatEndString;
	
	//combat experience
	private int combatExpValue;
	
	//grid
	private CombatSquare[][] grid;
	private ArrayList<CombatSquare> affectedSquares;
	
	CombatLogic(){
		
	}
	
	public CombatLogic(Character character, Encounter encounter){
		this.grid = encounter.getGrid();
		this.affectedSquares = new ArrayList<CombatSquare>();
		
		this.turnOwner = 0;
		this.turnNumber = 1;
		this.currAction = -1;
		
		this.phase = "move";
		this.validMove = false;
		this.validAttack = false;
		this.hasMoved = false;
		
		//roll initiatives?
		units = new ArrayList<Unit>();
		party = new ArrayList<Unit>();
		mobs = encounter.getMobs();
		
		character.updateVSPanel();
		
		//TODO should really loop through
		mobs.get(0).updateVSPanel();
		
		
		units.add(character);
		party.add(character);
		
		units.add(mobs.get(0));
		//mobs.add(mobs.get(0));
		
		calcCombatExp();
		
		System.out.println("\n\n--- Entering combat ---\nParticipants:\n");
		System.out.println(units);
		System.out.println("Combat Experience Value = " + combatExpValue);
		
	}
	
	private void calcCombatExp(){
		combatExpValue = 0;
		int tempExp = 0, difference;
		double multiplier = 100;
		
		for(Unit u : mobs){
			tempExp = ((Monster) u).getBaseExpValue();
			difference = ((Monster) u).getBaseStats().getLevel() - ((Character) party.get(0)).getBaseStats().getLevel();
			multiplier += (double) difference;
			multiplier /= 100;
			combatExpValue += tempExp*multiplier;
		}
	}
	
	public int getCombatExp(){
		return this.combatExpValue;
	}
	
	public int getTurnNumber(){
		return this.turnNumber;
	}
	
	public void passTurn() throws CombatFinishedEvent{
		
		if(combatFinished()){
			throw new CombatFinishedEvent(combatEndString);
		}
		
		phase = "move";
		validMove = false;
		validAttack = false;
		currAction = -1;
		hasMoved = false;
		
		this.turnOwner++;
		if(turnOwner == units.size()){
			this.turnOwner = 0;
			this.turnNumber++;
			endOfTurnCleanup();
			
		}
		
		if(units.get(turnOwner).getClass().getInterfaces().length > 0){
			if(units.get(turnOwner).getClass().getInterfaces()[0] == NPC.class){
				takeTurn();
			}
		}
		
		//System.out.println(party.get(0));
	}
	
	private void endOfTurnCleanup(){
		for(Unit u : units){
			u.regenResources();
			u.cooldownSkills();
		}
	}
	
	public boolean combatFinished(){
		//if turn limit reached? (future mission possibilities)
		//other condition met?
		boolean partyDead = true, mobsDead = true;
		
		for(Unit u : party){
			if(((Character) u).getModifiedStats().getCurrentHealth() > 0.0){
				partyDead = false;
			}
		}
		
		for(Unit u : mobs){
			if(((Monster) u).getModifiedStats().getCurrentHealth() > 0.0){
				mobsDead = false;
			}
		}
		
		if(mobsDead){
			combatEndString = "Victory!"; 
		}
		if(partyDead){
			combatEndString = "Defeat";
		}
		
		return (partyDead | mobsDead);
	}
	
	private void takeTurn() throws CombatFinishedEvent{
		//edit this
		double damage = ((Monster) units.get(turnOwner)).getDamage(0);
		
		System.out.print(units.get(turnOwner).getName() + " hit for " + damage + " damage ");
		
		units.get(0).takeDamage(damage);
		units.get(0).updateVSPanel();
		
		System.out.println(((Character) units.get(0)).getName() + "'s hp = "+ ((Character) units.get(0)).getModifiedStats().getCurrentHealth());
		
		this.currAction = -1;
		passTurn();
	}
	
	public boolean setCurrentAction(int action){
		if(((Character) units.get(turnOwner)).getActiveSkills().get(action).isOffCooldown()){
			if(((Character) units.get(turnOwner)).hasSufficientResources(action)){
				this.currAction = action;
				this.phase = "attack";
				System.out.println("currAction = " + currAction);
				return true;
			}
			System.out.println("unit " + units.get(turnOwner).getName() + " has insufficient resources");
			return false;
		}
		System.out.println("skill " + action + " is on cooldown");
		return false;
	}
	
	public void deselectAction(){
		if(!hasMoved){
			phase = "move";
		}
		currAction = -1;
	}

	
//character turn related methods
	public void highlightMovementPath(int row, int col){
		if(phase.equals("attack")){
			return;
		}
		
		CombatSquare origin = grid[((Character) units.get(turnOwner)).getCombatRow()][((Character) units.get(turnOwner)).getCombatCol()];
		
		if(!origin.containsSomething()){
			System.out.println("in highlightMovementPath, origin doesn't contain a unit :S");
		}
		
		//System.out.print("origin row = " + origin.getRow() + ", origin col = " + origin.getCol() + " ");
		
		
		//Very simple pathing algorithm
		int rowDir = 0, colDir = 0, currRow = origin.getRow(), currCol = origin.getCol();
		
		if(row > origin.getRow()) rowDir = 1;
		else rowDir = -1;
		
		if(col > origin.getCol()) colDir = 1;
		else colDir = -1;
		
		
		while(currRow != row){
			currRow += rowDir;
			affectedSquares.add(grid[currRow][currCol]);
			
		}
		while(currCol != col){
			currCol += colDir;
			affectedSquares.add(grid[currRow][currCol]);
			
		}
		//end very simple pathing algorithm
		
		/*System.out.println("affectedSquares.size() = " + affectedSquares.size());
		
		for(CombatSquare cs : affectedSquares){
			System.out.print("["+cs.getRow()+", "+cs.getCol()+"], ");
		}
		System.out.println("");*/
		
		if(affectedSquares.size() <= ((Character) units.get(turnOwner)).getModifiedStats().getMovementSpeed()){
			for(CombatSquare cs : affectedSquares){
				cs.setBackground(Color.GREEN);
			}
			validMove = true;
		}
		else{
			grid[row][col].setBackground(Color.RED);
			validMove = false;
		}
	}
	
	public void highlightAttackArea(int row, int col){
		String shape;
		int shapeSize, range;
		CombatSquare origin;
		double meleeRangeModifier = 0;
		
		if(phase.equals("move") || currAction == -1){
			return;
		}
		
		origin = grid[((Character) units.get(turnOwner)).getCombatRow()][((Character) units.get(turnOwner)).getCombatCol()];

		shape = ((Character) units.get(turnOwner)).getActiveSkills().get(currAction).getShape();
		shapeSize = ((Character) units.get(turnOwner)).getActiveSkills().get(currAction).getShapeSize();
		range = ((Character) units.get(turnOwner)).getActiveSkills().get(currAction).getRange();
		
		
		switch(shape){
		case "circle": 
			if(shapeSize == 0){
				meleeRangeModifier = 0.5;
			}
			if(!isInRange(origin, row, col, range, meleeRangeModifier)){
				grid[row][col].setBackground(Color.RED);
				affectedSquares.add(grid[row][col]);
				validAttack = false;
				return;
			}
			highlightCircle(row, col, shapeSize);
			break;
		case "cone":
			if(!isValidConeLineStart(origin, row, col)){
				grid[row][col].setBackground(Color.RED);
				affectedSquares.add(grid[row][col]);
				validAttack = false;
				return;
			}
			highlightCone(origin, row, col, shapeSize);
			break;
		case "line":
			//if the target square isn't in one of the adjacent ordinals
			if(!isValidConeLineStart(origin, row, col)){
				grid[row][col].setBackground(Color.RED);
				affectedSquares.add(grid[row][col]);
				validAttack = false;
				return;
			}
			highlightLine(origin, row, col, shapeSize);
			break;
		}
		
		for(CombatSquare cs : affectedSquares){
			cs.setBackground(Color.GREEN);
		}
		validAttack = true;
		
	}
	
	private void highlightLine(CombatSquare origin, int row, int col, int shapeSize){
		int rowDir = 0, colDir = 0, currRow = row, currCol = col;
		
		if(row > origin.getRow()) rowDir = 1;
		else if(row < origin.getRow()) rowDir = -1;
		
		if(col > origin.getCol()) colDir = 1;
		else if(col < origin.getCol()) colDir = -1;
		
		while(shapeSize > 0){
			if(isValidSquare(currRow, currCol)){
				affectedSquares.add(grid[currRow][currCol]);
				currRow += rowDir;
				currCol += colDir;
				//affectedSquares.add(grid[currRow][currCol]);
			}
			shapeSize--;
		}
	}
	
	private void highlightCone(CombatSquare origin, int row, int col, int shapeSize){
		int rowDir = 0, colDir = 0, currRow = row, currCol = col, coneWidth = 1;
		
		if(row > origin.getRow()) rowDir = 1;
		else if(row < origin.getRow()) rowDir = -1;
		
		if(col > origin.getCol()) colDir = 1;
		else if(col < origin.getCol()) colDir = -1;
		
		while(shapeSize > 0){
			if(rowDir == 0){ //horizontal
				highlightConeLine(currRow-(coneWidth-1)/2, currCol, 1, 0, coneWidth);
				
			}
			else if(colDir == 0){ //vertical
				highlightConeLine(currRow, currCol-(coneWidth-1)/2, 0, 1, coneWidth);
			}
			currRow += rowDir;
			currCol += colDir;
			coneWidth += 2;
			shapeSize--;
		}
	}
	
	private void highlightConeLine(int startRow, int startCol, int rowDir, int colDir, int length){
		while(length > 0){
			if(isValidSquare(startRow, startCol)){
				affectedSquares.add(grid[startRow][startCol]);
			}
			startRow += rowDir;
			startCol += colDir;
			length--;
		}
	}
	
	private void highlightCircle(int startRow, int startCol, int shapeSize){
		for(int i = startRow - shapeSize; i < startRow + shapeSize + 1; i++){
			for(int j = startCol - shapeSize; j < startCol + shapeSize + 1; j++){
				if(isValidSquare(i, j)){
					if(isInRange(grid[startRow][startCol], i, j, shapeSize, 0)){
						affectedSquares.add(grid[i][j]);
					}
				}
			}
		}
		
	}
	
	//big if statement to check if the target square isn't in one of the adjacent ordinals
	private boolean isValidConeLineStart(CombatSquare origin, int row, int col){
		if(!(row == origin.getRow()-1 && col == origin.getCol()) && !(row == origin.getRow()+1 && col == origin.getCol()) && 
				!(row == origin.getRow() && col == origin.getCol()-1) && !(row == origin.getRow() && col == origin.getCol()+1)){
			return false;
		}
		return true;
	}
	
	private boolean isInRange(CombatSquare origin, int row, int col, int range, double modifier){
		int rowDis = origin.getRow() - row;
		int colDis = origin.getCol() - col;
		
		double totalDis = Math.sqrt((double) (rowDis*rowDis + colDis*colDis));
		
		
		if(totalDis <= range + modifier){
			return true;
		}
		return false;
	}
	
	private boolean isValidSquare(int row, int col){
		if(row < 0 || row >=19){
			return false;
		}
		if(col < 0 || col >=19){
			return false;
		}
		return true;
	}
	
	public void resetHighlighting(){
		for(CombatSquare cs : affectedSquares){
			cs.setBackground(Color.LIGHT_GRAY); //in reality, set to the CS's default
		}
		affectedSquares.removeAll(affectedSquares);
	}

//turn methods	
	public boolean doAction(int row, int col) throws CombatFinishedEvent{
		
		switch(phase){
		case "move":
			if(validMove){
				if(move(row, col)){
					units.get(turnOwner).setCombatRow(row);
					units.get(turnOwner).setCombatCol(col);
				}
			}
			break;
		case "attack": 
			if(validAttack){
				if(attack()){
					passTurn();
				}
			}
			break;
		}
		
		return false;
	}
	
	private boolean move(int row, int col){
		CombatSquare origin = grid[((Character) units.get(turnOwner)).getCombatRow()][((Character) units.get(turnOwner)).getCombatCol()];
		origin.remove(units.get(turnOwner));
		grid[row][col].add(units.get(turnOwner));
		
		for(CombatSquare cs : affectedSquares){
			cs.setBackground(Color.LIGHT_GRAY); //in reality, set to the CS's default
		}
		affectedSquares.removeAll(affectedSquares);
		
		phase = "attack";
		hasMoved = true;
		return true;
	}
	
	private boolean attack(/*list of target squares*/){
		//no action selected
		if(currAction == -1){
			return false;
		}
		
		Character tempChar = null;
		SkillType type;
		SkillSubType[] subTypes;
		double damage = 0;
		
		try{
			tempChar = (Character) units.get(turnOwner);
		} catch(ClassCastException e){
			System.out.println("inside CombatLogic.attack() top catch(ClassCastException):");
			e.printStackTrace();
			//cast to companion instead
		}
		
		
		if(tempChar != null){
			type = tempChar.getActiveSkills().get(currAction).getType();
			subTypes = tempChar.getActiveSkills().get(currAction).getSubTypes();
			
			for(SkillSubType sst : subTypes){
				switch(sst){
				case BUFF: break;
				case DEBUFF: break;
				case CREATION: break;
				case DAMAGE:
					damage = tempChar.getDamage(currAction);
					break;
				case HEALING: break;
				}
			}
			
			tempChar.useResources(currAction);
			tempChar.getActiveSkills().get(currAction).placeOnCooldown();
			
			
			//loop through affectedSquares and apply the effect of the skill
			for(CombatSquare cs : affectedSquares){
				//System.out.println("Checking square [" + cs.getRow() + "," + cs.getCol() + "]");
				if(cs.containsSomething()){
					if(cs.containsUnit()){
						System.out.println(tempChar.getName() + " is dealing " + damage + " damage to " + cs.getUnit().getName());
						cs.getUnit().takeDamage(damage);
						cs.getUnit().updateVSPanel();
					}
				}
			}
			
			
			
			//temp stuff for overall combat testing
			/*double temp = tempChar.getDamage();
			
			System.out.print(tempChar.getName() + " hit for " + temp + " damage ");
			
			units.get(1).takeDamage(temp);
			units.get(1).updateVSPanel();
			
			System.out.println(((Monster) units.get(1)).getName() + "'s hp = "+ ((Monster) units.get(1)).getModifiedStats().getCurrentHealth());*/
		}
		
		//turn successfully finished
		return true;
	}
	
}
