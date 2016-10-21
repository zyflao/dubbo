package Spring.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapUtils {
public static void main(String[] args) {
	Map<Integer,Integer> map = new HashMap<Integer,Integer>();
	map.put(-1, 1);
	map.put(-2, 1);
	map.put(-3, 1);
	int i = -3;
	if(map.containsKey(i)){
		System.out.println(i);
	}
	Set<Integer> set = new HashSet<Integer>();
	set.add(-1);
	set.add(-2);
	set.add(-3);
	if(set.contains(i)){
		System.out.println("set"+i);
	}
	List<Integer> l = new ArrayList<Integer>();
	l.add(-1);
	l.add(-2);
	l.add(-3);
	System.out.println(l.contains(i));
	}



}
