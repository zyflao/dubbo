package Spring.Util.AES;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 本类结合base64和hex编码实现多种对称加密算法。
 * 类名解释：对称加密(symmetric encryption)
 * @author 肖明
 */
public class SymEncUtil {
	public static enum Algorithm {
		DES("DES","DES","key长度：8byte"), 
		DESEDE("DESede","DESede","key长度：16-24byte"), 
		DESEDE2("DESede","DESede/ECB/PKCS5Padding","key长度：16-24byte"), 
		DESEDE3("DESede","DESede/ECB/PKCS7Padding","key长度：16-24byte"), 
		AES("AES","AES","key长度：16byte"), 
		BLOWFISH("Blowfish","Blowfish","key长度：1-16byte"), 
		RIJNDAEL("Rijndael","Rijndael","key长度：16byte"),
		RIJNDAEL2("Rijndael","Rijndael/ECB/PKCS5Padding","key长度：16byte"),
		RIJNDAEL3("Rijndael","Rijndael/ECB/PKCS7Padding","key长度：16byte");
		private Algorithm(String keyAlgorithm, String transformation, String description) {
			this.keyAlgorithm = keyAlgorithm;
			this.transformation = transformation;
			this.description = description;
		}
		private String keyAlgorithm;
		private String transformation;
		private String description;//描述
		public String getKeyAlgorithm() {
			return this.keyAlgorithm;
		}
		public String getTransformation() {
			return this.transformation;
		}
		public String getDescription() {
			return this.description;
		}
	}
	private static final String CHARSET = "UTF-8";//编码
	
	/**
	 * 随机产生密钥
	 * @return 密钥
	 */
	public static SecretKey genRandomKey(Algorithm algorithm) {
		KeyGenerator keygen = null;
		try {
			keygen = KeyGenerator.getInstance(algorithm.getKeyAlgorithm());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);// 加密算法名是本工具类提供的，如果错了业务没有办法处理。所以这个异常不需要外部捕获。
		}
		return keygen.generateKey();
	}
	/**
	 * 根据指定字符串产生密钥
	 * @param algorithm 加解密算法
	 * @param keyStr 密钥字符串
	 * @return 密钥
	 */
	public static SecretKey genKeyByStr(Algorithm algorithm, String keyStr) {
		try {
			byte[] bkey = keyStr.getBytes(CHARSET);
			return genKeyByBytes(algorithm, bkey);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);//编码字符集是本工具类提供的，如果错了业务没有办法处理。所以这个异常不需要外部捕获。
		}
	}
	/**
	 * 根据指定字节数组产生密钥
	 * @param algorithm 加解密算法
	 * @param keyBytes 密钥字节数组
	 * @return 密钥
	 */
	public static SecretKey genKeyByBytes(Algorithm algorithm, byte[] keyBytes) {
		return new SecretKeySpec(keyBytes, algorithm.getKeyAlgorithm());
	}
	
	/**
	 * 得到密钥的base64字符串
	 * @param key 密钥
	 * @return base64字符串
	 */
	public static String getKeyStr(SecretKey key) {
		return Base64.encodeBase64String(key.getEncoded());
	}
	
	/**
	 * 从base64字符串中读取密钥
	 * @param algorithm 加解密算法
	 * @param key base64字符串
	 * @return 密钥
	 */
	public static SecretKey readKeyFromStr(Algorithm algorithm, String key) {
		return new SecretKeySpec(Base64.decodeBase64(key), algorithm.getKeyAlgorithm());
	}
	
	/**
	 * 加解密字节数组
	 * @param algorithm 加解密算法
	 * @param opmode 操作：1加密，2解密
	 * @param key 密钥
	 * @param data 数据
	 * @throws InvalidKeyException 密钥错误
	 * @throws BadPaddingException 解密密文错误(加密模式没有)
	 */
	private static byte[] cipherDoFinal(Algorithm algorithm, int opmode, SecretKey key, byte[] data) throws InvalidKeyException, BadPaddingException {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(algorithm.getTransformation());
		} catch (Exception e) {
			//NoSuchAlgorithmException：加密算法名是本工具类提供的，如果错了业务没有办法处理。所以这个异常不需要外部捕获。
			//NoSuchProviderException：Provider是本工具类提供的，如果错了业务没有办法处理。所以这个异常不需要外部捕获。
			//NoSuchPaddingException：没有特定的填充机制，与环境有关，业务没有办法处理。所以这个异常不需要外部捕获。
			throw new RuntimeException(e);
		}
		cipher.init(opmode, key);
		try {
			return cipher.doFinal(data);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);//业务不需要将数据分块(好像由底层处理了)，如果错了业务没有办法处理。所以这个异常不需要外部捕获。
		}
	}
	/**
	 * 加密字节数组
	 * @param algorithm 加解密算法
	 * @param key 密钥
	 * @param data 明文
	 * @return 密文
	 * @throws InvalidKeyException 密钥错误
	 */
	public static byte[] encrypt(Algorithm algorithm, SecretKey key, byte[] data) throws InvalidKeyException {
		try {
			return cipherDoFinal(algorithm, Cipher.ENCRYPT_MODE, key, data);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);//明文没有具体格式要求，不会出错。所以这个异常不需要外部捕获。
		}
	}
	/**
	 * 加密字符串，并进行base64编码
	 * @param algorithm 加解密算法
	 * @param key 密钥
	 * @param data 明文
	 * @return 密文
	 * @throws InvalidKeyException 密钥错误
	 */
	public static String encryptBase64(Algorithm algorithm, SecretKey key, String data) throws InvalidKeyException {
		try {
			return Base64.encodeBase64String(encrypt(algorithm, key, data.getBytes(CHARSET)));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);//编码字符集是本工具类提供的，如果错了业务没有办法处理。所以这个异常不需要外部捕获。
		}
	}
	/**
	 * 加密字符串，并进行Hex编码
	 * @param algorithm 加解密算法
	 * @param key 密钥
	 * @param data 明文
	 * @return 密文
	 * @throws InvalidKeyException 密钥错误
	 */
	public static String encryptHex(Algorithm algorithm, SecretKey key, String data) throws InvalidKeyException {
		try {
			return BytesUtil.bytes2hexStr(encrypt(algorithm, key, data.getBytes(CHARSET)));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);//编码字符集是本工具类提供的，如果错了业务没有办法处理。所以这个异常不需要外部捕获。
		}
	}
	
	/**
	 * 解密字节数组
	 * @param algorithm 加解密算法
	 * @param key 密钥
	 * @param data 密文
	 * @return 明文
	 * @throws InvalidKeyException 密钥错误
	 * @throws BadPaddingException 密文错误
	 */
	public static byte[] decrypt(Algorithm algorithm, SecretKey key, byte[] data) throws InvalidKeyException, BadPaddingException {
		return cipherDoFinal(algorithm, Cipher.DECRYPT_MODE, key, data);
	}
	/**
	 * 对字符串先进行base64解码，再解密
	 * @param algorithm 加解密算法
	 * @param data 密文
	 * @param key 密钥
	 * @return 明文
	 * @throws InvalidKeyException 密钥错误
	 * @throws BadPaddingException 密文错误
	 */
	public static String decryptBase64(Algorithm algorithm, SecretKey key, String data) throws InvalidKeyException, BadPaddingException {
		try {
			return new String(decrypt(algorithm, key, Base64.decodeBase64(data)), CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);//编码字符集是本工具类提供的，如果错了业务没有办法处理。所以这个异常不需要外部捕获。
		}
	}
	/**
	 * 对字符串先进行Hex解码，再解密
	 * @param algorithm 加解密算法
	 * @param data 密文
	 * @param key 密钥
	 * @return 明文
	 * @throws InvalidKeyException 密钥错误
	 * @throws BadPaddingException 密文错误
	 */
	public static String decryptHex(Algorithm algorithm, SecretKey key, String data) throws InvalidKeyException, BadPaddingException {
		try {
			return new String(decrypt(algorithm, key, BytesUtil.hexStr2bytes(data)), CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);//编码字符集是本工具类提供的，如果错了业务没有办法处理。所以这个异常不需要外部捕获。
		}
	}
}
