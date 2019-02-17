package pers.goetboy.services;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.AbstractService;
import pers.goetboy.entity.sys.Group;
import pers.goetboy.mapper.GroupMapper;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author:goetb
 * @date 2019 /02 /13
 **/
@Service
public class GroupService extends AbstractService<Group> {
    private final GroupMapper groupMapper;

    public TreeSet<Group> listGroupTree(Long parentId) {
        if(parentId==null){
            parentId=0L;
        }
        List<Group> groups = listGroupByParentId(parentId);
        if(CollectionUtils.isEmpty(groups)){
            return null;
        }
        groups.forEach(group -> {

        });
        Set<Group> treeSet = groups.stream().collect(Collectors.toSet());
        return new TreeSet<Group>(treeSet);
    }

    /**
     * 通过parentId查询子分组列表
     *
     * @param parentId 父分组id
     * @return 子分组列表
     */
    public List<Group> listGroupByParentId(Long parentId) {
        return groupMapper.selectList(Wrappers.<Group>lambdaQuery().eq(Group::getParentId, parentId));
    }

    /**
     * 构造函数，强制子类注入baseMapper
     *
     * @param groupMapper 主要mapper
     */
    @Autowired
    public GroupService(GroupMapper groupMapper) {
        super(groupMapper);
        this.groupMapper = groupMapper;

    }
}
