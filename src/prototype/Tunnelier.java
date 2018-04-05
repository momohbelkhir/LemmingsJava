package prototype;

import java.awt.Color;

public class Tunnelier extends PingusState {

	private static final int TUNEL_TIME = 5;
	private int Ttimer;

	public Tunnelier(Game game, Coordinate pos, Color c) {
		super(game, pos, c);
		// TODO Auto-generated constructor stub
		this.Ttimer = 0;
	}

	@Override
	void tache(Coordinate next) {
		// TODO Auto-generated method stub

		// Coordinate current = this.getPos();
		// Coordinate next = new Coordinate(current.getX() + getDirection().getX(),
		// current.getY() + getDirection().getY());
		if (isAlive() && Ttimer < TUNEL_TIME) {

			for (int j = 0; j < getGame().getObstacle().size(); j++) {
				Obstacle ob = getGame().getObstacle().get(j);

				if (ob.getType() == TypeOb.Destruct) {
					if (ob.getPos().getX() == next.getX() && ob.getPos().getY() == next.getY()) {
						getGame().getObstacle().remove(j);
					}
				} else if (ob.getType() == TypeOb.DestructBomb) {

					if (ob.getPos().getX() == next.getX() && ob.getPos().getY() == next.getY()) {

						getGame().getLemmings().removeLemming(ob.getPos());
						getGame().getObstacle().remove(j);

					}

				} else if (ob.getType() == TypeOb.DestructObs) {

					if (ob.getPos().getX() == next.getX() && ob.getPos().getY() == next.getY()) {
						getGame().getLemmings().creerObstacle();
						getGame().getObstacle().remove(j);

					}

				}
			}

			Ttimer++;
		}
		System.out.println("tachhe fini Tunnelier" + Ttimer);
		// en comain

	}

	public static int getTunelTime() {
		return TUNEL_TIME;
	}

	public int getTtimer() {
		return Ttimer;
	}

	public void setTtimer(int ttimer) {
		Ttimer = ttimer;
	}

	@Override
	void tacheChute(Coordinate next) {
		// TODO Auto-generated method stub

		setVie(getVie() - 1);
		setPos(next);

		if (getGame().isOut(next) || getVie() == 0) {
			setAlive(false);
			getGame().getLemmings().getPingus().remove(this);// remove(i);

		} // mais un moment en chuttant il trouve un sol.
		else if (!getGame().chute(next)) {
			Coordinate c1 = new Coordinate(next.getX() + 1, next.getY());
			Coordinate c2 = new Coordinate(next.getX() - 1, next.getY());

			if (getGame().getLemmings().obChute(c1))
				if (getOld() == Direction.Left)
					setDirection(Direction.Left);
				else
					setDirection(Direction.Right);

			else if (getGame().getLemmings().obChute(c2))
				if (getOld() == Direction.Left)
					setDirection(Direction.Left);
				else
					setDirection(Direction.Right);

			else
				setDirection(Direction.Right);

		}
		///

	}

	@Override
	void tacheMarche(Coordinate next) {
		// TODO Auto-generated method stub
		System.out.println("tacheMarche " + getDirection());
		if (getGame().isOut(new Coordinate(next.getX() - 1, next.getY()))
				|| getGame().isOut(new Coordinate(next.getX() + 1, next.getY()))) { // sur les cotés

			if (getDirection() == Direction.Left) {
				setDirection(Direction.Right);
				setOld(Direction.Right);
			} else {
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
					setPos(new Coordinate(getPos().getX() + getDirection().getX(),
							getPos().getY() + getDirection().getY()));
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
					} else {
						setDirection(Direction.Left);
						setOld(Direction.Left);
					}
				} else
					setPos(next);
			}
		}

	}

}
