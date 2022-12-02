package com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl;

import com.bogdan_yanushkevich.javacore.crud.model.Developer;
import com.bogdan_yanushkevich.javacore.crud.model.Skill;
import com.bogdan_yanushkevich.javacore.crud.model.Specialty;
import com.bogdan_yanushkevich.javacore.crud.model.Status;
import com.bogdan_yanushkevich.javacore.crud.repository.DeveloperRepository;

import static com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl.CommonSQLQueries.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {
    public JdbcDeveloperRepositoryImpl() {
    }


    @Override
    public Developer create(String firstname, String lastname, List<Skill> skills, Specialty specialty) {
        Long id;
        try (Statement statement = JdbcConnection.getStatement()) {
            statement.executeUpdate(String.format(DEVELOPER_CREATE, firstname, lastname, specialty.getId(), "ACTIVE"));
            id = getCreatedDeveloperId();
            setDependencies(skills, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return read(id);
    }


    @Override
    public Developer read(Long id) {
        return getALl().stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Developer update(Long id, String firstname, String lastname, List<Skill> skills, Specialty specialty) {
        try (Statement statement = JdbcConnection.getStatement()) {
            statement.executeUpdate(String.format(DEVELOPER_UPDATE, firstname, lastname, specialty.getId(), id));
            statement.executeUpdate(String.format(DEVELOPER_SKILL_DELETE, id));
            setDependencies(skills, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return read(id);
    }

    public boolean delete(Long id) {
        try (Statement statement = JdbcConnection.getStatement()) {
            statement.executeUpdate(String.format(DEVELOPER_DELETE, "DELETED", id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Developer> getALl() {
        List<Developer> developers = new ArrayList<>();
        try (Statement statement = JdbcConnection.getStatement()) {
            ResultSet resultSet = statement.executeQuery(DEVELOPER_GET_ALL);
            while (resultSet.next()) {
                Developer developer = buildDeveloperObject(resultSet);
                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return developers;
    }

    private List<Skill> getDeveloperSkillsList(long id) {
        List<Skill> skills = new ArrayList<>();
        try (Statement statement = JdbcConnection.getStatement()) {
            ResultSet resultSet1 = statement.executeQuery(String.format(DEVELOPER_GET_SKILLS, id));
            while (resultSet1.next()) {
                Skill skill = buildSkillObject(resultSet1);
                skills.add(skill);
            }
            resultSet1.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return skills;
    }


    private void setDependencies(List<Skill> skillList, Long developerId) {
        for (Skill s : skillList) {
            try (Statement statement = JdbcConnection.getStatement()) {
                statement.executeUpdate(String.format(DEVELOPER_ADD_SKILLS, developerId, s.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Specialty getSpecialty(long id) {
        Specialty specialty = new Specialty();
        try (Statement statement = JdbcConnection.getStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format(SPECIALTY_GET_BY_ID, id));
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

    private Long getCreatedDeveloperId() {
        Long id = null;
        try (Statement statement = JdbcConnection.getStatement()) {
            ResultSet resultSet = statement.executeQuery(DEVELOPER_GET_MAX);
            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
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
}