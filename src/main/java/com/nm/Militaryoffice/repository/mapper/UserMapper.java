package com.nm.Militaryoffice.repository.mapper;

import com.nm.Militaryoffice.model.User;
import com.nm.Militaryoffice.model.UserRole;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        var user = new User();
        user.setId(rs.getLong("user_id"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        user.setPatronymic(rs.getString("patronymic"));
        user.setSurname(rs.getString("surname"));
        user.setPassword(rs.getString("password"));
        user.setIsLocked(rs.getBoolean("isLocked"));
        user.setUserRole(UserRole.valueOf(rs.getString("role").toUpperCase()));

        return user;
    }
}
