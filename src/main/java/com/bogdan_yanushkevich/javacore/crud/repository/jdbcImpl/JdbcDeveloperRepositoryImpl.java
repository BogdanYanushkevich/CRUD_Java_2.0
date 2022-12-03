package com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl;

import com.bogdan_yanushkevich.javacore.crud.model.Developer;
import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.model.Specialty;
import com.bogdan_yanushkevich.javacore.crud.model.Status;
import com.bogdan_yanushkevich.javacore.crud.repository.DeveloperRepository;

import static com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.CommonSQLQueries.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;


public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {
    public JdbcDeveloperRepositoryImpl() {
    }


    @Override
    public Developer create(Developer developer) {
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatementWithKeys(DEVELOPER_CREATE)) {
            preparedStatement.setString(1, developer.getName());
            preparedStatement.setString(2, developer.getLastName());
            preparedStatement.setLong(3, developer.getSpecialty().getId());
            preparedStatement.setString(4, Status.ACTIVE.toString());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            developer.setId(getId(resultSet));
            developer.setStatus(Status.ACTIVE);
            setDependencies(developer.getSkills(), developer.getId());
            return developer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Developer read(Long id) {
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(DEVELOPER_GET_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return buildDeveloperObject(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public Developer update(Developer developer) {
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(DEVELOPER_UPDATE)) {
            preparedStatement.setString(1, developer.getName());
            preparedStatement.setString(2, developer.getLastName());
            preparedStatement.setLong(3, developer.getSpecialty().getId());
            preparedStatement.setLong(4, developer.getId());
            preparedStatement.executeUpdate();
            deleteDependencies(developer.getId());
            setDependencies(developer.getSkills(), developer.getId());
            return developer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(Long id) {
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(DEVELOPER_DELETE)) {
            preparedStatement.setString(1, Status.DELETED.toString());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Developer> getALl() {
        List<Developer> developers = new ArrayList<>();
        Developer developer;
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(DEVELOPER_GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developer = buildDeveloperObject(resultSet);
                developers.add(developer);
            }
            return developers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Skill> getDeveloperSkillsList(long id) {
        List<Skill> skills = new ArrayList<>();
        Skill skill;
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(DEVELOPER_GET_SKILLS)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                skill = buildSkillObject(resultSet);
                skills.add(skill);
            }
            return skills;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void setDependencies(List<Skill> skillList, Long developerId) {
        for (Skill s : skillList) {
            try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(DEVELOPER_ADD_SKILLS)) {
                preparedStatement.setLong(1, developerId);
                preparedStatement.setLong(2, s.getId());
                preparedStatement.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException ex) {
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Specialty getSpecialty(long id) {
        Specialty specialty = new Specialty();
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(SPECIALTY_GET_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                specialty.setId(resultSet.getLong("id"));
                specialty.setName(resultSet.getString("name"));
                specialty.setStatus(Status.ACTIVE);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return specialty;
    }

    private Developer buildDeveloperObject(ResultSet resultSet) {
        Developer developer = new Developer();
        try {
            developer.setId(resultSet.getLong("id"));
            developer.setName(resultSet.getString("first_name"));
            developer.setLastName(resultSet.getString("last_name"));
            developer.setStatus(Status.valueOf(resultSet.getString("status")));
            developer.setSpecialty(getSpecialty(resultSet.getInt("specialty_id")));
            developer.addSkills(getDeveloperSkillsList(developer.getId()));
            return developer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Skill buildSkillObject(ResultSet resultSet) {
        Skill skill = new Skill();
        try {
            skill.setId(resultSet.getLong("id"));
            skill.setName(resultSet.getString("name"));
            skill.setStatus(Status.valueOf(resultSet.getString("status")));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return skill;
    }

    private void deleteDependencies(Long id) {
        try (PreparedStatement preparedStatement = JdbcConnection.getPreparedStatement(DEVELOPER_SKILL_DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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