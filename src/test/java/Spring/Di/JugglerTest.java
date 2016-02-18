package Spring.Di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Spring.Di.Set.ChiBaoZi;

public class JugglerTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:test-spring-app.xml");
		Juggler j = (Juggler) context.getBean("juggler");
		ChiBaoZi c = (ChiBaoZi) context.getBean("chi");
		try {
			//j.perform();
			c.perform();
		} catch (PerformanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
}
