package pers.goetboy.common;

import lombok.Data;

/**
 * 抽象参数
 * @author goetb
 */
@Data
public abstract class AbstractParam<T extends AbstractEntity> {
    protected Long id;
    protected Integer state;
    protected T entity;

}
