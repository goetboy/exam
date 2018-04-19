package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.sys.RoleUser;
import pers.goetboy.mapper.RoleUserMapper;

import java.util.List;

@Service
public class RoleUserService extends AbstractService<RoleUser> {
    @Autowired
    RoleUserMapper roleUserMapper;
    @Override
    public RoleUser get(Integer id) {
        return roleUserMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    List<RoleUser> listAll() {
        return roleUserMapper.getAll();
    }

    @Override
    void save(RoleUser user) {
        roleUserMapper.dynamicInsert(user);
    }

    @Override
    void update(RoleUser user) {
        roleUserMapper.dynamicUpdate(user);
    }

    @Override
    void delete(Integer id) {
        roleUserMapper.delete(id);
    }


}
