package Spring.Util;


public class Recursion {
	int sum;
	Recursion(){System.out.println(sum);}
	public int fn(int n){
		
	    sum +=n;
		
		if(n>1)return fn(n-1);
		return sum;		
		
	}
	public static void main(String[] args) {
		Recursion r = new Recursion();
		int n=10;
		System.out.println(r.fn(n));
	}

}
