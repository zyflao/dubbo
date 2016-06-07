package Spring.Util;

import org.apache.commons.codec.binary.StringUtils;



public class zyfStringUtil {
public static void main(String[] args) {
	String s = "rwin_616342_730938";
	String acountid = s.split("_")[2];
	int a =4;
	int b =5;
/*	System.out.println(a%b);
	
	System.out.println(acountid);
	System.out.println(s.indexOf("rwin1"));*/
	String positionStr = "5";
	String size = "5";
	if (StringUtils.equals(positionStr, "-1")) {
		positionStr = "0";
		System.out.println(positionStr);
		}
	int concludescount = 0;
	if (Integer.parseInt(positionStr) % Integer.parseInt(size) != 0|| concludescount ==0) {
		System.out.println("return ---");
		//return "";
	}


	int p = Integer.parseInt(positionStr) + concludescount;
	positionStr = String.valueOf(p);
	System.out.println(positionStr);
}
}
