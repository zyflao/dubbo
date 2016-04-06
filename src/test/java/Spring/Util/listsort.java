package Spring.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 * @className:listsort.java
 * @classDescription:  list 指定属性排序
 * @author： Administrator
 * @dateTime:2016年2月24日下午5:32:04
 */
public class listsort {
	public static void main(String[] args) {
		  
		  Student stu1 = new Student (1,"zhangsan","male",28,"cs");
		  Student stu2 = new Student (2,"lisi","female",19,"cs");
		  Student stu3 = new Student (3,"wangwu","male",22,"cs");
		  Student stu4 = new Student (4,"zhaoliu","female",17,"cs");
		  Student stu5 = new Student (5,"jiaoming","male",22,"cs");

		  ArrayList List = new ArrayList();
		  List.add(stu1);
		  List.add(stu2);
		  List.add(stu3);
		  List.add(stu4);
		  List.add(stu5); 
		  for(int j=0;j<List.size();j++){  
		        Student st=(Student)List.get(j);  
		        System.out.println("st.age="+st.getAge());
		    }  
		  //这里就会自动根据规则进行排序
		   Collections.sort(List, new Comparator(){  
		        public int compare(Object o1, Object o2) {  
		            Student stu1=(Student)o1;  
		            Student stu2=(Student)o2;  
		            if(stu1.getAge()>stu2.getAge()){  
		                return -1;  
		            }else if(stu1.getAge()==stu2.getAge()){  
		                return 0;  
		            }else{  
		                return 1;  
		            }  
		        }             
		    });  
		   
		   
		    System.out.println("/////////////排序之后///////////////");  
		    for(int i=0;i<List.size();i++){  
		        Student st=(Student)List.get(i);  
		        System.out.println("st.age="+st.getAge());
		    }  
	}
		

		static class Student {
		int age;
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		int id;
		String gender;
		String name;
		String cs;
		Student(int id,String name,String gender,int age,String cs){
		  this.age=age;
		  this.name=name;
		  this.gender=gender;
		  this.id=id;
		  this.cs=cs;
		}
		public String toString(){
		  return id+"  "+name+"  "+gender+"  "+age+"  "+cs;
		}
	

}
}
