package com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl;

public class CommonSQLQueries {
    // For Skills
    public static final String SKILL_GET_MAX = "SELECT * " +
            "FROM crud.skills " +
            "WHERE id = (SELECT MAX(id) " +
            "FROM crud.skills) ;";
    public static final String SKILL_CREATE =
            "INSERT INTO crud.skills (name, status) " +
                    "VALUES( '%s' , '%s' ) ;";

    public static final String SKILL_UPDATE =
            "UPDATE crud.skills " +
                    "SET name = '%s'" +
                    "WHERE id = %d ;";

    public static final String SKILL_GET_ALL =
            "SELECT * " +
                    "FROM crud.skills ;";

    public static final String SKILL_DELETE =
            "UPDATE crud.skills " +
                    "SET status = '%s'" +
                    "WHERE id = %d ;";


    // For Specialties
    public static final String SPECIALTY_GET_MAX = "SELECT * " +
            "FROM crud.specialties " +
            "WHERE id = (SELECT MAX(id) " +
            "FROM crud.specialties) ;";
    public static final String SPECIALTY_GET_BY_ID =
            "SELECT * " +
                    "FROM crud.specialties  " +
                    "WHERE id = %d";
    public static final String SPECIALTY_CREATE =
            "INSERT INTO crud.specialties (name, status) " +
                    "VALUES( '%s' , '%s' ) ;";

    public static final String SPECIALTY_UPDATE =
            "UPDATE crud.specialties " +
                    "SET name = '%s'" +
                    "WHERE id = %d ;";

    public static final String SPECIALTY_GET_ALL =
            "SELECT * " +
                    "FROM crud.specialties ;";

    public static final String SPECIALTY_DELETE =
            "UPDATE crud.specialties " +
                    "SET status = '%s'" +
                    "WHERE id = %d ;";

    // For Developer


    public static final String DEVELOPER_CREATE =
            "INSERT INTO crud.developers (first_name, last_name, specialty_id, status) " +
                    "VALUES('%s' , '%s', %d, '%s') ;";

    public static final String DEVELOPER_ADD_SKILLS =
            "INSERT INTO crud.skills_attribute  (developer_id, skill_id) " +
                    "VALUES( %d , %d) ;";

    public static final String DEVELOPER_GET_SKILLS =
            "SELECT * " +
                    "FROM crud.skills s LEFT JOIN skills_attribute sa ON sa.skill_id = s.id WHERE developer_id = %d";
    public static final String DEVELOPER_SKILL_DELETE =
            "DELETE " +
                    "FROM crud.skills_attribute " +
                    "WHERE developer_id = %d ;";
    public static final String DEVELOPER_UPDATE =
            "UPDATE crud.developers " +
                    "SET first_name = '%s' , last_name = '%s'  , specialty_id  = %d " +
                    "WHERE id = %d ;";


    public static final String DEVELOPER_DELETE =
            "UPDATE crud.developers " +
                    "SET status = '%s'" +
                    "WHERE id = %d ;";


    public static final String DEVELOPER_GET_ALL =
            "SELECT * " +
                    "FROM crud.developers ;";

    public static final String DEVELOPER_GET_MAX = "SELECT * " +
            "FROM crud.developers " +
            "WHERE id = (SELECT MAX(id) " +
            "FROM crud.developers) ;";


}
