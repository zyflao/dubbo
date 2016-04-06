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
 * ������base64��hex����ʵ�ֶ��ֶԳƼ����㷨��
 * �������ͣ��ԳƼ���(symmetric encryption)
 * @author Ф��
 */
public class SymEncUtil {
	public static enum Algorithm {
		DES("DES","DES","key���ȣ�8byte"), 
		DESEDE("DESede","DESede","key���ȣ�16-24byte"), 
		DESEDE2("DESede","DESede/ECB/PKCS5Padding","key���ȣ�16-24byte"), 
		DESEDE3("DESede","DESede/ECB/PKCS7Padding","key���ȣ�16-24byte"), 
		AES("AES","AES","key���ȣ�16byte"), 
		BLOWFISH("Blowfish","Blowfish","key���ȣ�1-16byte"), 
		RIJNDAEL("Rijndael","Rijndael","key���ȣ�16byte"),
		RIJNDAEL2("Rijndael","Rijndael/ECB/PKCS5Padding","key���ȣ�16byte"),
		RIJNDAEL3("Rijndael","Rijndael/ECB/PKCS7Padding","key���ȣ�16byte");
		private Algorithm(String keyAlgorithm, String transformation, String description) {
			this.keyAlgorithm = keyAlgorithm;
			this.transformation = transformation;
			this.description = description;
		}
		private String keyAlgorithm;
		private String transformation;
		private String description;//����
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
	private static final String CHARSET = "UTF-8";//����
	
	/**
	 * ���������Կ
	 * @return ��Կ
	 */
	public static SecretKey genRandomKey(Algorithm algorithm) {
		KeyGenerator keygen = null;
		try {
			keygen = KeyGenerator.getInstance(algorithm.getKeyAlgorithm());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);// �����㷨���Ǳ��������ṩ�ģ��������ҵ��û�а취������������쳣����Ҫ�ⲿ����
		}
		return keygen.generateKey();
	}
	/**
	 * ����ָ���ַ���������Կ
	 * @param algorithm �ӽ����㷨
	 * @param keyStr ��Կ�ַ���
	 * @return ��Կ
	 */
	public static SecretKey genKeyByStr(Algorithm algorithm, String keyStr) {
		try {
			byte[] bkey = keyStr.getBytes(CHARSET);
			return genKeyByBytes(algorithm, bkey);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);//�����ַ����Ǳ��������ṩ�ģ��������ҵ��û�а취������������쳣����Ҫ�ⲿ����
		}
	}
	/**
	 * ����ָ���ֽ����������Կ
	 * @param algorithm �ӽ����㷨
	 * @param keyBytes ��Կ�ֽ�����
	 * @return ��Կ
	 */
	public static SecretKey genKeyByBytes(Algorithm algorithm, byte[] keyBytes) {
		return new SecretKeySpec(keyBytes, algorithm.getKeyAlgorithm());
	}
	
	/**
	 * �õ���Կ��base64�ַ���
	 * @param key ��Կ
	 * @return base64�ַ���
	 */
	public static String getKeyStr(SecretKey key) {
		return Base64.encodeBase64String(key.getEncoded());
	}
	
	/**
	 * ��base64�ַ����ж�ȡ��Կ
	 * @param algorithm �ӽ����㷨
	 * @param key base64�ַ���
	 * @return ��Կ
	 */
	public static SecretKey readKeyFromStr(Algorithm algorithm, String key) {
		return new SecretKeySpec(Base64.decodeBase64(key), algorithm.getKeyAlgorithm());
	}
	
	/**
	 * �ӽ����ֽ�����
	 * @param algorithm �ӽ����㷨
	 * @param opmode ������1���ܣ�2����
	 * @param key ��Կ
	 * @param data ����
	 * @throws InvalidKeyException ��Կ����
	 * @throws BadPaddingException �������Ĵ���(����ģʽû��)
	 */
	private static byte[] cipherDoFinal(Algorithm algorithm, int opmode, SecretKey key, byte[] data) throws InvalidKeyException, BadPaddingException {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(algorithm.getTransformation());
		} catch (Exception e) {
			//NoSuchAlgorithmException�������㷨���Ǳ��������ṩ�ģ��������ҵ��û�а취������������쳣����Ҫ�ⲿ����
			//NoSuchProviderException��Provider�Ǳ��������ṩ�ģ��������ҵ��û�а취������������쳣����Ҫ�ⲿ����
			//NoSuchPaddingException��û���ض��������ƣ��뻷���йأ�ҵ��û�а취������������쳣����Ҫ�ⲿ����
			throw new RuntimeException(e);
		}
		cipher.init(opmode, key);
		try {
			return cipher.doFinal(data);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);//ҵ����Ҫ�����ݷֿ�(�����ɵײ㴦����)���������ҵ��û�а취������������쳣����Ҫ�ⲿ����
		}
	}
	/**
	 * �����ֽ�����
	 * @param algorithm �ӽ����㷨
	 * @param key ��Կ
	 * @param data ����
	 * @return ����
	 * @throws InvalidKeyException ��Կ����
	 */
	public static byte[] encrypt(Algorithm algorithm, SecretKey key, byte[] data) throws InvalidKeyException {
		try {
			return cipherDoFinal(algorithm, Cipher.ENCRYPT_MODE, key, data);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);//����û�о����ʽҪ�󣬲��������������쳣����Ҫ�ⲿ����
		}
	}
	/**
	 * �����ַ�����������base64����
	 * @param algorithm �ӽ����㷨
	 * @param key ��Կ
	 * @param data ����
	 * @return ����
	 * @throws InvalidKeyException ��Կ����
	 */
	public static String encryptBase64(Algorithm algorithm, SecretKey key, String data) throws InvalidKeyException {
		try {
			return Base64.encodeBase64String(encrypt(algorithm, key, data.getBytes(CHARSET)));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);//�����ַ����Ǳ��������ṩ�ģ��������ҵ��û�а취������������쳣����Ҫ�ⲿ����
		}
	}
	/**
	 * �����ַ�����������Hex����
	 * @param algorithm �ӽ����㷨
	 * @param key ��Կ
	 * @param data ����
	 * @return ����
	 * @throws InvalidKeyException ��Կ����
	 */
	public static String encryptHex(Algorithm algorithm, SecretKey key, String data) throws InvalidKeyException {
		try {
			return BytesUtil.bytes2hexStr(encrypt(algorithm, key, data.getBytes(CHARSET)));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);//�����ַ����Ǳ��������ṩ�ģ��������ҵ��û�а취������������쳣����Ҫ�ⲿ����
		}
	}
	
	/**
	 * �����ֽ�����
	 * @param algorithm �ӽ����㷨
	 * @param key ��Կ
	 * @param data ����
	 * @return ����
	 * @throws InvalidKeyException ��Կ����
	 * @throws BadPaddingException ���Ĵ���
	 */
	public static byte[] decrypt(Algorithm algorithm, SecretKey key, byte[] data) throws InvalidKeyException, BadPaddingException {
		return cipherDoFinal(algorithm, Cipher.DECRYPT_MODE, key, data);
	}
	/**
	 * ���ַ����Ƚ���base64���룬�ٽ���
	 * @param algorithm �ӽ����㷨
	 * @param data ����
	 * @param key ��Կ
	 * @return ����
	 * @throws InvalidKeyException ��Կ����
	 * @throws BadPaddingException ���Ĵ���
	 */
	public static String decryptBase64(Algorithm algorithm, SecretKey key, String data) throws InvalidKeyException, BadPaddingException {
		try {
			return new String(decrypt(algorithm, key, Base64.decodeBase64(data)), CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);//�����ַ����Ǳ��������ṩ�ģ��������ҵ��û�а취������������쳣����Ҫ�ⲿ����
		}
	}
	/**
	 * ���ַ����Ƚ���Hex���룬�ٽ���
	 * @param algorithm �ӽ����㷨
	 * @param data ����
	 * @param key ��Կ
	 * @return ����
	 * @throws InvalidKeyException ��Կ����
	 * @throws BadPaddingException ���Ĵ���
	 */
	public static String decryptHex(Algorithm algorithm, SecretKey key, String data) throws InvalidKeyException, BadPaddingException {
		try {
			return new String(decrypt(algorithm, key, BytesUtil.hexStr2bytes(data)), CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);//�����ַ����Ǳ��������ṩ�ģ��������ҵ��û�а취������������쳣����Ҫ�ⲿ����
		}
	}
}
