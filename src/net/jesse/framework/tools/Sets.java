package net.jesse.framework.tools;

import java.util.HashSet;
import java.util.Set;


public class Sets {
	//两个Set合并
	public static <T> Set<T> union(Set<T> a,Set<T> b){
		Set<T> result = new HashSet<T>(a);
		result.addAll(b);
		return result;
	}
	//两个Set共有部分
	public static <T> Set<T> intersection(Set<T> a,Set<T> b){
		Set<T> result = new HashSet<T>(a);
		result.retainAll(b);
		return result;
	}
	//从superset移除subset
	public static <T> Set<T> difference(Set<T> superset,Set<T> subset){
		Set<T> result = new HashSet<T>(superset);
		result.removeAll(subset);
		return result;
	}
	//返回除交集以外的所有内容
	public static <T> Set<T> complement(Set<T> a,Set<T> b){
		return difference(union(a,b),intersection(a,b));
	}
	
	public static void main(String[] args) {
		Sets.<String>union(null, null);
	}
}

