package junit.framework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath*:test-spring-app.xml");
	
	
	}
}
