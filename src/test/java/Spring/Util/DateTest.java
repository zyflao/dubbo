package Spring.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jrj.common.utils.DateUtil;


class A {
	String a;
	String b;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "a [a=" + a + ", b=" + b + "]";
	}

}

public class DateTest {
	@Override
	public String toString() {
		return "DateTest [getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(new Date()));
		
		String now = DateUtil.format(new java.util.Date(1473987631000L),
				"yyyyMMdd HHmmss");
		String now1 = DateUtil.format(new java.util.Date(1464671858123L),
				"yyyyMMdd HHmmss");
		String now2 = DateUtil.format(new java.util.Date(1464671836220L),
				"yyyyMMdd HHmmss");
		String now3 = DateUtil.format(new java.util.Date(1464671523953L),
				"yyyyMMdd HHmmss");

		Calendar calendar = Calendar.getInstance();
		String today = new SimpleDateFormat("yyyyMMdd ").format(calendar
				.getTime());
		calendar.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd ").format(calendar
				.getTime());
		calendar.add(Calendar.YEAR, -1);
		String startday = new SimpleDateFormat("yyyyMMdd ").format(calendar
				.getTime());
		// System.out.println(sdf.parse("20160315")+","+sdf.parse("20160215")+",d:"+sdf.format(new
		// Date()));
		// System.out.println(sdf.format(new Date()));
		 System.out.println("now:"+now);
		// System.out.println(now1);
		// System.out.println(now2);
		// System.out.println(now3);
		// System.out.println(yesterday);
		// System.out.println(startday);
		// System.out.println(today);
		A a = new A();
		a.setA("1");
		a.setB("2S");

		//System.out.println(a);
	List l = new ArrayList<String>();
		l.add("1");
		l.add("2");
		l.add("3");
System.out.println(l.subList(0, 1));

		/*
		 * System.out.println(new java.sql.Date(new
		 * Date().getTime()).toString().replace("-", ""));
		 * System.out.println(DateUtil.format(new Date(),
		 * "yyyy-MM-dd HH:MM:ss").getClass());
		 */
		// System.out.println(DateUtil.format(new Date(), "HHmmss"));
	}
}
