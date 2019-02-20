package pers.goetboy.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.AbstractService;
import pers.goetboy.exam.model.entity.Exam;
import pers.goetboy.exam.mapper.ExamMapper;

/**
 * @author:goetb
 * @date 2019 /02 /13
 **/
@Service
public class ExamService extends AbstractService<Exam> {
    private final ExamMapper examMapper;

    /**
     * 构造函数
     *
     * @param examMapper 主要mapper
     */
    @Autowired
    public ExamService(ExamMapper examMapper) {
        super(examMapper);
        this.examMapper = examMapper;

    }
}
