package Spring.AOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Spring.AOP.api.UserApi;

public class UserImplTest {
public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath*:test-spring-app.xml");
	UserApi j = (UserApi) context.getBean("userImpl");
	j.findUser("zyf");
}
}
