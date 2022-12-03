package com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl;

import com.bogdan_yanushkevich.javacore.crud.model.Specialty;
import com.bogdan_yanushkevich.javacore.crud.model.Status;
import com.bogdan_yanushkevich.javacore.crud.repository.SpecialtyRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.CommonSQLQueries.*;
import static com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.CommonSQLQueries.SPECIALTY_GET_ALL;

public class JdbcSpecialtyRepositoryImpl implements SpecialtyRepository {

    public JdbcSpecialtyRepositoryImpl() {
    }

    public Specialty create(Specialty specialty) {

        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatementWithKeys(SPECIALTY_CREATE)) {
            preparedStatement.setString(1, specialty.getName());
            preparedStatement.setString(2, Status.ACTIVE.toString());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            specialty.setId(getId(resultSet));
            specialty.setStatus(Status.ACTIVE);
            return specialty;
        } catch (SQLIntegrityConstraintViolationException ex) {
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Specialty read(Long id) {
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(SPECIALTY_GET_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return buildSpecialtyObject(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Specialty update(Specialty specialty) {
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(SPECIALTY_UPDATE)) {
            preparedStatement.setString(1, specialty.getName());
            preparedStatement.setLong(2, specialty.getId());
            preparedStatement.executeUpdate();
            return specialty;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(SPECIALTY_DELETE)) {
            preparedStatement.setString(1, Status.DELETED.toString());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Specialty> getALl() {
        List<Specialty> specialties = new ArrayList<>();

        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(SPECIALTY_GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Specialty specialty = new Specialty();
                specialty.setId(resultSet.getLong("id"));
                specialty.setName(resultSet.getString("name"));
                specialty.setStatus(Status.valueOf(resultSet.getString("status")));
                specialties.add(specialty);
            }
            return specialties;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Specialty buildSpecialtyObject(ResultSet resultSet) {
        Specialty specialty = new Specialty();
        try {
            if (resultSet.next()) {
                specialty.setId(resultSet.getLong("id"));
                specialty.setName(resultSet.getString("name"));
                specialty.setStatus(Status.valueOf(resultSet.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return specialty;
    }

    private Long getId(ResultSet resultSet) {
        Long id = null;
        try {
            if (resultSet != null && resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
