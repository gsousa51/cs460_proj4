package Validator;

import Model.Doctor;
import Model.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DoctorValidator {

    private Doctor doctor;
    private boolean valid;

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public DoctorValidator(Doctor doctor){
        this.doctor = doctor;
        this.valid = false;
        validate();
        testQuery();
    }

    private void testQuery(){

    }
    private void validate(){

    }
}
