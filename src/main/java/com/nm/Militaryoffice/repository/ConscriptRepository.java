package com.nm.Militaryoffice.repository;

import com.nm.Militaryoffice.dao.ConscriptDao;
import com.nm.Militaryoffice.model.Conscript;
import com.nm.Militaryoffice.repository.mapper.ConscriptMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.sql.Types;
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
        return jdbcTemplate.update(sql, sqlParameterSource);
    }

//    @Override
//    public int deleteConscript(Long id) {
//        return jdbcTemplate.getJdbcTemplate().update("DELETE FROM Conscript WHERE id = ?", id);
//    }

    @Override
    public int updateConscript(Conscript conscript) {
        return 0;
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
}
