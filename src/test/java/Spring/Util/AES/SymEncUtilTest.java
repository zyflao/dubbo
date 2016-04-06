package Spring.Util.AES;

import javax.crypto.SecretKey;


/**
 * ������SymEncUtil�Ĳ�����
 * @author Ф��
 */
public class SymEncUtilTest {
	
	public static void main(String[] args) throws Exception {
		SymEncUtil.Algorithm algorithm2 = SymEncUtil.Algorithm.AES;
		SecretKey key = SymEncUtil.genKeyByStr(algorithm2, "z8dr1234567nh01a");//ʮ���ֽ�
		
		String yuanwen = "{NOW_TIME:��20160405 17:55:00��,MOBILE_NO:��13987654321��}";
//		String yuanwen = "{RETCODE:0,RETMSG:���ɹ���,DATAS:[{CHANNEL_USERID:����,CHANNEL_SEQ:��12345��,SEC_NO:��12345��,USER_ID:��12345��,MOBILE_NO:��13987654321��,LASTCOMPLETE_STEP:��login��,MODIFIED_DATE:��20160405 12:55:00��},{CHANNEL_USERID:����,CHANNEL_SEQ:��12346��,SEC_NO:��12346��,USER_ID:��12346��,MOBILE_NO:��13987654321��,LASTCOMPLETE_STEP:��uploadimg��,MODIFIED_DATE:��20160405 17:54:20��}]}";
		String miwen = SymEncUtil.encryptHex(algorithm2, key, yuanwen);
		String jiemi = SymEncUtil.decryptHex(algorithm2, key, miwen);
		
		System.out.println("ԭ��=" + yuanwen);
		System.out.println("����=" + miwen);
		System.out.println("����=" + jiemi);
		System.out.println("ԭ��=���ܣ�" + yuanwen.equals(jiemi));
	}
}
