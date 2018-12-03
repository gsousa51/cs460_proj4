package Controller;
/*
    Class: Query3Controller
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 3
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This is the Controller that we're using to execute Query 3 as defined by  README.md
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

import Model.Query3;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class Query3Controller {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/query3")
    public String query3Form(Model model) {
        model.addAttribute("query3", new Query3());
        return "query3";
    }

    /*
    This method is called after the user hits submit after inputting the "Run Query" button.
    We execute the query, using a RowMapper to create a list of values
    that are the result from the query. We'll add this list as an attribute to the model, so we can
    access it in /resultQuery3.

    Return value : "resultQuery3" so we can display this webpage to the user.
 */
    @PostMapping("/query3")
    public String query3Submit(Model model, @ModelAttribute Query3 query) {
        List<String> allNames = this.jdbcTemplate.query(
                "SELECT pid, f_name, l_name, days, room, SUM(amount) AS total \n" +
                        "FROM (SELECT p.pid AS pid, p.f_name AS f_name, p.l_name AS l_name, a.exp_discharge - a" +
                        ".admission AS days, a.room AS room, cd.amount AS amount\n" +
                        "\t\tFROM aswindle.patient p, aswindle.appointment a, aswindle.cash_data cd\n" +
                        "\t\tWHERE p.pid = a.pid\n" +
                        "\t\tAND p.pid = cd.pid \n" +
                        "\t\tAND a.act_discharge is NULL\n" +
                        "\t\tAND (a.exp_discharge - a.admission) >= 432000000\n" +
                        "\t\tAND cd.paid is NULL)\n" +
                        "GROUP BY pid, f_name, l_name, days, room", new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        int pid = rs.getInt("pid");
                        String first_name = rs.getString("f_name");
                        String last_name = rs.getString("l_name");
                        // Days hospitalized is returned as milliseconds, so divide by the # of milliseconds in a day
                        long days = rs.getLong("days")/86400000;
                        int room = rs.getInt("room");
                        int total = rs.getInt("total");
                        return (String.format("PID: %d Name: %s %s, Expected days in: %d, Room: %d, Total Unpaid: $%d",
                                pid, first_name, last_name, days, room, total));
                    }});

        model.addAttribute("names", allNames);
        return "resultQuery3";
    }
}
