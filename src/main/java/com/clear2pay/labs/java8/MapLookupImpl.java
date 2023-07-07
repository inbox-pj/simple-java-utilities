package com.clear2pay.labs.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MapLookupImpl implements MapLookup {

	@Override
	public <V> List<V> lookup(String regularExpression, Map<String, V> map) {
		 final Pattern pattern = Pattern.compile(regularExpression);
	        List<String> values  = map.keySet()
	                .stream()
	                .filter(string -> pattern.matcher(string).matches())
	                .collect(Collectors.toList());
	        if(values!= null && !values.isEmpty()){
	            return values.stream().map((key) -> map.get(key)).collect(Collectors.toList());

	        }
	        return new ArrayList<>();
	}

}
