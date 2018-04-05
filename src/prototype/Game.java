package prototype;

import java.awt.Color;




import java.util.ArrayList;
import java.util.List;






public class Game {
	     //charger un niveau.
	     private int height;
	     private int width;
	     private MesLemming lemmings;  //gui aussi a list pingus
	     private List<Obstacle> obstacle;
	     private boolean gameOver = false;
	     private Entree entree;
	     private Sortie sortie; 
	     

	    


		public Game(int width, int height) {
			this.entree= new Entree(new Coordinate(0,0),5);
			this.sortie= new Sortie(new Coordinate(34,27),0);
			this.height = height;
			this.width = width;
		    this.lemmings =new MesLemming(this,entree.getNombreLemming());
			this.obstacle= new ArrayList<>();
			
			level1();
		}	
       public boolean chute(Coordinate c) {
    	   for(Obstacle ob : obstacle) {
    		   if(ob.getPos().getX()==c.getX() && ob.getPos().getY()-1==c.getY()) {
    			   return false; // marcher
    		   }
    	   }
		return true; //chuter
       }
		
		public int getHeight() {
			return height;
		}

		public int getWidth() {
			return width;
		}
		
		 public MesLemming getLemmings() {
			return lemmings;
		}
		public void setLemmings(MesLemming lemmings) {
			this.lemmings = lemmings;
		}
		boolean isOut(Coordinate c) { //pingus out
			if (c.getX() < 0 || c.getY() < 0)
				return true;
			if (c.getX() >= width || c.getY() >= height)
				return true;
			return false;
		}
		
		 

		public void step() {
			if(!gameOver)
			lemmings.move();
			 
		}

		public boolean isGameOver() {
			return gameOver;
		}

		public void setGameOver(boolean gameOver) {
			this.gameOver = gameOver;
		}



		

		public List<Obstacle> getObstacle() {
			return obstacle;
		}
		public void obb(Coordinate c) {
			obstacle.add(new Obstacle(c,Color.magenta,TypeOb.NonDestruct));
		}
		public boolean isLave(Coordinate c,Coordinate next) {  //si c ou next corresponde à une lave
			// TODO Auto-generated method stub
			for(Obstacle ob : obstacle) {
	    		   if(ob.getPos().getX()==c.getX() && ob.getPos().getY()-1==c.getY() && ob.getType()==TypeOb.Lave) {
	    			   return true; //marcher sur une lave ou pas
	    		   }else if(ob.getPos().getX()==next.getX() && ob.getPos().getY()==next.getY() && ob.getType()==TypeOb.Lave) {
	    			   return true; //le next est une lave ou pas
	    		   }
	    		   
	    	   }
			return false;
		}
		public boolean isObLave(Coordinate c) {// si c corresponde à une lave 
			// TODO Auto-generated method stub
			for(Obstacle ob : obstacle) {
	    		   if(ob.getPos().getX()==c.getX() && ob.getPos().getY()==c.getY() && ob.getType()==TypeOb.Lave) {
	    			   return true; //c corresponds à une lave
	    		   }
	    		   
	    	   }
			return false;
		}
		
		
		public boolean isTeleporteur(Coordinate c) { //si c correspond à un teleporteur
			// TODO Auto-generated method stub
			for(Obstacle ob : obstacle) {
	    		   if(ob.getPos().getX()==c.getX() && ob.getPos().getY()-1==c.getY() && ob.getType()==TypeOb.Teleporteur) {
	    			   return true; //marcher sur un teleporteur
	    		   }
	    		   
	    	   }
			return false;  
			
		}
		public Entree getEntree() {
			return entree;
		}
		public void setEntree(Entree entree) {
			this.entree = entree;
		}
		public Sortie getSortie() {
			return sortie;
		}
		public void setSortie(Sortie sortie) {
			this.sortie = sortie;
		}
		public void level1() {  //niveau a faire charger depuis un fichier
			for(int i=2;i<getWidth();i++) {
				obstacle.add(new Obstacle(new Coordinate(i,1),Color.DARK_GRAY,TypeOb.NonDestruct) );
			    //obstacle.add(new Obstacle(new Coordinate(i,0),Color.DARK_GRAY,TypeOb.NonDestruct) );
			}
			
			for(int i=18;i<getWidth();i++) {
			    obstacle.add(new Obstacle(new Coordinate(i,getHeight()-1),Color.green,TypeOb.DestructObs) );
			}
			for(int i=0;i<18;i++) {
					
			    obstacle.add(new Obstacle(new Coordinate(i,getHeight()-1),Color.red,TypeOb.Lave) );
			}
			lemmings.getPingus().add(new Marcheur(this,new Coordinate(0,0),Color.green));
		
			for(int i=0;i<9;i++)
				if(i!=6)obstacle.add(new Obstacle(new Coordinate(i,6),Color.blue,TypeOb.Destruct));
			
			obstacle.add(new Obstacle(new Coordinate(19,7),Color.white,TypeOb.Teleporteur) );
			obstacle.add(new Obstacle(new Coordinate(2,3),Color.blue,TypeOb.Destruct) );
			obstacle.add(new Obstacle(new Coordinate(0,3),Color.blue,TypeOb.Destruct)) ;
			obstacle.add(new Obstacle(new Coordinate(1,3),Color.blue,TypeOb.Destruct)) ;
			obstacle.add(new Obstacle(new Coordinate(6,5),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(6,7),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(7,8),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(6,9),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(8,9),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(10,9),Color.green,TypeOb.DestructObs));
			obstacle.add(new Obstacle(new Coordinate(11,8),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(12,7),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(11,6),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(17,9),Color.green,TypeOb.DestructObs));
			obstacle.add(new Obstacle(new Coordinate(15,9),Color.green,TypeOb.DestructObs));
			obstacle.add(new Obstacle(new Coordinate(21,9),Color.yellow,TypeOb.DestructBomb));
			obstacle.add(new Obstacle(new Coordinate(0,10),Color.red,TypeOb.Lave));
			for(int i=1;i<width;i++)
				obstacle.add(new Obstacle(new Coordinate(i,10),Color.blue,TypeOb.Destruct));
			
			 obstacle.add(new Obstacle(new Coordinate(19,9),Color.DARK_GRAY,TypeOb.NonDestruct));
			 obstacle.add(new Obstacle(new Coordinate(4,9),Color.blue,TypeOb.Destruct));
			 obstacle.add(new Obstacle(new Coordinate(19,8),Color.blue,TypeOb.Destruct));
			 obstacle.add(new Obstacle(new Coordinate(9,11),Color.green,TypeOb.DestructObs));
			 obstacle.add(new Obstacle(new Coordinate(9,12),Color.green,TypeOb.DestructObs));
			 obstacle.add(new Obstacle(new Coordinate(9,13),Color.green,TypeOb.DestructObs));
			 
			for(int i=0;i<15;i++)
				obstacle.add(new Obstacle(new Coordinate(i,14),Color.blue,TypeOb.Destruct));
			for(int i=26;i<getHeight()-1;i++)
				obstacle.add(new Obstacle(new Coordinate(32,i),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(33,26),Color.green,TypeOb.DestructObs));
			obstacle.add(new Obstacle(new Coordinate(34,26),Color.green,TypeOb.DestructObs));
			
			obstacle.add(new Obstacle(new Coordinate(15,13),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(15,17),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(16,20),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(17,23),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(18,25),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(1,11),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(2,12),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(3,13),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(18,28),Color.blue,TypeOb.Destruct));
			obstacle.add(new Obstacle(new Coordinate(18,27),Color.blue,TypeOb.Destruct));
		}
		
	
		

		
	     

}
