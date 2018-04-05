package prototype;



import java.awt.Color;




import prototype.Coordinate;




public abstract class PingusState  {  //PingusState
	
	private Coordinate pos;   // occupée dans la Grille 
	private Color couleur ;
	private Direction direction;
	private Direction old; 
	private boolean alive ;
	private int vie;
	private Game game;
	
	//state

	
	//private Affectaion type;
	
      

	public PingusState(Game game,Coordinate pos,Color c) {
		super();

		this.pos=pos;
		this.game=game;
		this.couleur = c;
		this.direction = Direction.Right;
		this.old=getDirection();
		this.alive = true;
		this.vie=5;
		
	}
	
	///////////
	/*public void register(PingusObserver o) {
  	  observers.add(o);
    }
    public void unregister(PingusObserver o) {
  	  observers.remove(o);
    }
    private void notifyObserver (List<PingusEvent>events) {
  	  for (PingusObserver snakeObserver : observers) {
			snakeObserver.notify(events);
		}
    }*/
  	  ////////////
	public Coordinate getPos() {
		return pos;
	}

	public void setPos(Coordinate pos) {
		this.pos = pos;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean isAlive) {
		this.alive = isAlive;
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
/////////////////////

	

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	///////////////////////////
	

	

	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}
	abstract void tache(Coordinate next);
	abstract void tacheChute(Coordinate next);
	abstract void tacheMarche(Coordinate next);

	public Direction getOld() {
		return old;
	}

	public void setOld(Direction old) {
		this.old = old;
	}
	

}




	


