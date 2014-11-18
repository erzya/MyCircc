import javax.swing.JFrame;




	public class Circles {

	    public static void main(String[] args) {
	        createGui();
	    }
	    public static void createGui() {
	        JFrame myFrame = new MyJFrame("Circles Window" , 800, 600);
	        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        myFrame.setVisible(true);

	    }
	}


