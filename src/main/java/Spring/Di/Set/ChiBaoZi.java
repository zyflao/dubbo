package Spring.Di.Set;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import Spring.Di.PerformanceException;
import Spring.Di.Performer;
import Spring.Di.Xishou;

public class ChiBaoZi implements Performer {
	 
	/*before拿到map集合的*/
	private Map<String,BeforeChi> before;
	public void setBefore(Map<String, BeforeChi> before) {
		this.before = before;
	}
	private String n ;
    public void setN(String n) {
		this.n = n;
	}
	private String xian;
	public void setXian(String xian) {
		this.xian = xian;
	}
	private Xishou xishou;
	public void setXishou(Xishou xishou) {
		this.xishou = xishou;
	}
	private MamaSay mamaSay;
	public void setMamaSay(MamaSay mamaSay) {
		this.mamaSay = mamaSay;
	}

	public void perform() throws PerformanceException {
	/*	遍历拿到的map 集合before
		for(String key:before.keySet()){
			System.out.println(key+":");
			// BeforeChi s = before.get(key);
			 before.get(key).todo();
		}*/
		//mamaSay.todo();
		//xishou.todo();
		System.out.println("我吃"+n+xian+"大包子");
		//System.out.println(m);
	}

}
