package prototype;


import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JFrame;


public class Gui implements LemmingObserver {

	private Game game;
	private List<Obstacle> obstacle;
	private static final int scale = 20;
	private JFrame frame = new JFrame("Lemming game");
	private Grille component;

	@SuppressWarnings("serial")
	public Gui(Game game) {
		this.game = game;
		this.obstacle = game.getObstacle();
		this.component = new Grille(game.getLemmings()) {

			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.setColor(Color.CYAN);
				g.fillOval(game.getEntree().getPos().getX() * scale, game.getEntree().getPos().getY() * scale, scale,
						scale);
				
				g.setColor(Color.orange);
				g.fillOval(game.getSortie().getPos().getX() * scale, game.getSortie().getPos().getY() * scale, scale,
						scale);

				for (PingusState c : game.getLemmings().getPingus()) {
					g.setColor(c.getCouleur());
					g.fill3DRect(c.getPos().getX() * scale, c.getPos().getY() * scale, scale, scale, true);

				}

				for (Obstacle o : obstacle) {
					g.setColor(o.getCouleur());
					g.fill3DRect(o.getPos().getX() * scale, o.getPos().getY() * scale, scale, scale, false);

				}

			}
		};
		
        
		frame.setContentPane(component);
		frame.setSize(game.getWidth() * scale, game.getHeight() * scale);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	@Override
	public void notify(List<PingusEvent> events) {
		// TODO Auto-generated method stub
		component.repaint();

	}

	public Game getGame() {
		return game;
	}

}
