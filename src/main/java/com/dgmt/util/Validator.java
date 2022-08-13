package com.dgmt.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Validator {
	
	static Boolean valid = false;
	
	// we only want case insensitive letters and numbers  /^[ A-Za-z0-9()[\]+-*/%]*$/ //^[a-zA-Z0-9!@#$&()\\-.+,/\"]*$
	
	protected final static String ALPHA_NUMERIC = "/^[ A-Za-z0-9()[\\]+-*/%]*$/";
	
	private static Log logger = LogFactory.getLog(Validator.class);
	
	public static Boolean validate(List<?> listobj) {

		Map<String, Object> map = new HashMap<String, Object>();

		if (!listobj.isEmpty()) {

			Iterator<?> itr = listobj.iterator();

			while (itr.hasNext()) {

				try {

					Object obj = itr.next();

					if (obj != null) {

						map = getFieldNamesAndValues(obj, false);
						if (!map.isEmpty())
							for (Entry<String, Object> entry : map.entrySet()) {

								if (entry.getValue() != null) {

									if (entry.getValue() instanceof String|| entry.getValue() instanceof Integer || entry.getValue() instanceof Float || entry.getValue() instanceof Long) {
										
										valid = validate(entry.getValue().toString());
										
									} else if (entry.getValue() instanceof Object) {
										
										valid = validate((Object) entry.getValue());
									}
									if (valid == false) {
										
										return false;
									}
								}
							}
					}
				} catch (Exception e) {
					
					logger.info("validate "+DGMTUtil.getStackTrace(e));
				}
			}
		}
		return valid;
	}

	public static Boolean validate(Map<String, ?> listobj) {

		Map<String, Object> map = new HashMap<String, Object>();

		if (listobj != null)
			
			try {
				
				String keyval = "";

				for (String key : listobj.keySet()) {

					keyval = key;
				}
				
				map = getFieldNamesAndValues(listobj.get(keyval), false);
				
                Object listval =map.get("elementData");
                
                Object[] list = (Object[]) listval;
                
                valid = validate( Arrays.asList(list));
                
                if (valid == false) {
                	
					return false; 
				}
				
			} catch (Exception e) {
				
				logger.info("validate "+DGMTUtil.getStackTrace(e));
			}
		return valid;
	}

	public static Boolean validate(Object obj) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			map = getFieldNamesAndValues(obj, false);
			if (!map.isEmpty())
				for (Entry<String, Object> entry : map.entrySet()) {
					if(entry !=null)
					{
					  valid = validate(entry.getValue().toString());
					}
					if (valid == false) {
						
						return false; 
					}
				}
		} catch (Exception e) {
			
			logger.info("validate "+DGMTUtil.getStackTrace(e));
		}

		return valid;
	}
	public static boolean validate(byte[] bytes) {
		
	    int expectedLen;
	    
	    int b11 = Integer.parseInt("0b10000000", 2);
	    int b12 = Integer.parseInt("0b00000000", 2);
	    int b21 = Integer.parseInt("0b11100000", 2);
	    int b22 = Integer.parseInt("0b11000000", 2);
	    int b31 = Integer.parseInt("0b11110000", 2);
	    int b32 = Integer.parseInt("0b11100000", 2);
	    int b41 = Integer.parseInt("0b11111000", 2);
	    int b42 = Integer.parseInt("0b11110000", 2);
	    int b51 = Integer.parseInt("0b11111100", 2);
	    int b52 = Integer.parseInt("0b11111000", 2);
	    int b61 = Integer.parseInt("0b11111110", 2);
	    int b62 = Integer.parseInt("0b11111100", 2);
	    int b01 = Integer.parseInt("0b11000000", 2);
	    int b02 = Integer.parseInt("0b10000000", 2);
	    
	    if      (bytes.length == 0) return false;
	    
	    else if ((bytes[0] & b11) == b12) expectedLen = 1;
	    else if ((bytes[0] & b21) == b22) expectedLen = 2;
	    else if ((bytes[0] & b31) == b32) expectedLen = 3;
	    else if ((bytes[0] & b41) == b42) expectedLen = 4;
	    else if ((bytes[0] & b51) == b52) expectedLen = 5;
	    else if ((bytes[0] & b61) == b62) expectedLen = 6;
	    else    return false;

	    if (expectedLen != bytes.length) return false;

	    for (int i = 1; i < bytes.length; i++) {
	        if ((bytes[i] & b01) != b02) {
	            return true; //for safe side
	        }
	    }

	    return true;
	}
	public static Boolean validate(String parameterValue) {
		
		boolean result = false;
		Pattern pattern = null;

		if(parameterValue != null) {
			
			pattern = Pattern.compile("^[a-zA-Z0-9_,:@$%^&*-_~()' ]*$");
			result = pattern.matcher(parameterValue).matches(); // does parameterValue contain legal characters?
			if(result== false)
			{
				logger.info("validate parameterValue "+parameterValue);
			}
		}
		return result;
	}

	public static Map<String, Object> getFieldNamesAndValues(final Object obj,boolean publicOnly)

	throws IllegalArgumentException, IllegalAccessException {
		
		Class<? extends Object> c1 = obj.getClass();
		
		Map<String, Object> map = new HashMap<String, Object>();

		Field[] fields = c1.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			if (publicOnly) {
				if (Modifier.isPublic(fields[i].getModifiers())) {
					Object value = fields[i].get(obj);
					map.put(name, value);
				}
			} else {
				fields[i].setAccessible(true);
				Object value = fields[i].get(obj);
				map.put(name, value);
			}
		}
		return map;
	}

}
