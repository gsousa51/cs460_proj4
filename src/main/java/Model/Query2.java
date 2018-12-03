package Model;

/*
    Class: Query2
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used as a "Bean" for the values the user wants to use in Query2 as
    stated in the project specification.

    The only methods in this class are Getters/Setters used primarily by .html forms to store values
    or display values, or this classes corresponding Controller found in the Controller package.
 */
public class Query2 {
    private String dept_name;

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
}
