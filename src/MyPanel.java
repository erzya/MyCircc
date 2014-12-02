import java.awt.Graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class  MyPanel extends JPanel {
    private  static List<PaintOval> list = new ArrayList<PaintOval>();
    
    private static boolean gravityCheck = false;


	
	private Timer timer = new Timer();;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;


    MyPanel() {
        addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent me)
            {

                PaintOval temp = new PaintOval(me.getX(), me.getY());
                setBorder (temp);
                if ((me.getX() > minX) && (me.getX() < maxX)
                        && (me.getY() > minY) && (me.getY() < maxY))
                {

                    list.add(temp);

                }
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
        	
        	
        	public void mouseDragged(MouseEvent me) {
        		

        		for (int i =0; i<list.size(); i++) {
        			 double dx = me.getX() - list.get(i).getXpos();
    				 double dy = me.getY() - list.get(i).getYpos();
        			
    				 if ( Math.hypot(dx,dy) < (list.get(i).getRadius())){
        				 
        				 list.get(i).setXpos( me.getX());
        				 list.get(i).setYpos( me.getY() );
        				 
        				 list.get(i).setAngle(0.5 * Math.PI - Math.atan2(dy, dx));
        				 list.get(i).setSpeed(Math.hypot(dx, dy) * 0.9); 
        			 }
        		 } 	
        	}
        });
      
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i =0; i<list.size(); i++) {
                	
                    testCollisionBorder(list.get(i),i);
                    for (int j = 0; j < list.size(); j++)        
                    {
                        if (j==i){

                            break;
                        }else {

                          testCircleIntersection(list.get(i), list.get(j));

                        }
                    }
                    moveCircle(list.get(i));
                }
                repaint();
            }
        }, 0, 20);
    }

    

	protected void setBorder (PaintOval i) {
        minX =  i.getRadius();
        minY =  i.getRadius();
        maxX =  (getWidth() - i.getRadius());
        maxY =  (getHeight() - i.getRadius());

    }

    protected void moveCircle(PaintOval i) {
    	double friction =0.95;
    	if(isGravityCheck()) {
    		addVectors(i, 2 * Math.PI, 0.08);
    	}
        i.setXpos(i.getXpos() + Math.sin(i.getAngle()) * i.getSpeed() * friction) ;
        i.setYpos(i.getYpos() + Math.cos(i.getAngle()) * i.getSpeed() * friction);

    }


    protected void testCollisionBorder(PaintOval i, int a) {

        setBorder(i);
        double elastic = 0.95;

        if (i.getXpos() >= maxX) {
            i.setXpos((int) (2 * (getWidth() - i.getRadius())) - i.getXpos());

            i.setAngle(i.getAngle() * -1);
            i.setSpeed(i.getSpeed() * elastic);

        } else if (i.getXpos() <= minX) {
            i.setXpos(i.getRadius() * 2 - i.getXpos());
            i.setAngle(i.getAngle() * -1);
            i.setSpeed(i.getSpeed() * elastic);

        }
        else if (i.getYpos() <= minY) {
            i.setYpos(i.getRadius() * 2 - i.getYpos());
            i.setAngle(Math.PI - i.getAngle());
            i.setSpeed(i.getSpeed() * elastic);

        } else if (i.getYpos() >= maxY) {

            i.setYpos((int) (2 * (getHeight() - i.getRadius())) - i.getYpos());
            i.setAngle(Math.PI - i.getAngle());
            i.setSpeed(i.getSpeed() * elastic);
        }
    }
    
    private boolean testCircleIntersection(PaintOval i, PaintOval j) {    
        double dx = i.getXpos() - j.getXpos();
        double dy = i.getYpos() - j.getYpos();
        

        if ( Math.hypot(dx,dy) < (i.getRadius() + j.getRadius())) {
        	
        	calculateVector(i,j,dx,dy);
            return true;
        }
        return false;
    }
    
    
    private void calculateVector (PaintOval i, PaintOval j, double dx, double dy) {
    	
    	double dir = i.getRadius() + j.getRadius()-Math.hypot(dx,dy);
        
        double tang =Math.atan2(dy,dx);
        double angle = 0.5 * Math.PI + tang;
        
        
        double totalMass = i.getMass() + j.getMass();
        
        double speedI = i.getSpeed() * ((i.getMass() - j.getMass())/totalMass);
        double speedII = (2 * j.getSpeed() * j.getMass())/totalMass;
        
        double speedJ = j.getSpeed() * ((j.getMass() - i.getMass())/ totalMass);
        double speedJJ = (2 * i.getSpeed() * i.getMass())/totalMass;
        
        i.setSpeed(speedI);
        j.setSpeed(speedJ);
       
        addVectors(i, angle,speedII);
        addVectors(j, angle + Math.PI,speedJJ);
    
        i.setSpeed(i.getSpeed() * i.getElastic());
        j.setSpeed(j.getSpeed() * j.getElastic());
        

        i.setXpos(i.getXpos() + Math.sin(angle) * dir);
        i.setYpos(i.getYpos() - Math.cos(angle) * dir);
        j.setXpos(j.getXpos() - Math.sin(angle) * dir);
        j.setYpos(j.getYpos() + Math.cos(angle) * dir);
    }
    
    
    private void addVectors(PaintOval i, double angle, double speed) {                  //  find middle vector
    	double dx = Math.sin(i.getAngle()) * i.getSpeed() + Math.sin(angle) * speed;
    	double dy =	Math.cos(i.getAngle()) * i.getSpeed() + Math.cos(angle) * speed;
    	
    	if(isGravityCheck()) {
    		i.setAngle(0.5 * Math.PI - Math.atan2(dy, dx));
    	} else { 
    		i.setAngle(0.5 * Math.PI + Math.atan2(dy, dx));
    	}
    	
    	i.setSpeed(Math.hypot(dx, dy));
    	
    }
    

    protected void paintComponent(Graphics g)
    {

        super.paintComponent(g);

        for ( int i =0; i< list.size(); i++)
        {
            list.get(i).paintOval(g);
        }
    }
    
    public static void resetCircles (){
    	list.clear();
    }
    
    
    public static boolean isGravityCheck() {
		return gravityCheck;
	}



	public static void setGravityCheck(boolean gravityCheck) {
		MyPanel.gravityCheck = gravityCheck;
	}
   
}


