package Spring.Util.AES;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil {
	private static String Algorithm = "AES";// 加密算法的名称

	/***
	 * content ：为需加密的内容 password ：为加密秘钥 ，目前暂定为CRH
	 */
	public static byte[] encrypt(byte[] content, byte[] password) {
		try {
			SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(password, Algorithm);
			Cipher c = Cipher.getInstance(Algorithm);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] cipherByte = c.doFinal(content);
			return cipherByte;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static byte[] decrypt(byte[] content, byte[] password) {
		try {
			SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(password, Algorithm);
			Cipher c = Cipher.getInstance(Algorithm);
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] cipherByte = c.doFinal(content);
			return cipherByte;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static void main(String[] args){
		try {
			String key = "cairenhui0002015";
			String name = "张三";
			
			//加密过程
			byte[] dbytes = encrypt(name.getBytes("UTF-8"), /*dBase64(key)*/key.getBytes());
			String jiami = new BASE64Encoder().encode(dbytes);
			System.out.println("加："+jiami);
			
			//解密过程
			byte[] destr = new BASE64Decoder().decodeBuffer("ia6SZ3mTyc1Yl4Hnm3Wqjg==");
			byte[] str = decrypt(destr, key.getBytes());
			System.out.println("解："+new String(str, "UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
