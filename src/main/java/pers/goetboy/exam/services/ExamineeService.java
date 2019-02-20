package pers.goetboy.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.AbstractService;
import pers.goetboy.exam.mapper.ExamineeMapper;
import pers.goetboy.exam.model.entity.Examinee;

/**
 * 考生service
 *
 * @author:goetb
 * @date 2019 /02 /13
 **/
@Service
public class ExamineeService extends AbstractService<Examinee> {
    private final ExamineeMapper examineeMapper;

    /**
     * 构造函数
     *
     * @param examineeMapper 主要mapper
     */
    @Autowired
    public ExamineeService(ExamineeMapper examineeMapper) {
        super(examineeMapper);
        this.examineeMapper = examineeMapper;

    }
}
