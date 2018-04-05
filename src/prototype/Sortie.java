package prototype;

import javax.swing.JOptionPane;



public class Sortie {
	private Coordinate pos;
	private int compteur;
	

	public Sortie(Coordinate pos, int compteur) {
		super();
		this.pos = pos;
		this.compteur = compteur;
	}

	public Coordinate getPos() {
		return pos;
	}

	public void setPos(Coordinate pos) {
		this.pos = pos;
	}

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}
	
	public void compter() {
		setCompteur(getCompteur()+1);
		System.out.println("Le nombre le lemmings suvécus est :"+ getCompteur());
	}
	public void infoJeu() {
	    // TODO Auto-generated method stub
	JOptionPane.showMessageDialog(null,"Game Over ! Le nombre de lemmings sauvés est : "+ getCompteur(),null,0);
}

}
