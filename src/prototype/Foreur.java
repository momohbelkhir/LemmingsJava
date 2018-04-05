package prototype;

import java.awt.Color;

public class Foreur extends PingusState {

	private static final int FOREUR_TIME = 5;
	private int Ftimer;

	public Foreur(Game game, Coordinate pos, Color c) {
		super(game, pos, c);
		// TODO Auto-generated constructor stub
		this.Ftimer = 1;
	}

	@Override
	void tache(Coordinate next) {
		// TODO Auto-generated method stub
	
		if (isAlive() && Ftimer < FOREUR_TIME) {

			for (int j = 0; j < getGame().getObstacle().size(); j++) {
				Obstacle ob = getGame().getObstacle().get(j);
				if (ob.getType() == TypeOb.Destruct) {
					if (ob.getPos().getX() == next.getX() && ob.getPos().getY() == next.getY()) {
						getGame().getObstacle().remove(j);
					}
				}else if(ob.getType() == TypeOb.DestructBomb) {
					
					if (ob.getPos().getX() == next.getX() && ob.getPos().getY() == next.getY()) {
						
					
					    getGame().getLemmings().removeLemming(ob.getPos());
					    getGame().getObstacle().remove(j);
					    
					}
				
			
		       }else if(ob.getType() == TypeOb.DestructObs) {
					
					if (ob.getPos().getX() == next.getX() && ob.getPos().getY() == next.getY()) {
						getGame().getLemmings().creerObstacle();
				        getGame().getObstacle().remove(j);
					    
					}
				
			
		       }
			}

			Ftimer++;
		}

	}

	public int getFtimer() {
		return Ftimer;
	}

	public void setFtimer(int ftime) {
		Ftimer = ftime;
	}

	public static int getForeurTime() {
		return FOREUR_TIME;
	}

	@Override
	void tacheChute(Coordinate next) {
		// TODO Auto-generated method stub
		setVie(getVie() - 1);
		setPos(next);
         
		if (getGame().isOut(next) || getVie() == 0) {
			setAlive(false);
			getGame().getLemmings().getPingus().remove(this);// remove(i);

		} else if (!getGame().chute(next) && Ftimer >= 5) { // mais un moment en chuttant il trouve un sol.
			///
			Coordinate c1 = new Coordinate(next.getX() + 1, next.getY());
			Coordinate c2 = new Coordinate(next.getX() - 1, next.getY());
            System.out.println("old====== "+getOld()+" ");
			if (getGame().getLemmings().obChute(c1))
				if (getOld()== Direction.Left)
					setDirection(Direction.Left);
				else
					setDirection(Direction.Right);

			else if (getGame().getLemmings().obChute(c2))
				if (getOld() == Direction.Left)
					setDirection(Direction.Left);
				else
					setDirection(Direction.Right);
			
			else if (getOld()== Direction.Left)
				setDirection(Direction.Left);
			else
				setDirection(Direction.Right);

		}

	}

	@Override
	void tacheMarche(Coordinate next) {
		// TODO Auto-generated method stub
		if (getGame().isOut(new Coordinate(next.getX() - 1, next.getY()))
				|| getGame().isOut(new Coordinate(next.getX() + 1, next.getY()))) { // sur les cotés

			if (getDirection() == Direction.Left) {
				setDirection(Direction.Right);
				setOld(Direction.Right);
			}
			else {
				setDirection(Direction.Left);
				setOld(Direction.Left);
			}
		}

		if (getDirection() != Direction.Bloquer) { // le bloqueur ne peut pas avoir du next

			if ((getGame().getLemmings().obContains(next) && getDirection() == Direction.Left)) { // ob rencontré par la
																									// gauche

				if ((!getGame().getLemmings().obContains(new Coordinate(next.getX(), next.getY() - 1))
						&& !getGame().getLemmings().obContains(new Coordinate(next.getX() + 1, next.getY() - 1))
						&& !getGame().getLemmings().isBloqueur(next))
						&& (!getGame().getLemmings().isBloqueur(new Coordinate(next.getX() + 1, next.getY() - 1))
								&& !getGame().getLemmings().isBloqueur(new Coordinate(next.getX(), next.getY() - 1))))

					setPos(new Coordinate(next.getX(), next.getY() - 1)); // sauter sur l ob
				else {
					setDirection(Direction.Right);
				    setOld(Direction.Right);
				}

			} else if ((getGame().getLemmings().obContains(next) && getDirection() == Direction.Right)) { // par la
																											// droit

				if ((!getGame().getLemmings().obContains(new Coordinate(next.getX(), next.getY() - 1))
						&& !getGame().getLemmings().obContains(new Coordinate(next.getX() - 1, next.getY() - 1))
						&& !getGame().getLemmings().isBloqueur(next))
						&& (!getGame().getLemmings().isBloqueur(new Coordinate(next.getX(), next.getY() - 1))
								&& !getGame().getLemmings()
										.isBloqueur(new Coordinate(next.getX() - 1, next.getY() - 1))))// aucun ob en
					// haut est
					// le
					// next n
					// est pas
					// un
					// bloqueur
					setPos(new Coordinate(next.getX(), next.getY() - 1)); // sauter sur l ob
				else {
					setDirection(Direction.Left);
					setOld(Direction.Left);
				}

			} else {
				if (getGame().getLemmings().isBloqueur(next)
						|| getGame().getLemmings().isBloqueur(new Coordinate(next.getX(), next.getY() + 1))) {
					if (getDirection() == Direction.Left) {
						setDirection(Direction.Right);
					    setOld(Direction.Right);
					}
					else {
						setDirection(Direction.Left);
						setOld(Direction.Left);
					}
				} else
					setPos(next);
			}
		}

	}

}
