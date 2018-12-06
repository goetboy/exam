package pers.goetboy.utils;

import java.util.HashMap;
import java.util.Map;

public class DataBus {
    private final static ThreadLocal<HashMap> dataBus = new ThreadLocal<HashMap>();

    /**
     * 获取总线中的数据
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return dataBus.get().get(key);
    }

    /**
     * 写入数据总线
     *
     * @param key
     * @param object
     */
    public static synchronized void put(String key, Object object) {
        dataBus.get().put(key, object);
    }

    /**
     * 清空数据总线
     */
    public static void clear() {
        dataBus.get().clear();
    }

}
