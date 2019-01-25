package pers.goetboy.utils;

import java.util.HashMap;

/**
 * 数据公交
 *
 * @author goetb
 */
public class DataBus {
    private final static ThreadLocal<HashMap<String, Object>> DATA_BUS = new ThreadLocal<>();

    /**
     * 获取总线中的数据
     *
     * @param key the key
     * @return the value
     */
    public static Object get(String key) {
        return DATA_BUS.get().get(key);
    }

    /**
     * 写入数据总线
     *
     * @param key    the key
     * @param object the value
     */
    public static synchronized void put(String key, Object object) {
        DATA_BUS.get().put(key, object);
    }

    /**
     * 清空数据总线
     */
    public static void clear() {
        DATA_BUS.get().clear();
    }

    public static synchronized void remove() {
        DATA_BUS.remove();
    }

}
