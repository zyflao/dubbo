package Spring.Util;


public class zyfStringUtil {
public static void main(String[] args) {
	String s = "rwin_616342_730938";
	String acountid = s.split("_")[2];
	System.out.println(acountid);
	System.out.println(s.indexOf("rwin1"));
	
}
}
