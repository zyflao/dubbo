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
		System.out.println(">>>>>>>> 准备搜查用户..........");
	}
	
	@After("checkUser()")
	public void afterCheck(){
		System.out.println(">>>>>>>>　搜查用户完毕..........");
	}

	@Before("checkAdd()")
	public void beforeAdd(){
		System.out.println(">>>>>>>>　增加用户--检查ing..........");
	}
	
	@After("checkAdd()")
	public void afterAdd(){
		System.out.println(">>>>>>>>　增加用户--检查完毕！未发现异常!..........");
	}
}

