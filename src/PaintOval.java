import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class PaintOval {
	
	    private final double DENSITY = 0.96;

	    Random random = new Random();
	  
	    private int randomSize = random.nextInt(80) + 20;
	    private int radius = Math.round(randomSize / 2);

	    private double xPos = 300;
	    private double yPos = 200;
	    
	    private double angle = random.nextDouble() *  (Math.PI * 2);
	    private double mass = Math.PI * radius * DENSITY;
	    private double speed = (random.nextDouble() *2.0 + 0.3) ;
	    private double  elastic = 0.8;
	

	    PaintOval(double xPos, double yPos)
	    {
	        this.xPos = xPos;
	        this.yPos = yPos;
	    }

	    public double getMass() {
			return mass;
		}

		public double getXpos()
	    {

	        return xPos;
	    }

	    public double getYpos()
	    {
	        return yPos;
	    }

	    public void setXpos(double xPos)
	    {
	        this.xPos = xPos;
	    }

	    public void setYpos(double yPos)
	    {
	        this.yPos = yPos;
	    }

	    public int getRadius()
	    {
	        return radius;
	    }

	    public void paintOval(Graphics g)
	    {
	    	g.setColor(Color.GRAY);
	        g.fillOval((int) Math.round(xPos - radius), (int) Math.round(yPos - radius), radius * 2, radius * 2);
	    }

	    public double getSpeed()
	    {
	        return speed;
	    }

	    public void setSpeed(double speed)
	    {
	        this.speed = speed;
	    }

	    public double getAngle()
	    {
	        return angle;
	    }

	    public void setAngle(double angle)
	    {
	        this.angle = angle;
	    }
	    
	    public double getElastic() {
			return elastic;
		}
}
