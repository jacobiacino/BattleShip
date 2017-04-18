package battleship;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;


public class BattleShip extends GraphicsProgram {
Random random = new Random();
    JLabel label;
    GLabel gLabel;
    JTextArea textArea;
    JTextField textField;
    JButton button1;
    JButton button2;
    JButton button3;
    Timer timer;
   
    public void init(){
    this.setSize(500,500);
        this.gLabel = new GLabel("BattleShip");
        gLabel.setFont("Arial-BOLD-45"); 
       
        this.add(gLabel,110 ,200);
        this.setBackground(Color.BLUE);
        this.textArea = new JTextArea(4,10);
        this.textArea.setText("Follow the instructions carefully to play! Hit 'GO' to Start!");
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        this.add(textArea, NORTH);
       
        this.button1 = new JButton();
        this.button1.setText("GO");
        this.add(button1, SOUTH);
        this.button1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	action(e);
			}
	    });
	}
   
    public void action(ActionEvent e) {
	    int randomX = random.nextInt(8);
	    int randomY = random.nextInt(8);
	    int randomLength = random.nextInt(8);
	    this.setBackground(Color.WHITE);
	    this.remove(gLabel);
	    this.remove(textArea);
	    this.remove(button1);
	    int rows = 9;
	    int colums = 9;
	    GRect[][] grect = new GRect[rows][colums];
	    int countX = 0;
	    int countY = 0;
	      for(int i=0; i<rows; i++){
	          for (int j=0; j<colums; j++){
	   
	              grect[i][j] = new GRect(countX*38, countY*38, 76 ,76 );  
	   
	              if(j == randomX){
	            	  grect[i][j].setFillColor(Color.BLUE);
	              }
	              add(grect[i][j]); 
	              countX++;
	              if(countX == 9){
		              countX = 0;
		              countY++;
	              }    
	          }
	      }
      }
  
    public void run() {
       
    }
}
