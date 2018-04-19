package pers.goetboy.services;

import java.util.List;

public abstract class AbstractService<T> {
    /**
     * 获取信息
     *
     * @param id
     * @return
     */
    abstract T get(Integer id);

    /**
     * 查询所有
     *
     * @return
     */
    abstract List<T> listAll();

    /**
     * 保存
     * @param t
     */
    abstract void save(T t);

    /**
     * 更新
     * @param t
     */
    abstract void update(T t);

    /**
     * 删除
     * @param id
     */
    abstract void delete(Integer id);

}
