package net.jesse.framework.tools;

import java.util.HashSet;
import java.util.Set;


public class Sets {
	//����Set�ϲ�
	public static <T> Set<T> union(Set<T> a,Set<T> b){
		Set<T> result = new HashSet<T>(a);
		result.addAll(b);
		return result;
	}
	//����Set���в���
	public static <T> Set<T> intersection(Set<T> a,Set<T> b){
		Set<T> result = new HashSet<T>(a);
		result.retainAll(b);
		return result;
	}
	//��superset�Ƴ�subset
	public static <T> Set<T> difference(Set<T> superset,Set<T> subset){
		Set<T> result = new HashSet<T>(superset);
		result.removeAll(subset);
		return result;
	}
	//���س������������������
	public static <T> Set<T> complement(Set<T> a,Set<T> b){
		return difference(union(a,b),intersection(a,b));
	}
	
	public static void main(String[] args) {
		Sets.<String>union(null, null);
	}
}

