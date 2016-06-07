
/**
 * 本类是封装了一些关于字节和字节数组的方法
 * @author 肖明
 */
public class BytesUtil {
	private static final char[] HEX_CHARS = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

	public static String bytes2hexStr(byte[] bytes) {
		int len = bytes.length;
		if (len==0) {
			return null;
		}
		char[] cbuf = new char[len*2];
		for (int i=0; i<len; i++) {
			int x = i*2;
			cbuf[x] 	= HEX_CHARS[(bytes[i] >>> 4) & 0xf];
			cbuf[x+1]	= HEX_CHARS[bytes[i] & 0xf];
		}
		return new String(cbuf);
	}
	
	public static byte[] hexStr2bytes(String hexStr) {
		if(hexStr==null || "".equals(hexStr.trim())) {
			return null;
		}
		if(hexStr.length()%2 != 0) {//长度为单数
			hexStr = "0" + hexStr;//前面补0
		}
		char[] chars = hexStr.toCharArray();
		int len = chars.length/2;
		byte[] bytes = new byte[len];
		for (int i = 0; i < len; i++) {
			int x = i*2;
			bytes[i] = (byte)Integer.parseInt(String.valueOf(new char[]{chars[x], chars[x+1]}), 16);
		}
		return bytes;
	}
}
