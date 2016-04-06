package Spring.Util;

import java.util.ArrayList;
import java.util.List;

public class ReadFile {
		
		private List<String> getMobiles(){
			List<String> mobileList = new ArrayList<String>();
//			 获取文件路径
			String path = getClass().getClassLoader().getResource("mobiles.txt").getPath();
//			 读取文件内容
		/*	File file = FileUtils.getFile(path);
			String mobiles = FileUtils.getContent(file);
			mobileList = Arrays.asList(mobiles.split(","));*/
			return mobileList;
		 }
  
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
