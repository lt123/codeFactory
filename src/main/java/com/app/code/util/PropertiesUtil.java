package com.app.code.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
	  private static PropertiesUtil propertiesUtil = null;
	    private static Map<String,Properties> propertiesMap = null;

	    /**
	     *
	     * @return 如果实例存在则直接返回缓存了的实例
	     */
	    public static PropertiesUtil getInstance(){
	        if(propertiesUtil == null) {
	            synchronized (PropertiesUtil.class) {
	                propertiesUtil = new PropertiesUtil();
	                propertiesMap = new HashMap<>();
	            }
	        }
	        return propertiesUtil;
	    }

	    /**
	     *
	     * @param name 配置文件名称 不需要加properties
	     * @return  返回properties文件
	     */
	    public Properties load(String name) {
	        Properties properties = propertiesMap.get(name);
	        if(properties == null) {
	           try {
	               properties = new Properties();
	               properties.load(PropertiesUtil.class.getResourceAsStream("/" + name + ".properties"));
	               propertiesMap.put(name,properties);
	               return properties;
	           }catch (Exception e) {
	               e.printStackTrace();
	           }
	        }else{
	            return properties;
	        }
	        return null;
	    }
}
