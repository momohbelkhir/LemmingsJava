package prototype;

import java.awt.Checkbox;


import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import prototype.PingusEvent.ChangeType;

public class MesLemming {
	private List<PingusState> pingus;
	private List<LemmingObserver> observers;
	private Game game;
	private int cpt = 0; // pour faire une marge de 4 pas pour chaque lemming
	private int n; // le nobre de lemming

	public MesLemming(Game game, int n) {
		this.pingus = new ArrayList<PingusState>();
		this.observers = new ArrayList<>();
		this.setGame(game);
		this.n = n;

	}
	public void removeLemming(Coordinate posOb){ //pour le bombeur & tunnelier //ob oxplosable 
		for (int i=0; i<getGame().getLemmings().getPingus().size();i++) {
			PingusState p= getGame().getLemmings().getPingus().get(i);
			int x = p.getPos().getX();
			int y = p.getPos().getY();

			if (posOb.getX()-1 ==x  && posOb.getY()-1 ==y ) 
				game.getLemmings().getPingus().remove(p);
			if (posOb.getX() ==x  && posOb.getY()-1 ==y ) 
				game.getLemmings().getPingus().remove(p);
			if (posOb.getX()+1 ==x && posOb.getY()-1 ==y ) 
				game.getLemmings().getPingus().remove(p);
			
			if (posOb.getX()-1 ==x  && posOb.getY() ==y ) 
				game.getLemmings().getPingus().remove(p);
			if (posOb.getX()+1 ==x  && posOb.getY() ==y ) 
				game.getLemmings().getPingus().remove(p);
			
			if (posOb.getX()-1 ==x  && posOb.getY()+1 ==y ) 
				game.getLemmings().getPingus().remove(p);
			if (posOb.getX() ==x  && posOb.getY()+1 ==y ) 
				game.getLemmings().getPingus().remove(p);
			if (posOb.getX()+1 ==x && posOb.getY()+1 ==y ) 
				game.getLemmings().getPingus().remove(p);
			
		}
		
		
	}
	public void creerObstacle() {
		  Random r = new Random();
		Coordinate c = new Coordinate(r.nextInt(getGame().getHeight()), r.nextInt(getGame().getWidth()));
		 getGame().getObstacle().add(new Obstacle(c,Color.green,TypeOb.DestructObs));
	}

	public void register(LemmingObserver o) {
		observers.add(o);
	}

	public void unregister(LemmingObserver o) {
		observers.remove(o);
	}

	private void notifyObserver(List<PingusEvent> events) { 
		for (LemmingObserver lemmingObserver : observers) {
			lemmingObserver.notify(events);
		}

	} 

	public List<PingusState> getPingus() {
		return pingus;
	}

	public void setPingus(List<PingusState> pingus) {
		this.pingus = pingus;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void move() {
		List<Coordinate> pinE = new ArrayList<>();
		List<Coordinate> pinL = new ArrayList<>();
		List<PingusEvent> pin = new ArrayList<>();

		for (int i = 0; i < pingus.size(); i++) {

			PingusState p = pingus.get(i);
			Coordinate current = p.getPos();
			Coordinate next = new Coordinate(current.getX() + p.getDirection().getX(),
					current.getY() + p.getDirection().getY());
			//
			isSortie(p,next);  //supprime le lemmings qui sort
			
			if(game.isLave(current, next)){
				game.getLemmings().getPingus().remove(p);
			}
			//
			if(game.isTeleporteur(current)) {
				teleportation(p);
			}
			// tache :: tacheMarche :: tacheChute.
			if(p.isAlive()) p.tache(next); 
			
			if ( ( !game.chute(current) || p.getDirection()==Direction.Up) && p.isAlive()){ // si le pinug n'est pas en chute
				//Direction.Up grimpeur
				p.setVie(5);
				pinE.add(next);
				pinL.add(current);

				p.tacheMarche(next);// ici

			} else if ( p.isAlive() && game.chute(current) && p.getDirection() != Direction.Bloquer
					&& p.getDirection() != Direction.Up) { // si le pinug est en chute
																									
				
				p.setDirection(Direction.Down);
				
				next = new Coordinate(current.getX() + p.getDirection().getX(),
						current.getY() + p.getDirection().getY());
				pinE.add(next);
				pinL.add(current);
				//
				p.tacheChute(next);

			}

		} // end for

		cpt++;
		pin.add(new PingusEvent(pinE, ChangeType.ENTER));
		pin.add(new PingusEvent(pinL, ChangeType.LEAVE));
		notifyObserver(pin);
		
		if (tousMort()) {
			game.setGameOver(true);
		}
		if (cpt % 4 == 0 && cpt < n * 4)
			getPingus().add(new Marcheur(game, new Coordinate(0, 0), Color.green));

	}// end move

	private boolean tousMort() {
		// TODO Auto-generated method stub
		//for (PingusState p : pingus)
			if (game.getLemmings().getPingus().isEmpty()) { 
	            System.out.println("Partie Terminée ! Lemmings sauvés = "
			       +game.getSortie().getCompteur());
	            game.getSortie().infoJeu();
				return true;
				
			}
		return false;
	}

	public boolean obContains(Coordinate c) { // si c existe dans la liste d'obstacles
		for (Obstacle ob : game.getObstacle()) {
			int x = ob.getPos().getX();
			int y = ob.getPos().getY();

			if (x == c.getX() && y == c.getY()) {
				return true;
			}
		}
		return false;
	}
	public void teleportation(PingusState p) {
		// TODO Auto-generated method stub
		Random r = new Random();
		Coordinate c = new Coordinate(r.nextInt(getGame().getHeight()), r.nextInt(getGame().getWidth()));
		while(obContains(c) || !obContains(new Coordinate(c.getX(),c.getY()+3)) 
				|| game.isObLave(new Coordinate(c.getX(),c.getY()+3))  ) {
			c.setX( r.nextInt(getGame().getHeight()) );
			c.setY( r.nextInt(getGame().getWidth()) ) ;
				
		}
		 getGame().getObstacle().add(new Obstacle(c,Color.white,TypeOb.Teleporteur));
		 game.getLemmings().getPingus().add(new Marcheur(game,c,Color.green));
		 game.getLemmings().getPingus().remove(p);
			
		
		
	}
	
	public void isSortie(PingusState p, Coordinate next) { //passé par la sortie
		if(next.equals(game.getSortie().getPos()) ) {
			game.getLemmings().getPingus().remove(p);
			game.getSortie().compter();
		}
		
	}

	public boolean obChute(Coordinate c) { // Coordinate de l'ob en chutant et en trouvant un sol <P> c=next><

		for (Obstacle ob : game.getObstacle()) {

			int x = ob.getPos().getX();
			int y = ob.getPos().getY();

			if (c.getX() == x && c.getY() == y) {
				return true;
			}
		}

		return false;

	}

	public boolean isBloqueur(Coordinate c) { // si un bloqueur existe dans la liste des lemming
		for (PingusState p : pingus) {
			if (p.getPos().equals(c) && p.getDirection() == Direction.Bloquer)
				return true;
		}
		return false;
	}

	void mouse(int x, int y, Checkbox cb) {
		// TODO Auto-generated method stub
		for (int i = 0; i < pingus.size(); i++) {
			PingusState p = pingus.get(i);
			if (p.getPos().getX() == x && p.getPos().getY() == y) {
				// affectation du lemming avec le chekbox coché
				if (cb.getState() == true) {
					if (cb.getState()) { 
						if (cb.getLabel() == "Tunnelier") {
							PingusState p1 = new Tunnelier(game, new Coordinate(x, y), Color.yellow);
							if (p.getDirection() != Direction.Bloquer) {
								p1.setDirection(p.getDirection());
							    p1.setOld(p.getOld());}

							pingus.remove(p);
							pingus.add(p1);
							//p1.tache();

						} else if (cb.getLabel() == "Bloqueur") {
							PingusState p2 = new Bloqueur(game, new Coordinate(x, y), Color.magenta);
							p2.setDirection(p.getDirection());
							p2.setOld(p.getOld());
							pingus.remove(p);
							pingus.add(p2);
							//p2.tache();
						} else if (cb.getLabel() == "Marcheur") {

							PingusState p3 = new Marcheur(game, new Coordinate(x, y), Color.green);
							if (p.getDirection() != Direction.Bloquer) {
								p3.setDirection(p.getDirection());
								p3.setOld(p.getOld());
							}

							pingus.remove(p);
							pingus.add(p3);

						} else if (cb.getLabel() == "Foreur") {

							PingusState p4 = new Foreur(game, new Coordinate(x, y), Color.cyan);
							// if (p.getDirection() != Direction.Bloquer)
							p4.setDirection(Direction.Down);
							p4.setOld(p.getOld());

							pingus.remove(p);
							pingus.add(p4);
							//p4.tache();

						} else if (cb.getLabel() == "Bombeur") {
							PingusState p5 = new Bombeur(game, new Coordinate(x, y), Color.red);
							if (p.getDirection() != Direction.Bloquer) {
								p5.setDirection(p.getDirection());
								p5.setOld(p.getOld());}

							pingus.remove(p);
							pingus.add(p5);
							//p5.tache();

						} else if (cb.getLabel() == "Para") {
							PingusState p6 = new Parachutiste(game, new Coordinate(x, y), Color.gray);
							if (p.getDirection() != Direction.Bloquer) {
								p6.setDirection(p.getDirection());
								p6.setOld(p.getOld());}

							pingus.remove(p);
							pingus.add(p6);
							//p6.tache();

						} else if (cb.getLabel() == "Grimpeur") {
							PingusState p7 = new Grimpeur(game, new Coordinate(x, y), Color.pink);
							if (p.getDirection() != Direction.Bloquer) {
								p7.setDirection(p.getDirection());
								p7.setOld(p.getOld());}

							pingus.remove(p);
							pingus.add(p7);
							//p7.tache();

						}else if (cb.getLabel() == "Charpentier") {
							PingusState p8 = new Charpentier(game, new Coordinate(x, y), Color.black);
							if (p.getDirection() != Direction.Bloquer) {
								p8.setDirection(p.getDirection());
								p8.setOld(p.getOld());}

							pingus.remove(p);
							pingus.add(p8);
							//p8.tache();

						}

					}
				}
			}
		}
	}

}
