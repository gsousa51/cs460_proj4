package Model;

/*
    Class: Query4
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used as a "Bean" for the values the user wants to use in Query4 as
    stated in the project specification.

    The only methods in this class are Getters/Setters used primarily by .html forms to store values
    or display values, or this classes corresponding Controller found in the Controller package.
 */
public class Query4 {
    private long EID;

    public long getEID() {
        return EID;
    }

    public void setEID(long EID) {
        this.EID = EID;
    }
}
