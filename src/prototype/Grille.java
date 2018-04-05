package prototype;

import java.awt.Checkbox;



import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
@SuppressWarnings("serial")





public class Grille extends JPanel  {

	private MesLemming lem;
	 

    public Grille (MesLemming lem) {
  	     this.lem=lem;

  	     this.setBorder(new LineBorder(Color.YELLOW, 1 ));
  	     //this.setBackground(Color.cyan);  
  	    CheckboxGroup groupe=new CheckboxGroup();  
	    Checkbox marcheur=new Checkbox("Marcheur",groupe,false);  	    
	    Checkbox bloqueur=new Checkbox("Bloqueur",groupe,false); 
	    Checkbox tunnelier=new Checkbox("Tunnelier",groupe,false);
	    Checkbox foreur=new Checkbox("Foreur",groupe,false);
	    Checkbox bombeur=new Checkbox("Bombeur",groupe,false);
	    Checkbox parachutiste=new Checkbox("Para",groupe,false);
	    Checkbox grimpeur=new Checkbox("Grimpeur",groupe,false);
	    Checkbox charpentier=new Checkbox("Charpentier",groupe,false);
	    this.add(marcheur);
        this.add(bloqueur);
        this.add(tunnelier);
        this.add(foreur);
        this.add(bombeur);
        this.add(parachutiste);
        this.add(grimpeur);
        this.add(charpentier);
      // System.out.println("box1 "+ marcheur.getState());
        
         this.addMouseListener(new MouseAdapter() {
        	  @Override
              public void mouseClicked(MouseEvent e){
        	    int x = e.getX()/20;
              int y = e.getY()/20;
              System.out.println("mouse    "+x + " " + y);
              Checkbox cb=groupe.getSelectedCheckbox();
             if(cb!=null) { 
            	 System.out.println("cb= "+cb.getLabel());
                 lem.mouse(x, y , cb);
             }
              
  	  }
      });
  	 
  	  
    }


   


	public MesLemming getLem() {
		return lem;
	}


	public void setLem(MesLemming lem) {
		this.lem = lem;
	}


	


	

	
}
