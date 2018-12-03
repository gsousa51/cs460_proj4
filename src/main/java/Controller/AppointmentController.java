package Controller;

/*
    Class: AppointmentController
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This is the Controller that we're using for INSERT/UPDATE pages that we're
    using manipulate the Appointment table. This class uses AppointmentValidator which can be found
    in the Validator package. We use it to validate the data the user is attempting to use to
    manipulate the Appointment table.
    This class also requires the project to have access to Java.SpringFramework so we can use its
    annotations/methods.
 */

import Validator.AppointmentValidator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Appointment;

@Controller
public class AppointmentController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addAppointment")
    public String addAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "addAppointment";
    }

    @GetMapping("/updateAppointment")
    public String updateAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "updateAppointment";
    }

    /*
        Method is called when user hits submit at addAppointment, this endpoint will be hit.
        The parameters given are the model we're working with and an Appointment object
        which serves as the Bean for the user's input.

        We'll validate that this is a valid insertStatement and attempt to execute the query.

        If any errors occur during the insertion OR if this was determined to be an invalid INSERT
        by our validator, then we'll send the user to a page that tells them the query was invalid.

        Else we send the user to /resultAppointment.

        Return value: The URL to show.
     */
    @PostMapping("/addAppointment")
    public String appointmentAdd(Model model, @ModelAttribute Appointment appointment) {
        AppointmentValidator appointmentValidator = new AppointmentValidator(appointment);
        model.addAttribute(appointmentValidator);

        if(appointmentValidator.validateInsertDates()) {
            try {
                this.jdbcTemplate.update(appointmentValidator.getInsertMessage());
            }catch(DataAccessException d){
                System.err.println("*****CAUGHT ERROR*****");
                d.printStackTrace();
                return "resultError";
            }
        }
        else{
            System.err.println("Invalid Dates");
            return "resultError";
        }
        return "resultAppointment";
    }
    /*
        Method is called when user hits submit at updateAppointment, this endpoint will be hit.
        The parameters given are the model we're working with and an Appointment object
        which serves as the Bean for the user's input.

        We'll validate that this is a valid insertStatement and attempt to execute the query.

        If any errors occur during the insertion OR if this was determined to be an invalid UPDATE
        by our validator, then we'll send the user to a page that tells them the query was invalid.

        Else we send the user to /resultAppointment.

        Return value: The URL to show.
     */
    @PostMapping("/updateAppointment")
    public String appointmentUpdate(Model model, @ModelAttribute Appointment appointment){
        AppointmentValidator appointmentValidator = new AppointmentValidator(appointment);
        model.addAttribute("validation", appointmentValidator);

        if(appointmentValidator.isValidUpdate()){
            long apptDate = this.getDate("Appt_Date" , appointment.getAID());
            long admission = this.getDate("Admission" , appointment.getAID());
            long expDischarge = this.getDate("Exp_Discharge" , appointment.getAID());
            long actDischarge = this.getDate("Act_Discharge" , appointment.getAID());
            if(appointmentValidator.validateUpdate(apptDate, admission, actDischarge, expDischarge)) {
                try {
                    this.jdbcTemplate.update(appointmentValidator.getUpdateMessage());
                } catch (DataAccessException d) {
                    //TODO: Send user to an error page.
                    System.err.println("****CAUGHT ERROR****");
                    d.printStackTrace();
                    return "resultError";
                }
                System.err.println("executed update query");
            }
            else{
                return "resultError";
            }
        }
        else{
            System.err.println("Invalid update message");
            return "resultError";
        }
        return "resultAppointment";
    }

    /*
        Method is used to return the values stored for whatever column name is sent in
        whose AID == the AID sent in.
        Though this works for any numeric column, we'll only use it for dates.

        Method stores the value into a String so we can test if the value sent in was null or not
        without a NumberFormatException.

        Return: If the returned value from our query was null, we return -1, else we return the value.

     */
    private long getDate(String colName, long AID){
        String query = "SELECT " + colName + " FROM aswindle.appointment WHERE AID = ?";
        String result = this.jdbcTemplate.queryForObject(
                query, new Object[] {AID}, String.class);
        System.out.println(colName + ": " + result);
        if(result == null){
            System.err.println("VALUE WAS NULL");
            return -1;
        }
        else{
            return Long.parseLong(result);
        }
    }
}
