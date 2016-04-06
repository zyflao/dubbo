package Spring.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jrj.common.utils.DateUtil;


public class DateTest {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		sdf.format(new Date());
		String now = DateUtil.format(new Date(), "yyyyMMdd");
		
		//System.out.println(sdf.parse("20160315")+","+sdf.parse("20160215")+",d:"+sdf.format(new Date()));
		System.out.println(sdf.format(new Date()));
		System.out.println(new java.sql.Date(new Date().getTime()));
		System.out.println(new java.sql.Date(new Date().getTime()).toString().replace("-", ""));
		System.out.println(Integer.parseInt(DateUtil.format(new Date(), "yyyyMMdd")));
		//System.out.println(DateUtil.format(new Date(), "HHmmss"));
	}
}
