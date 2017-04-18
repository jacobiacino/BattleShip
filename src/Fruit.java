import acm.graphics.GCompound;

public abstract class Fruit extends GCompound{
	private int fallRate = 1;
	
	public void fall() {
		move(0, fallRate);
		fallRate++;
	}
}
