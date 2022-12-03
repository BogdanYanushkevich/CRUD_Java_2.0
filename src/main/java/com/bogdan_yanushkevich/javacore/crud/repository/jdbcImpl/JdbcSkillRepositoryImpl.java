package com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl;

import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.model.Status;
import com.bogdan_yanushkevich.javacore.crud.repository.SkillRepository;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

import static com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.CommonSQLQueries.*;


public class JdbcSkillRepositoryImpl implements SkillRepository {

    public JdbcSkillRepositoryImpl() {
    }

    public Skill create(Skill skill) {

        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatementWithKeys(SKILL_CREATE)) {
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setString(2, Status.ACTIVE.toString());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            skill.setId(getId(resultSet));
            skill.setStatus(Status.ACTIVE);
            return skill;
        } catch (SQLIntegrityConstraintViolationException ex) {
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Skill read(Long id) {
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(SKILL_GET_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return buildSkillObject(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Skill update(Skill skill) {
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(SKILL_UPDATE)) {
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setLong(2, skill.getId());
            preparedStatement.executeUpdate();
            return skill;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(SKILL_DELETE)) {
            preparedStatement.setString(1, Status.DELETED.toString());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Skill> getALl() {
        List<Skill> skills = new ArrayList<>();

        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(SKILL_GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setId(resultSet.getLong("id"));
                skill.setName(resultSet.getString("name"));
                skill.setStatus(Status.valueOf(resultSet.getString("status")));
                skills.add(skill);
            }
            return skills;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Skill buildSkillObject(ResultSet resultSet) {
        Skill skill = new Skill();
        try {
            if (resultSet.next()) {
                skill.setId(resultSet.getLong("id"));
                skill.setName(resultSet.getString("name"));
                skill.setStatus(Status.valueOf(resultSet.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return skill;
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
