package com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl;


import com.bogdan_yanushkevich.javacore.crud.model.Specialty;
import com.bogdan_yanushkevich.javacore.crud.model.Status;
import com.bogdan_yanushkevich.javacore.crud.repository.SpecialtyRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import static com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.CommonSQLQueries.*;
import static com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.CommonSQLQueries.SPECIALTY_GET_ALL;

public class JdbcSpecialtyRepositoryImpl implements SpecialtyRepository {
    private final Statement statement = JdbcConnection.getStatement();


    public JdbcSpecialtyRepositoryImpl() {
    }

    public Specialty create(String name){
        Long id = null;
        try {
            statement.executeUpdate(String.format(SPECIALTY_CREATE, name, "ACTIVE"));
            id = getCreatedSkillId();
        } catch (SQLIntegrityConstraintViolationException ex) {
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return read(id);
    }

    @Override
    public Specialty read(Long id) {
        return getALl().stream()
                .filter(specialty -> id.equals(specialty.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Specialty update(String name, long id) {
        try {
            statement.executeUpdate(String.format(SPECIALTY_UPDATE, name, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return read(id);
    }

    @Override
    public boolean delete(Long id) {
        boolean result = false;
        try {
            if (statement.executeUpdate(String.format(SPECIALTY_DELETE, "DELETED", id)) > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Specialty> getALl() {
        List<Specialty> specialties = new ArrayList<>();

        try (ResultSet resultSet = statement.executeQuery(SPECIALTY_GET_ALL)) {
            while (resultSet.next()) {
                Specialty specialty = new Specialty();
                specialty.setId(resultSet.getLong("id"));
                specialty.setName(resultSet.getString("name"));
                specialty.setStatus(Status.valueOf(resultSet.getString("status")));
                specialties.add(specialty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialties;
    }


    private Long getCreatedSkillId() {
        Long id = null;
        try (ResultSet resultSet = statement.executeQuery(SPECIALTY_GET_MAX)) {
            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
