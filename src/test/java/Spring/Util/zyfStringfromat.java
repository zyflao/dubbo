package Spring.Util;

import java.util.ArrayList;
import java.util.List;

public class zyfStringfromat {
	public static String PARAM_TEL = "&sid=%d&t=%d&tels=%s";
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		String str =null;
		str=String.format(PARAM_TEL, 123,System.currentTimeMillis(),""); 
		System.out.println(str);
		System.out.println(Integer.MAX_VALUE);
	}
}
