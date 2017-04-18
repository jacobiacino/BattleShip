import java.awt.Color;

import acm.graphics.*;

public class Cherries extends Fruit {

	public Cherries(int x, int y) {
		GOval cherry1 = new GOval(x, y, 25, 25);
		cherry1.setFillColor(Color.RED);
		cherry1.setFilled(true);
		this.add(cherry1);
		GOval cherry2 = new GOval(x + 25, y, 25, 25);
		cherry2.setFillColor(Color.RED);
		cherry2.setFilled(true);
		this.add(cherry2);
		GArc arc1 = new GArc(x + 10, y - 20, 35, 70, 65, 90);
		arc1.setColor(Color.GREEN);
		this.add(arc1);
		GArc arc2 = new GArc(x + 30, y - 20, 25, 25, 125, 85);
		arc2.setColor(Color.GREEN);
		this.add(arc2);
	}
}
