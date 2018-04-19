package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.mapper.MenuMapper;

import java.util.List;

@Service

public class MenuService extends AbstractService<Menu> {
    @Autowired
    MenuMapper menuMapper;
    @Override
    public Menu get(Integer id) {
        return menuMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    List<Menu> listAll() {
        return menuMapper.getAll();
    }

    @Override
    void save(Menu menu) {
        menuMapper.dynamicInsert(menu);
    }

    @Override
    void update(Menu menu) {
        menuMapper.dynamicUpdate(menu);
    }

    @Override
    void delete(Integer id) {
        menuMapper.delete(id);
    }


}
