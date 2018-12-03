package Controller;
/*
    Class: Query2Controller
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This is the Controller that we're using to execute Query 2 as defined by the project spec.
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

import Model.Query2;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class Query2Controller {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/query2")
    public String query2Form(Model model) {
        model.addAttribute("query2", new Query2());
        return "query2";
    }
    /*
        This method is called after the user hits submit after inputting the Department name we'd like to use
        for Query 2. These values are stored in the parameter Query2 query, which is a Bean object whose fields
        are the data input by the user.
        We'll take these values and execute the query, using a RowMapper to create a list of values
        that are the result from the query. We'll add this list as an attribute to the model, so we can
        access it in /resultQuery2.

        Return value : "resultQuery2" so we can display this webpage to the user.
     */
    @PostMapping("/query2")
    public String query2Submit(Model model, @ModelAttribute Query2 query) {
        List<String> allNames = this.jdbcTemplate.query(
                "SELECT d.f_name, d.l_name, d.office, b.building_name\n" +
                        "FROM aswindle.doctor d, aswindle.department dep, aswindle.building b, aswindle.room r\n" +
                        "WHERE dep.dept_name = ?\n" +
                        "AND d.dept_id = dep.dept_id\n" +
                        "AND d.office = r.room_id\n" +
                        "AND r.building_name = b.building_name", new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String first_name = rs.getString("f_name");
                        String last_name = rs.getString("l_name");
                        int office = rs.getInt("office");
                        String building_name = rs.getString("building_name");
                        return (String.format("Name: %s %s, Building %s Room %d", first_name, last_name, building_name, office));
                    }
                }, query.getDept_name());


        model.addAttribute("names", allNames);
        return "resultQuery2";
    }
}
