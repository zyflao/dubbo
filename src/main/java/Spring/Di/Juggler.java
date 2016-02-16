package Spring.Di;

public class Juggler implements Performer {
	private int beanBags=3;
	protected Juggler() {
	
	}
	protected Juggler(int beanBags) {
		this.beanBags = beanBags;
	}

	public void perform() throws PerformanceException {
		System.out.println("我有"+beanBags +"个大肉包");
	}
	
}
