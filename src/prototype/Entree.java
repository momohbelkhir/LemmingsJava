package prototype;

public class Entree {
	private Coordinate pos;
	private int nombreLemming;
	
	

	public Entree(Coordinate pos, int nombreLemming) {
		super();
		this.pos = pos;
		this.nombreLemming = nombreLemming;
	}

	public Coordinate getPos() {
		return pos;
	}

	public void setPos(Coordinate pos) {
		this.pos = pos;
	}

	public int getNombreLemming() {
		return nombreLemming;
	}

	public void setNombreLemming(int nombreLemming) {
		this.nombreLemming = nombreLemming;
	}

}
