package com.nm.Militaryoffice.repository;

import com.nm.Militaryoffice.dao.ConscriptDao;
import com.nm.Militaryoffice.model.Conscript;
import com.nm.Militaryoffice.model.Conscription;
import com.nm.Militaryoffice.model.SummonsInfo;
import com.nm.Militaryoffice.repository.mapper.ConscriptMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ConscriptRepository implements ConscriptDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ConscriptRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Conscript> listConscripts() {
        String SQL = """
                SELECT *
                FROM Conscript
                """;
        return jdbcTemplate.query(SQL, new ConscriptMapper());
    }

    @Override
    public int insertConscript(@NotNull final Conscript conscript) {
        String sql = """
                INSERT INTO Conscript (
                    series_and_number,
                    passport,
                    name,
                    patronymic,
                    surname,
                    gender,
                    date_of_birth,
                    place_of_birth,
                    place_of_residence,
                    occupation,
                    marital_status,
                    sports_category
                )
                VALUES (
                    :series_and_number,
                    :passport,
                    :name,
                    :patronymic,
                    :surname,
                    :gender,
                    :date_of_birth,
                    :place_of_birth,
                    :place_of_residence,
                    :occupation,
                    :marital_status,
                    :sports_category
                );
                """;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("series_and_number", conscript.getSeriesAndNumber(), Types.VARCHAR)
                .addValue("passport", conscript.getPassport(), Types.VARCHAR)
                .addValue("name", conscript.getName(), Types.VARCHAR)
                .addValue("patronymic", conscript.getPatronymic(), Types.VARCHAR)
                .addValue("surname", conscript.getSurname(), Types.VARCHAR)
                .addValue("gender", conscript.getGender(), Types.CHAR)
                .addValue("date_of_birth", conscript.getDateOfBirth(), Types.DATE)
                .addValue("place_of_birth", conscript.getPlaceOfBirth(), Types.VARCHAR)
                .addValue("place_of_residence", conscript.getPlaceOfResidence(), Types.VARCHAR)
                .addValue("occupation", conscript.getOccupation(), Types.VARCHAR)
                .addValue("marital_status", conscript.getMaritalStatus(), Types.VARCHAR)
                .addValue("sports_category", conscript.getSportsCategory(), Types.VARCHAR);
        if (jdbcTemplate.update(sql, sqlParameterSource) == 1) {
            try {
                return jdbcTemplate.getJdbcTemplate()
                        .queryForObject( "SELECT currval(pg_get_serial_sequence('conscript','id'));", Integer.class);
            } catch (NullPointerException e) {
                return -1;
            }
        }
        return -1;
    }

    @Override
    public Optional<Conscript> getConscriptById(Long id) {
        String sql = "SELECT * FROM Conscript WHERE id = ?;";
        return jdbcTemplate.getJdbcTemplate().query(
                sql,
                new Object[] {id},
                new int[] {Types.BIGINT},
                new ConscriptMapper()
        ).stream().findFirst();
    }

    // Получить всех призывников, которые были отправлены на службу за определенный призыв
    public List<Conscript> getConscriptsThatEnlistment(Conscription conscription) {
        var sql = """
                SELECT 
                    Conscript.*
                FROM Commission_decision
                JOIN Conscript
                    ON Commission_decision.id = Conscript.id
                WHERE 
                    decision_date BETWEEN :start_date AND :end_date
                    AND is_enlistment    
                """;
        var sqlParameterSource = new MapSqlParameterSource()
                .addValue("start_date", conscription.getStart_date())
                .addValue("end_date", conscription.getEnd_date());
        return jdbcTemplate.query(sql, sqlParameterSource, new ConscriptMapper());
    }

    public Long getCountConscriptsThatEnlistment(Conscription conscription) {
        var sql = """
                SELECT 
                    COUNT(id)
                FROM Commission_decision
                JOIN Conscript
                    ON Commission_decision.id = Conscript.id
                WHERE 
                    decision_date BETWEEN :start_date 
                    AND :end_date AND is_enlistment
                """;
        var sqlParameterSource = new MapSqlParameterSource()
                .addValue("start_date", conscription.getStart_date())
                .addValue("end_date", conscription.getEnd_date());

        return jdbcTemplate.queryForObject(sql, sqlParameterSource, Long.class);
    }

    public Optional<Conscript> findConscriptByNumberPripisnoe(String series_and_number_pripisnoe) {
        var sql = """
                SELECT * 
                FROM Conscript
                WHERE series_and_number = :series_and_number;
                """;
        var parameter = new MapSqlParameterSource()
                .addValue("series_and_number", series_and_number_pripisnoe);
        return jdbcTemplate.query(sql, parameter, new ConscriptMapper())
                .stream().findFirst();
    }

    public List<Conscript> findConscriptBySurname(String surname) {
        var sql = "SELECT * FROM Conscript WHERE surname = :surname;";
        var parameter = new MapSqlParameterSource()
                .addValue("surname", surname);
        return jdbcTemplate.query(sql, parameter, new ConscriptMapper())
                .stream().toList();
    }

    public List<Conscript> findConscriptByBirthday(Date birthday) {
        var sql = "SELECT * FROM Conscript WHERE date_of_birth = :birthday;";
        var parameter = new MapSqlParameterSource().addValue("birthday", birthday);
        return jdbcTemplate.query(sql, parameter, new ConscriptMapper());
    }

    public int updateConscript(Conscript conscript) {
        var sql = """
                UPDATE
                    Conscript
                SET
                    "name" = :name,
                    passport = :passport,
                    surname = :surname,
                    patronymic = :patronymic,
                    gender = :gender,
                    date_of_birth = :dateOfBirth,
                    place_of_birth = :placeOfBirth,
                    occupation = :occupation,
                    marital_status = :maritalStatus,
                    sports_category = :sportsCategory
                WHERE
                    id = :id;
                """;
        var parameters = new MapSqlParameterSource()
                .addValue("id", conscript.getId())
                .addValue("name", conscript.getName())
                .addValue("passport", conscript.getPassport())
                .addValue("surname", conscript.getSurname())
                .addValue("patronymic", conscript.getPatronymic())
                .addValue("gender", conscript.getGender())
                .addValue("dateOfBirth", conscript.getDateOfBirth())
                .addValue("occupation", conscript.getOccupation())
                .addValue("maritalStatus", conscript.getMaritalStatus())
                .addValue("placeOfBirth", conscript.getPlaceOfBirth())
                .addValue("sportsCategory", conscript.getSportsCategory());

        return jdbcTemplate.update(sql, parameters);
    }

    public List<Conscript> getAllConscriptThatFits(SummonsInfo summonsInfo) {
        var sql = """
                SELECT
                    *
                FROM Conscript
                WHERE
                    date_part('year', age(date_of_birth::date)) BETWEEN :minAge AND :maxAge;
                """;
        var parameters = new MapSqlParameterSource()
                .addValue("minAge", summonsInfo.getAgeFrom())
                .addValue("maxAge", summonsInfo.getAgeTo())
                .addValue("limitN", summonsInfo.getSummonsMaxCount());

        return jdbcTemplate.query(sql, parameters, new ConscriptMapper());
    }
}