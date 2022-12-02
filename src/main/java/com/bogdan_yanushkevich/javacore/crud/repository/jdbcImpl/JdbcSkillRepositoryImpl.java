package com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl;

import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.model.Status;
import com.bogdan_yanushkevich.javacore.crud.repository.SkillRepository;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

import static com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.CommonSQLQueries.*;
import static com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.CommonSQLQueries.SKILL_GET_MAX;


public class JdbcSkillRepositoryImpl implements SkillRepository {

    private final Statement statement = JdbcConnection.getStatement();


    public JdbcSkillRepositoryImpl() {
    }

    public Skill create(String name) {
        Long id = null;
        try {
            statement.executeUpdate(String.format(SKILL_CREATE, name, "ACTIVE"));
            id = getCreatedSkillId();
        } catch (SQLIntegrityConstraintViolationException ex) {
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return read(id);
    }

    @Override
    public Skill read(Long id) {
        return getALl().stream()
                .filter(skill -> id.equals(skill.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Skill update(String name, long id) {
        try {
            statement.executeUpdate(String.format(SKILL_UPDATE, name, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return read(id);
    }

    @Override
    public boolean delete(Long id) {
        boolean result = false;
        try {
            if (statement.executeUpdate(String.format(SKILL_DELETE, "DELETED", id)) > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Skill> getALl() {
        List<Skill> skills = new ArrayList<>();

        try (ResultSet resultSet = statement.executeQuery(SKILL_GET_ALL)) {
            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setId(resultSet.getLong("id"));
                skill.setName(resultSet.getString("name"));
                skill.setStatus(Status.valueOf(resultSet.getString("status")));
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }


    private Long getCreatedSkillId() {
        Long id = null;
        try (ResultSet resultSet = statement.executeQuery(SKILL_GET_MAX)) {
            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
