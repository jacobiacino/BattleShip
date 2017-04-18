import java.util.Scanner;

import acm.breadboards.OneButtonBreadboard;
import acm.graphics.GRect;
import java.awt.Color;
import java.util.Random;

public class SquareGenerator extends OneButtonBreadboard {

	public void run() {
		this.getTextArea().setText("Click \"Draw\" to draw a square. " +
									"It will be of random size, color," +
									" and location unless you type \"color =\"" +
									" followed by \"red\", \"green\", or \"blue\"." +
									" You may instead specify the size (i.e.," +
									" side length) by typing \"size =\" followed" +
									" by some decimal value."); 
		this.getButton().setText("Draw!");
		System.out.println("hello");
		
	}
	
	public void onButtonClick() {
	    double textFieldPanelHeight = this.getRegionPanel(SquareGenerator.SOUTH).getHeight();
	    
	    int height = getHeight();
	    int width = getWidth();
	
		
		String userInput = this.getTextField().getText();
		Scanner scanner = new Scanner(userInput);
		
		if(scanner.hasNext("color")) {
			Color squareColor;
			scanner.next();
			scanner.next();
			if(scanner.hasNext("red")) {
				squareColor = Color.RED;
			} else if (scanner.hasNext("green")) {
				squareColor = Color.GREEN;
			} else {
				squareColor = Color.BLUE;
			}
			for (int i = 0; i < 50; i++) {
				double size = Math.random() * 25;
				double xCoord = Math.random() * width;
				double yCoord = Math.random() * height;
				if (yCoord - size < textFieldPanelHeight) {
					yCoord = textFieldPanelHeight + size;
				}
				if (xCoord + size > width) {
					xCoord = width - size;
				}
				GRect newSquare = new GRect(xCoord, yCoord, size, size);
				newSquare.setFilled(true);
				newSquare.setColor(squareColor);
				add(newSquare);
				pause(50);
			}
		} else if (scanner.hasNext("size")) {
			scanner.next();
			scanner.next();
			int squareSize = Integer.parseInt(scanner.next());
			for (int i = 0; i < 50; i++) {
				double xCoord = Math.random() * width;
				double yCoord = Math.random() * height;
				if (yCoord - squareSize < textFieldPanelHeight) {
					yCoord = textFieldPanelHeight + squareSize;
				}
				if (xCoord + squareSize > width) {
					xCoord = width - squareSize;
				}
				GRect newSquare = new GRect(xCoord, yCoord, squareSize, squareSize);
				newSquare.setFilled(true);
				Random random = new Random();
				newSquare.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
				add(newSquare);
				pause(50);
			}
		} else {
			for (int i = 0; i < 50; i++) {
				double newSize = Math.random() * 25;
				double xCoord = Math.random() * width;
				double yCoord = Math.random() * height;
				if (yCoord - newSize < textFieldPanelHeight) {
					yCoord = textFieldPanelHeight + newSize;
				}
				if (xCoord + newSize > width) {
					xCoord = width - newSize;
				}
				GRect newSquare = new GRect(xCoord, yCoord, newSize, newSize);
				newSquare.setFilled(true);
				Random random = new Random();
				newSquare.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
				add(newSquare);
				pause(50);
			}
		}
		scanner.close();
	}

}
