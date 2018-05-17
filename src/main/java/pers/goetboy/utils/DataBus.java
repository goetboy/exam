package pers.goetboy.utils;

import java.util.HashMap;
import java.util.Map;

public class DataBus {
    private static Map<String,Object> dataBus = new HashMap();

    /**
     * 获取总线中的数据
     * @param key
     * @return
     */
    public  static  Object get(String key){
        return  dataBus.get(key);
    }

    /**
     * 写入数据总线
     * @param key
     * @param object
     */
    public  static  void  put(String key,Object object){
        dataBus.put(key,object);
    }

}
