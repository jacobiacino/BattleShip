import acm.graphics.GOval;

import java.awt.Color;
import java.awt.color.*;

public class Orange extends Fruit {

	public Orange(int x, int y) {
		GOval orange = new GOval(x, y, 50, 50);
		orange.setFillColor(Color.ORANGE);
		orange.setFilled(true);
		this.add(orange);
		
	}
	
}
