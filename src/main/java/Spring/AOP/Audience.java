package Spring.AOP;

public class Audience {
	
	//表演之前
	public void takeSeats(){
		System.out.println("The audience is taking their seats");
	}
	//表演之前
	public void turnOffcellPhones(){
		System.out.println("The audience is turning off their cellphones");
	}
	//表演之后
	public void applaud(){
		System.out.println("CLAP");
	}
	//表演失败之后
	public void demandRefund(){ 
		System.out.println("Boo ! We want our money back!");
	}
	
}
