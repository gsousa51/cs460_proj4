package Controller;

/*
    Class: Query1Controller
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This is the Controller that we're using to execute Query 1 as defined by the project spec.
    When the user tries to access the page to submit data and when they actually submit the data,
    we'll be using this class to coordinate the behavior of the website.

    This class also requires the project to have access to Java.SpringFramework so we can use its
    annotations/methods.
 */
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Query1;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class Query1Controller {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/query1")
    public String query1Form(Model model) {
        model.addAttribute("query1", new Query1());
        return "query1";
    }

    /*
        This method is called after the user hits submit after inputting the Name/DOB we'd like to use
        for Query 1. These values are stored in the parameter Query1 query, which is a Bean object whose fields
        are the data input by the user.
        We'll take these values and execute the query, using a RowMapper to create a list of values
        that are the result from the query. We'll add this list as an attribute to the model, so we can
        access it in /resultQuery1.

        Return value : "resultQuery1" so we can display this webpage to the user.
     */
    @PostMapping("/query1")
    public String query1Submit(Model model, @ModelAttribute Query1 query) {
        List<String> allNames = this.jdbcTemplate.query(
                "SELECT p.pid, p.f_name, p.l_name, p.gender, p.dob, a.appt_date, a.reason, a.treatment, d.f_name AS " +
                        "d_f, d.l_name AS d_l\n" +
                        "FROM aswindle.patient p, aswindle.appointment a, aswindle.doctor d\n" +
                        "WHERE p.l_name = ?\n" +
                        "AND p.f_name = ?\n" +
                        "AND p.dob = ?\n" +
                        "AND p.pid = a.pid\n" +
                        "AND a.did = d.did\n" +
                        "AND ROWNUM = 1\n" +
                        "ORDER BY a.appt_date DESC", new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        int pid = rs.getInt("pid");
                        String first_name = rs.getString("f_name");
                        String last_name = rs.getString("l_name");
                        String gender = rs.getString("gender");
                        Date dob = new Date(rs.getLong("dob"));
                        Date appt_date = new Date(rs.getLong("appt_date"));
                        String reason = rs.getString("reason");
                        String treatment = rs.getString("treatment");
                        String d_f = rs.getString("d_f");
                        String d_l = rs.getString("d_l");
                        return (String.format("Patient ID: %d\nName: %s %s\nGender: %s\nDOB: %s\nLast Appointment: %s\n" +
                                        "Reason for appointment: %s\nTreatment: %s\nDoctor: %s %s",
                                pid, first_name, last_name, gender, dob, appt_date, reason, treatment, d_f, d_l));
                    }
                }, query.getlName(), query.getfName(), query.getDOB().getTime());


        model.addAttribute("names", allNames);
        return "resultQuery1";
    }
}
