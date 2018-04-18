package pers.goetboy.dao;

import pers.goetboy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDao {


    public List<User> findAll() {
        List<User> ret;
        String sql = "SELECT * FROM " + User.TABLE_NAME;
      //  ret = jdbcTemplate.query(sql, new CategoryRowMapper());
        return null;
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
