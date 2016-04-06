package Spring.Util.AES;

import javax.crypto.SecretKey;


/**
 * 本类是SymEncUtil的测试类
 * @author 肖明
 */
public class SymEncUtilTest {
	
	public static void main(String[] args) throws Exception {
		SymEncUtil.Algorithm algorithm2 = SymEncUtil.Algorithm.AES;
		SecretKey key = SymEncUtil.genKeyByStr(algorithm2, "z8dr1234567nh01a");//十六字节
		
		String yuanwen = "{NOW_TIME:’20160405 17:55:00‘,MOBILE_NO:’13987654321’}";
//		String yuanwen = "{RETCODE:0,RETMSG:’成功’,DATAS:[{CHANNEL_USERID:’’,CHANNEL_SEQ:’12345’,SEC_NO:’12345’,USER_ID:’12345’,MOBILE_NO:’13987654321’,LASTCOMPLETE_STEP:’login’,MODIFIED_DATE:’20160405 12:55:00’},{CHANNEL_USERID:’’,CHANNEL_SEQ:’12346’,SEC_NO:’12346’,USER_ID:’12346’,MOBILE_NO:’13987654321’,LASTCOMPLETE_STEP:’uploadimg’,MODIFIED_DATE:’20160405 17:54:20’}]}";
		String miwen = SymEncUtil.encryptHex(algorithm2, key, yuanwen);
		String jiemi = SymEncUtil.decryptHex(algorithm2, key, miwen);
		
		System.out.println("原文=" + yuanwen);
		System.out.println("密文=" + miwen);
		System.out.println("解密=" + jiemi);
		System.out.println("原文=解密？" + yuanwen.equals(jiemi));
	}
}
