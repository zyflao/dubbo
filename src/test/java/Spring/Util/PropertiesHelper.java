package Spring.Util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 读取properties
 */
public class PropertiesHelper {
	private static final String CONFIG = "test-config";
	private static final ResourceBundle RESOURCE_BUNDLE =ResourceBundle.getBundle(CONFIG);
	/**
	 * @param key 
	 * @return value
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			throw new RuntimeException( "! config : "+ key + '!');
		}
	} 
	public static void main(String[] args) {
		String s=RESOURCE_BUNDLE.getString("win.newsinfo.default.count");
		System.out.println(s);
	}
}
