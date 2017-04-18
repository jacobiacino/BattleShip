package battleship;

import acm.graphics.*;
import java.awt.Color;

public class Menu extends GCompound{

	public Menu(int x, int y) {
		GRect background = new GRect(x, y, 200, 540);
		background.setFilled(true);
		background.setFillColor(Color.GRAY);
		this.add(background);
		
	}
}
