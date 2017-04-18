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
	GameState state = GameState.OPENING;
	Random random = new Random();
    GLabel gLabel;
    JTextArea textArea;
    JTextField textField;
    JButton button1;
    Timer timer;
   
    public void init(){
    	this.setSize(1330, 670);
        this.textArea = new JTextArea(4,10);
       
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
    	this.add(new Menu(10, 10));
	    this.add(new PlayerBoard(220, 10));
	    this.add(new CPUBoard(780, 10));
	    state = GameState.PLACESHIPS;
      }
  
    public void run() {
       
    }
}
