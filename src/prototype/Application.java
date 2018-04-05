package prototype;

import java.util.List;


import prototype.Game;
import prototype.Gui;
import prototype.PingusEvent;


public class Application {
	static final int SPEED = 400;

	public static void main(String[] args) {
		Game game = new Game(35, 30); 

         
		
     game.getLemmings().register(new Gui(game));
     
     game.getLemmings().register(new LemmingObserver() { 
			@Override
			public void notify(List<PingusEvent> events) {
				for (PingusEvent event : events) {
					System.out.println(event);
				}
			}
		});
     
		while (!game.isGameOver()) {
			 
			game.step();
			try {
				Thread.sleep(SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
