package org.lee.framework.lang;
/*
 * Created on 7 nov. 2005
 */


import java.beans.BeanInfo;
import java.beans.Expression;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;


/**
 * @author lknhiayi
 */
public final class ClassUtils {

	public static String getClassShortName(Class clazz) {
		String name = clazz.getName();
		int lastIndexOf = name.lastIndexOf("$");
		if (-1 != lastIndexOf) {
			return name.substring(lastIndexOf + 1);
		}
		return name;
	}

	/**
	 * General toString 
	 */
	public static String toString(Object o) {
		StringBuffer str = new StringBuffer();
		str.append("(").append(getClassShortName(o.getClass())).append(" : ");
	    try {
	        BeanInfo bi = Introspector.getBeanInfo(o.getClass());
	        PropertyDescriptor[] pds = bi.getPropertyDescriptors();
	        for (int i = 0; i < pds.length; ++i) {
	        	String propertyName = pds[i].getName();
	        	if ("class".equals(propertyName))
	        		continue;
	            String getter = pds[i].getReadMethod().getName();
	            Expression expr = new Expression(o, getter, new Object[0]);
	            expr.execute();
	            Object ov = expr.getValue();
	            String s = ov == null? "null": ov.toString();
	            str.append(propertyName).append("=").append(s);
	            if (i < pds.length - 1)
	            	str.append(", ");
	        }
	    } catch (java.beans.IntrospectionException e) {
	    	e.printStackTrace();
	    } catch (Exception e) {
			e.printStackTrace();
		}
	    str.append(")");
		return str.toString();
	}
	
}
