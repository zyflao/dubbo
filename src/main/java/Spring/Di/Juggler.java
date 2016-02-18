package Spring.Di;



public class Juggler implements Performer {

	private int beanBags;

	private int d=10;

	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}

	public void setBeanBags(int beanBags) {
		this.beanBags = beanBags;
	}

	
	private Xishou xishou;
	

	protected Juggler() {
		
	}

	protected Juggler(int beanBags,Xishou xishou) {
		this.beanBags = beanBags;
		this.xishou = xishou;
		
	}

	public void perform() throws PerformanceException {
		xishou.todo();
		System.out.println("我有"+beanBags +"个大肉包");
	}
	
}
