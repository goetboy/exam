package pers.goetboy.sys.services;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.AbstractService;
import pers.goetboy.common.Tree;
import pers.goetboy.sys.model.entity.Group;
import pers.goetboy.sys.mapper.GroupMapper;

import java.util.List;

/**
 * @author:goetb
 * @date 2019 /02 /13
 **/
@Service
public class GroupService extends AbstractService<Group> {
    private final GroupMapper groupMapper;

    /**
     * 构造函数
     *
     * @param groupMapper 主要mapper
     */
    @Autowired
    public GroupService(GroupMapper groupMapper) {
        super(groupMapper);
        this.groupMapper = groupMapper;

    }

    /**
     * 分组树结构，如果父id为空，默认父id为0查询
     * id 0 为根节点id，数据库中实际上并不存在为此id的数据，但1级节点的父id默认即是0
     * @param parentId 父id
     * @return 分组树
     */
    public Tree<Group> listGroupTree(Long parentId) {
        if (parentId == null) {
            parentId = 0L;
        }
        List<Group> groups = groupMapper.selectTreeByParentId(parentId);
        if (CollectionUtils.isEmpty(groups)) {
            return null;
        }
        return new Tree<>(null, groups);
    }

    /**
     * 通过parentId查询子分组列表
     *
     * @param parentId 父分组id
     * @return 子分组列表
     */
    public List<Group> listByParentId(Long parentId) {
        return groupMapper.selectList(Wrappers.<Group>lambdaQuery().eq(Group::getParentId, parentId));
    }
}
