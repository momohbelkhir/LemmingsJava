package prototype;

import java.awt.Color;

public class Obstacle {
	
	
   private Coordinate pos;
   private Color couleur;
   private TypeOb type;

	public Obstacle(Coordinate pos,Color c,TypeOb type) {
		
		this.pos = pos;
		this.couleur = c;
		this.type=type;
	}
	

	public Coordinate getPos() {
		return pos;
	}


	public void setPos(Coordinate pos) {
		this.pos = pos;
	}


	public Color getCouleur() {
		return couleur;
	}


	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}


	public TypeOb getType() {
		return type;
	}


	public void setType(TypeOb type) {
		this.type = type;
	}
     
    

	/*public Color getCouleur() {
		return couleur;
	}


	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}*/

  
	 
}
