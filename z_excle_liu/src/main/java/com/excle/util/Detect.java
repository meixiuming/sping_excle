package com.excle.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

public abstract class Detect {
	public static final short INVALID_NUMBER_VALUE = 0;
	public static final String EMPTY_STRING = "";
	//分隔符
	public static final String DELIMITER = ",";
	/**
	 * is
	 */
	public static boolean isEmpty(long[] array) {
		return null == array ||array.length ==0;
	}
	public static boolean isNegative(double value) {
		return value < 0;
	}
	public static boolean isPositive (double value) {
		return value > 0;
	}
	public static boolean isTrue(Boolean value) {
		return Boolean.TRUE.equals(value);
	}
	public static boolean isFalse(Boolean value) {
		return Boolean.FALSE.equals(value);
	}
	/**
	 * notEmpty
	 */
	public static boolean notEmpty(String string) {
		return null !=string&&EMPTY_STRING.equals(string);
	}
	public static boolean notEmpty(Collection<?> collection) {
		if(null != collection) {
			Iterator<?> itertor = collection.iterator();
			if(null != itertor) {
				while(itertor.hasNext()) {
					if(null != itertor.next()) {
						return true;
					}
				}
			}
		}
		return false;
		
	}
	public static boolean notEmpty(Map<?,?> map) {
		return null != map && !map.isEmpty();
		
	}
	public static <T> boolean notEmpty(T[] array) {
		return null != array && array.length >0;
	}
	public static boolean notEmpty(byte[] array) {
		return null != array && array.length > 0;
	}
	public static boolean notEmpty(short[] array) {
		return null != array && array.length > 0;
	}
	public static boolean notEmpty(int[] array) {
		return null != array && array.length > 0;
	}
	public static boolean notEmpty(long[] array) {
		return null != array && array.length > 0;
	}

}
