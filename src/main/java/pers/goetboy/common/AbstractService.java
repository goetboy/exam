package pers.goetboy.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.transaction.annotation.Transactional;
import pers.goetboy.common.exception.service.ServiceTipsException;

/**
 * 业务层抽象类，需要对baseMapper进行初始化
 *
 * @param <T>
 * @author goetb
 */
@Transactional(rollbackFor = Throwable.class)
public abstract class AbstractService<T extends AbstractEntity> {
    /**
     * 业务类主要mapper
     * 需要子类注入
     */
    protected final BaseMapper<T> baseMapper;

    /**
     * 构造函数，强制子类注入baseMapper
     *
     * @param baseMapper 类基础mapper
     */
    public AbstractService(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 返回实体类
     *
     * @param id id
     * @return 实体类
     */
    public  T get(Long id) {
        return  baseMapper.selectById(id);
    }

    /**
     * 分页返回实体类
     *
     * @param page 分页信息
     * @return 实体分页列表
     */
    public IPage page(IPage page) {
        return baseMapper.selectPage(page, null);
    }

    /**
     * 保存实体类
     *
     * @param t 实体类
     */
    public Long save(T t) {
        baseMapper.insert(t);
        if (t != null) {
            return t.getId();
        } else {
            return null;
        }
    }

    /**
     * 更新实体类
     *
     * @param t 实体类 含id
     */
    public void update(T t) {
        baseMapper.updateById(t);
    }

    /**
     * 更改实体状态
     *
     * @param id    实体id
     * @param state 状态 0禁用|1正常
     */
    public void updateState(Long id, EntityState state) {
        T entity = baseMapper.selectById(id);
        if (entity != null) {
            entity.setState(state.getValue());
            baseMapper.updateById(entity);
        } else {
            throw new ServiceTipsException("无法更新此对象的状态");
        }
    }

    /**
     * 删除实体类
     *
     * @param id 实体类id
     */
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }

}
