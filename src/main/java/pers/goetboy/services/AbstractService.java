package pers.goetboy.services;

import pers.goetboy.common.exception.service.ServiceTipsException;

import java.util.List;

public abstract class AbstractService<T> {
    /**
     * 获取信息
     *
     * @param id
     * @return
     */
    public abstract T get(Long id);

    /**
     * 查询所有
     *
     * @return
     */
    public   abstract List<T> findAll();

    /**
     * 保存
     * @param t
     */
    public  abstract Long save(T t) throws ServiceTipsException;

    /**
     * 更新
     * @param t
     */
    public  abstract void update(T t);

    /**
     * 删除
     * @param id
     */
    public   abstract void delete(Long id);

}
