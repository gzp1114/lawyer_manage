package com.lawyer.cores.tools.other;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;


/**
 * json字符串反序列化为各种对象
 * @author zhb
 *
 */
public class JsonSerializer {
	public static ObjectMapper mapper = new ObjectMapper();
	public static String write(Object obj){
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取bean对象
	 * @param str
	 * @param classType
	 * @return
	 */
	public static <T> T readBean(String str,Class<T> classType){
		try {
			return mapper.readValue(str, classType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取list<bean>对象
	 * @param str
	 * @param classType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> readListBean(String str,Class<?> collectionType,Class<T> classType){
		try {
			JavaType javaType = getCollectionType(collectionType, classType); 
			return (List<T>)mapper.readValue(str, javaType); 
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取Map<K,V>对象
	 * @param str
	 * @param kType
	 * @param vType
	 * @return
	 */
	@SuppressWarnings("rawtypes") 
	public static <K,V> Map<K,V> readMapBean(String str,Class<? extends Map> mapClass,Class<K> kType,Class<V> vType){
		try {
			MapType mapType = getMapType(mapClass, kType, vType);
			return mapper.readValue(str, mapType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取 Map<K,List<V>>对象
	 * @param str
	 * @param kType
	 * @param vType
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <K,V> Map<K,List<V>> readMapofList(String str, Class<? extends Collection> collectionClass, Class<K> kType,Class<V> vType){
		try {
			JavaType javaType = getCollectionType(collectionClass, vType); 
			JavaType type = mapper.getTypeFactory().constructParametricType(kType,javaType);
			return mapper.readValue(str, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取List<Map<K,V>>对象
	 * @param str
	 * @param kType
	 * @param vType
	 * @return
	 */
	@SuppressWarnings("rawtypes") 
	public static <K,V> Collection<Map<K,V>> readListofMap(String str,Class<? extends Collection> collectionClass,Class<? extends Map> mapClass,Class<K> kType,Class<V> vType){
		try {
			JavaType javaType = mapper.getTypeFactory().constructCollectionType(collectionClass, getMapType(mapClass, kType, vType));
			return mapper.readValue(str, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
    }
	public static <K,V> MapType getMapType(@SuppressWarnings("rawtypes") Class<? extends Map> mapClass,Class<K> keyType,Class<V> vType){
		return mapper.getTypeFactory().constructMapType(mapClass,keyType,vType);
	}
}
