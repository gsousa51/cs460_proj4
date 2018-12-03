package Controller;
/*
    Class: Query4Controller
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This is the Controller that we're using to execute Query 4 as defined by  README.md
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

import Model.Query4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class Query4Controller {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/query4")
    public String query4Form(Model model) {
        model.addAttribute("query4", new Query4());
        return "query4";
    }

    /*
    This method is called after the user hits submit after inputting the Employee ID we'd like to use
    for Query 4. These values are stored in the parameter Query4 query, which is a Bean object whose fields
    are the data input by the user.
    We'll take these values and execute the query, using a RowMapper to create a list of values
    that are the result from the query. We'll add this list as an attribute to the model, so we can
    access it in /resultQuery4.

    Return value : "resultQuery4" so we can display this webpage to the user.
 */
    @PostMapping("/query4")
    public String query4Submit(Model model, @ModelAttribute Query4 query) {
        List<String> allNames = this.jdbcTemplate.query(
                "SELECT DISTINCT p.f_name, p.l_name, ph.medicine, pharm.f_name AS pharmf, pharm.l_name AS pharml\n" +
                        "FROM aswindle.patient p, aswindle.pharm_data ph, aswindle.pharmacist pharm, aswindle.recept_data rd\n" +
                        "WHERE rd.eid = ?\n" +
                        "AND p.pid = rd.pid\n" +
                        "AND ph.pid = p.pid\n" +
                        "AND pharm.phid = ph.phid\n" +
                        "ORDER BY p.l_name", new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String first_name = rs.getString("f_name");
                        String last_name = rs.getString("l_name");
                        String medicine = rs.getString("medicine");
                        String pharmf = rs.getString("pharmf");
                        String pharml = rs.getString("pharml");
                        return (String.format("Name: %s %s, Medicine: %s, Prescribed by: %s %s",
                                first_name, last_name, medicine, pharmf, pharml));
                    }
                }, query.getEID());


        model.addAttribute("names", allNames);
        return "resultQuery4";
    }
}
