package com.bins.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 2011-6-12
 * @author andybin
 *
 */
public class ComComponentInfoUtil {
	//use jdk logger
	private static Logger log = Logger.getLogger(ComComponentInfoUtil.class.getName());
	
	/**
	 * Test ComComponentInfo
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		
		ComComponentInfoUtil com = new ComComponentInfoUtil();
		UserInfoTest p = new UserInfoTest();
		p.setAge(23);
		p.setUsb(false);
		p.setSex("male");
		p.setId("2323");
		System.out.println(com.getObjectToStr(p,"$|$",true));
	}
	
	/**
	 * spit string 
	 * @param obj
	 * @param spit
	 * @param isOrder
	 * @return
	 * @throws Exception
	 */
	public String getObjectToStr(Object obj,String spit,boolean isOrder) 
				throws Exception {
		StringBuffer strBuff = new StringBuffer();
		Class clazz = obj.getClass();
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		int i = 0;
		for (Method method:clazz.getMethods()) {
			if (this.isClassMethod(method.getName())) {
				ComAnnotation comAnnotation = 
					this.getMethodAndFieldAnnotion(method, clazz);
				if (i > 0) {
					strBuff.append(spit);
				}
				if (comAnnotation != null) {
					//display information
					if (comAnnotation.name() == null 
							|| "".equals(comAnnotation.name())) {
						strBuff.append(this.getFieldNameByMethodName(method
								.getName()) + "=").
						append(method.invoke(obj, null));
					}
					else {
						strBuff.append(comAnnotation.name()+"=").
						append(method.invoke(obj, null));
					}
					//if true is order
					if (isOrder) {
						if (comAnnotation.name() == null 
								|| "".equals(comAnnotation.name())) {
							String str = this.getFieldNameByMethodName(
									method.getName()) + "=" +
							method.invoke(obj, null);
							if (map.containsKey(comAnnotation.order())) {
								log.info("name " +comAnnotation.name() + ":order = "
										+ comAnnotation.order() + " already exists ");
							}
							map.put(comAnnotation.order(), str);
						} 
						else {
							String str = comAnnotation.name() + "=" + 
							method.invoke(obj, null);
							if (map.containsKey(comAnnotation.order())) {
								log.info("name " +comAnnotation.name() + ":order = "
										+ comAnnotation.order() + " already exists ");
							}
							map.put(comAnnotation.order(), str);
						}
					}
				}
				else {
					if (comAnnotation.name() == null 
							|| "".equals(comAnnotation.name())) {
						strBuff.append(this.getFieldNameByMethodName(
								method.getName()) + "=").
						append(method.invoke(obj, null));
					}
					else {
						strBuff.append(comAnnotation.name()+"=").
						append(method.invoke(obj, null));
					}
					//if true is order
					if (isOrder) {
					    String str = comAnnotation.name() + "=" + 
					    method.invoke(obj, null);
					    if (map.containsKey(comAnnotation.order())) {
					    	log.info("name " +comAnnotation.name() + ":order = "
					    			+ comAnnotation.order() + " already exists ");
					    }
					    map.put(comAnnotation.order(), str);
					}
				}
				i++;
			}
		}
		
		if (isOrder) {
			int j = 0 ;
			StringBuffer strBuffOrder = new StringBuffer();
			for(Map.Entry<Integer,String> en:map.entrySet()) {
				if (j > 0){
					strBuffOrder.append(spit + en.getValue());
				} 
				else {
					strBuffOrder.append(en.getValue());
				}
				j++;
			}
			return strBuffOrder.toString();
		} 
		else {
			return strBuff.toString();
		}
	}
	

	/**
	 * getAnnotation Information
	 * @param method
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public ComAnnotation getMethodAndFieldAnnotion(Method method,
			Class clazz) throws Exception{
		ComAnnotation comAnn = method.getAnnotation(ComAnnotation.class);
		if (comAnn != null) {
			return comAnn;
		}
		else {
			String varFieldName = this.getFieldNameByMethodName(method
					.getName());
			Field field = clazz.getDeclaredField(varFieldName);
			comAnn = field.getAnnotation(ComAnnotation.class);
			if (field != null && comAnn != null) {
				return comAnn;
			}
		}
		return comAnn;
	}
	
	
	/**
	 * is methodName query fieldName
	 * @param methodName
	 * @return String
	 */
	public String getFieldNameByMethodName(String methodName) {
		
		if (methodName.startsWith("get")) {
			String varFieldName = methodName.substring(3);
			String varUpToLower = this.getUpToLower(varFieldName);
			if (!("".equals(varUpToLower))) {
				return varUpToLower;
			}
		}
		else if (methodName.startsWith("is")) {
			String varFieldName = methodName.substring(2);
			String varUpToLower = this.getUpToLower(varFieldName);
			if (!("".equals(varUpToLower))) {
				return varUpToLower;
			}
		}
		return null;
	}
	
	/**
	 * getName ---> Name ----return name;
	 * @param str
	 * @return String
	 */
    public String getUpToLower(String str) {
    	if (str != null && !("".equals(str))
    			&& str.length() > 0) {
    		char ch[] = str.toCharArray();
    		ch[0] = (char)(ch[0] + 32);
    		return new String(ch);
    	}
    	return "";
    }
	
    /**
     * if true is Field
     * @param methodName
     * @return
     */
	public boolean isClassMethod(String methodName) {
		if (methodName != null && methodName.length() > 3
				&& (methodName.startsWith("get")) 
				&& (!methodName.equals("getClass")) ) {
			return true;
		}
		else if (methodName != null && methodName.length() > 2
					&& (methodName.startsWith("is"))) {
			return true;
		}
		else {
			return false;
		}
	}

}
