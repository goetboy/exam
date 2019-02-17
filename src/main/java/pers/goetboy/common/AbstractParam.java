package pers.goetboy.common;

import lombok.Data;
import pers.goetboy.entity.AbstractEntity;

/**
 * 抽象参数
 */
@Data
public abstract class AbstractParam<T extends AbstractEntity> {
    protected Long id;
    protected Integer state;
    protected T entity;

}
