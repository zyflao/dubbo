package Spring.Di;

import org.springframework.stereotype.Component;

import Spring.Di.Set.BeforeChi;
@Component
public class Xishou implements BeforeChi{
	Xishou(){
		System.out.println("构造方法>>>>妈妈说吃饭洗手");
	}
	public void todo(){
		System.out.println("不得不去洗手");
	}
}
