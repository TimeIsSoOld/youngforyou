package com.example.youngforyou.commom.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtils {

	public static List<Map<String, Object>> ArrayToTreeData(List<Map<String, Object>> mydata, String id, String pid) {
		Map<Object, Object> h = new HashMap<Object, Object>();// 数据索引
		List<Map<String, Object>> r = new ArrayList<Map<String, Object>>();// 数据池，返回的数据

		for (Map<String, Object> item : mydata) {
			if (!item.containsKey(id)) {
				continue;
			} else {
				h.put(item.get(id), item);
			}
		}

		for (Map<String, Object> item : mydata) {
			if (!item.containsKey(id)) {
				continue;
			}
			if (!item.containsKey(pid) || (item.get(pid) == null) || !h.containsKey(item.get(pid))) {
				r.add(item);
			} else {
				Map<String, List<Map<String, Object>>> pitem = (Map<String, List<Map<String, Object>>>) h.get(item.get(pid));
				if (!pitem.containsKey("children")) {
					List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
					children.add(item);
					pitem.put("children", children);
				} else {
					List<Map<String, Object>> children = pitem.get("children");
					children.add(item);
					pitem.put("children", children);
				}
			}
		}
		return r;
	}
	
	
	public static List<Map<String,Object>> toLowerKey(List<Map<String,Object>> list){
		
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>(list.size());
		Map<String,Object> r = null;
		for(Map<String,Object> map : list) {
			
			r = new HashMap<String,Object>(map.size());
			
			for(String key : map.keySet()) {
				r.put(key.toLowerCase(), map.get(key));
				
				r.put(key, map.get(key));
			}
			result.add(r);
		}
		
		return result;
	}
}
