package com.nm.Militaryoffice.repository;

import com.nm.Militaryoffice.model.User;
import com.nm.Militaryoffice.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public UserRepository(@Autowired NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public void save(User user) {
        var sql = """
                INSERT INTO t_user (
                    email,
                    name,
                    patronymic,
                    surname,
                    password,
                    isLocked,
                    role
                )
                VALUES (
                    :email,
                    :name,
                    :patronymic,
                    :surname,
                    :password,
                    :isLocked,
                    :role
                );
                """;
        var sqlParameterSource = new MapSqlParameterSource()
                .addValue("email", user.getEmail())
                .addValue("name", user.getName())
                .addValue("patronymic", user.getPatronymic())
                .addValue("surname", user.getSurname())
                .addValue("password", user.getPassword())
                .addValue("isLocked", user.getIsLocked())
                .addValue("role", user.getUserRole().name());

        namedJdbcTemplate.update(sql, sqlParameterSource);
    }

    public List<User> getAllUsers() {
        var sql = """
                SELECT *
                FROM t_user;
                """;
        return namedJdbcTemplate.query(sql, new UserMapper());
    }

    public Optional<User> findUserById(Long userId) {
        var sql = """
                SELECT *
                FROM t_user
                WHERE user_id = :user_id;
                """;
        var mapParameter = new MapSqlParameterSource()
                .addValue("user_id", userId);
        return namedJdbcTemplate.query(sql, mapParameter, new UserMapper())
                .stream().findFirst();
    }

}
