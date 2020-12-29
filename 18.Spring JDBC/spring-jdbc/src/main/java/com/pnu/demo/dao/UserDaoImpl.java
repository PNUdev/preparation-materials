package com.pnu.demo.dao;

import com.pnu.demo.mapper.UserRowMapper;
import com.pnu.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.NumberUtils;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String SQL_SELECT_USERS = "SELECT * FROM users";
    private static final String SQL_CREATE_USER = "INSERT into users (first_name, last_name) values(?,?)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET first_name=?, last_name=? WHERE id = ?";


    private JdbcTemplate jdbcTemplate;
    private UserRowMapper userRowMapper;

    public UserDaoImpl(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getFirstName());
                    ps.setString(2, user.getLastName());
                    return ps;
                }, keyHolder
        );
        user.setId(NumberUtils.convertNumberToTargetClass(keyHolder.getKey(), Long.class));
        return user;
    }

    @Override
    public User update(User user) {
      jdbcTemplate.update(SQL_UPDATE_USER, user.getFirstName(), user.getLastName(), user.getId());
      return user;
    }

    @Override
    public User getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, userRowMapper, id );
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE_USER_BY_ID, id);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(SQL_SELECT_USERS, userRowMapper);
    }
}
