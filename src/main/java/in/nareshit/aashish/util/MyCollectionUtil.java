package in.nareshit.aashish.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * We are declaring here this interface for conversion of List<Object[]> type
 * data into Map<Integer,String> type. We could also take here class instead
 * of interface but unnecessarily it would allocate memory, so better to take
 * interface instead of class, this will improves the performace of the 
 * application.
 * And also we have declared a static method in the interface and JDK 1.8
 * allows us to declare a static method in the interface. 
 */
public interface MyCollectionUtil {
	
	//JDK 1.8 -- static methods in interface
	public static Map<Integer, String> convertListToMap(List<Object[]> list) {
		
		// convert this List<Object[]> type data into Map<Integer,String> type
		
		Map<Integer, String> map = new LinkedHashMap<>();
		for (Object[] ob : list) {
			map.put(Integer.valueOf(ob[0].toString()), ob[1].toString());
		}
		
		
		//alternative logic for above commented code 
		//JDK 1.8- Streams (convert List -> Map)
		/*
		Map<Integer, String> map =
				list.stream()
				   .collect(
						   Collectors.toMap(
								   ob->Integer.valueOf(ob[0].toString()),
								   ob->ob[1].toString())
						   );		
		*/
		return map;
	}

}
