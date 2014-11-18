import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public  class MyJFrame extends JFrame {

	    String name;
	    JPanel myPanel = new MyPanel();
	    JPanel buttonsPanel = new JPanel(new FlowLayout());
	    int width;
	    int height;

	    MyJFrame (String name, int width, int height) {
	        super(name);
	        this.width = width;
	        this.height = height;
	        runFrame();
	    }
	    public void runFrame() {
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        setSize(width, height);

	        setLocation((screenSize.width - width)/2, (screenSize.height - height)/2);
	        setResizable(false);
	        myPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	        myPanel.setSize(width, height - 200);
	        
	       

	        this.add(myPanel,  BorderLayout.CENTER);
	        
	        this.add(buttonsPanel,  BorderLayout.SOUTH);
	        
	        JButton button1 = new JButton("Reset");
	        button1.setVisible(true);
	        button1.addActionListener(new ActionListener(){
	        	  public void actionPerformed(ActionEvent e) {
	        		  MyPanel.resetCircles ();
	        		
	        	  }
            });
	        
	        JButton button2 = new JButton("Gravity/ OFF");
	        button2.setVisible(true);
	        button2.addActionListener(new ActionListener(){
	        	  public void actionPerformed(ActionEvent e) {
	        	
	        		  if(MyPanel.isGravityCheck()) {
	        			  button2.setText("Gravity/ OFF");
	        			  MyPanel.setGravityCheck(false);
	        			 
	        		  } else {
	        		      button2.setText("Gravity/ ON ");
	        		      MyPanel.setGravityCheck(true);
	        		  
	        		  }
	        		
	        	  }
          });
	        
	        buttonsPanel.add(button1);
	        buttonsPanel.add(button2);
	        
	    }
	    public int getWidth() {
	        return width;
	    }
	    public int getHeight() {
	        return height;
	    }

	}