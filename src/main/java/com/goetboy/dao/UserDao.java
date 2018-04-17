package com.goetboy.dao;

import com.goetboy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDao {
    @Autowired
    private DataSource dataSource;
    private DataSource getDataSource(){
        if (dataSource == null) {
            throw new RuntimeException("DataSource is null (configuration error, perhaps?)");
        }
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<User> findAll() {
        List<User> ret;
        JdbcTemplate jdbc = new JdbcTemplate(getDataSource());
        String sql = "SELECT * FROM " + User.TABLE_NAME;
        ret = jdbc.query(sql, new CategoryRowMapper());
        return ret;
    }

    /**
     * RowMapper for Category objects.
     * Used in conjection with JdbcTemplate.
     */
    public static class CategoryRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new User();
        }
    }

}
