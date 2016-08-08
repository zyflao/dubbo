package Spring.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.StringUtils;



public class ZyfStringUtil {
	private  String mobile;
	private  String idcard;
	
	
public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getIdcard() {
		return idcard;
	}


	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}


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
	
	String regex="^[a-zA-Z0-9]{6}$";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher("123");
	System.out.println(matcher.matches());
	
	ZyfStringUtil zyf =new ZyfStringUtil(); 
	zyf.setIdcard(null);
	zyf.getIdcard();
	System.out.println(zyf.getIdcard());
	
}
}
