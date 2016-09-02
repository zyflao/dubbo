package Spring.AOP.intercepter;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CheckUser {

	@Pointcut("execution(* Spring.AOP.*.findUser*(..))") //切点
	public  void checkUser(){
	}
	
	@Pointcut("execution(* Spring.AOP.*.add*(..))")
	public void checkAdd(){
		System.out.println("**************<< Add User >> Checking.....***************");
	}
	
	@Before("checkUser()")
	public void beforeCheck(){
		System.out.println(">>>>>>>> before >>>>>>>>臧云峰..........");
	}
	
	@After("checkUser()")
	public void afterCheck(){
		System.out.println(">>>>>>>>　after >>>>查询用户结束..........");
	}


	@Before("checkAdd()")
	public void beforeAdd(){
		System.out.println(">>>>>>>>　增加用户--查询ing..........");
	}
	
	@After("checkAdd()")
	public void afterAdd(){
		System.out.println(">>>>>>>>　新增结束 ,没有发现异常!..........");
	}
}

