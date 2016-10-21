package Spring.Util;

/**
 * @desc Integer java1.5 有缓存策略，-128~127，使用相同的对象和引用实现缓存重用，节约空间
 * @author zangyunfeng
 * 
 */
public class IntegerTest {
	public static void main(String[] args) {

		Integer i1 = 3;
		Integer i2 = 3;
		Integer i3 = 300;
		Integer i4 = 300;
		if (i1 == i2)
			System.out.println("i1=i2");
		if (i3 == i4) {
			System.out.println("i3=i4");
		} else {
			System.out.println("i3!=i4");
		}
	}
}
