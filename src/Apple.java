import java.awt.Color;

import acm.graphics.*;

public class Apple extends Fruit {

	public Apple(int x, int y) {
		GImage apple = new GImage("apple.png", x, y);
		this.add(apple);
	}
}
