import acm.breadboards.NoButtonsBreadboard;
import javax.swing.Timer;

public class FruitDrop extends NoButtonsBreadboard{

	private int timeCount = 0;
	
	public void run() {
		this.getTextArea().setText("It's raining Fruit!");
		getTimer().setDelay(100);
		getTimer().start();
	}
	
	@Override
	public void onTimerTick() {
		if (timeCount % 5 == 0) {
			spawnRandomFruit();
		}
		for (int i = 0; i < getElementCount(); i++) {
			if (getElement(i) instanceof Fruit) {
				((Fruit) getElement(i)).fall();
			}
		}
		timeCount++;
	}
	
	public void spawnRandomFruit() {
		int rand = (int)(Math.random()*3);
		int randX =  (int)(Math.random()*getWidth());
		if (rand == 0) {
			this.add(new Orange(randX, 0));
		} else if (rand == 1) {
			this.add(new Cherries(randX, 0));
		} else {
			this.add(new Apple(randX, 0));
		}
	}

}
