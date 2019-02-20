package pers.goetboy.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.AbstractService;
import pers.goetboy.exam.mapper.PaperMapper;
import pers.goetboy.exam.model.entity.Paper;

/**
 * @author:goetb
 * @date 2019 /02 /13
 **/
@Service
public class PaperService extends AbstractService<Paper> {
    private final PaperMapper paperMapper;

    /**
     * 构造函数
     *
     * @param paperMapper 主要mapper
     */
    @Autowired
    public PaperService(PaperMapper paperMapper) {
        super(paperMapper);
        this.paperMapper = paperMapper;

    }
}
