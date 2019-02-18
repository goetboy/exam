package pers.goetboy.services;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.AbstractService;
import pers.goetboy.entity.EntityState;
import pers.goetboy.entity.sys.Dict;
import pers.goetboy.mapper.DictMapper;

import java.util.List;

/**
 * @author:goetb
 * @date 2019 /02 /13
 **/
@Service
public class DictService extends AbstractService<Dict> {
    private final DictMapper dictMapper;

    /**
     * 构造函数
     *
     * @param dictMapper 主要mapper
     */
    @Autowired
    public DictService(DictMapper dictMapper) {
        super(dictMapper);
        this.dictMapper = dictMapper;

    }

    /**
     * 通过父id查询子字典
     *
     * @param parentCode 父分组id
     * @return 子分组列表
     */
    public List<Dict> listByParentCode(String parentCode) {
        return dictMapper.selectList(Wrappers.<Dict>lambdaQuery().eq(Dict::getParentCode, parentCode));
    }

    /**
     * 通过code查询字典
     *
     * @param code 字典编码
     * @return 字典对象
     */
    public Dict getByCode(String code) {
        return dictMapper.selectOne(Wrappers.<Dict>lambdaQuery().eq(Dict::getCode, code).eq(Dict::getState, EntityState.NORMAL.getValue()));
    }
}
