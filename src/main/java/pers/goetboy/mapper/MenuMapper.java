package pers.goetboy.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface MenuMapper extends Mapper<Menu> {
}
