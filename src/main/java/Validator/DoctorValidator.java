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
        List<String> allNames = this.jdbcTemplate.query(
                "select * from aswindle.doctor",
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        long first_name = rs.getLong("DOB");
                        String last_name = rs.getString("L_NAME");
                        System.out.println(first_name + " " + last_name);
                        return (first_name + " " + last_name);
                    }
                });
        System.out.println(allNames.toString());
    }
    private void validate(){

    }
}
