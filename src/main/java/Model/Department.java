package Model;

/*
    Class: Department
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used as a "Bean" for the values the user wants to use in UPDATE/INSERT
    queries on the Department table.
    NOTE: Please consult the README.md for what we're doing in the setters for Dates as well as the setters
    for String whose suffix is s_.

    Each field simply corresponds to a column in Department or is a flag/string rep for that column.

    The only methods in this class are Getters/Setters used primarily by .html forms to store values
    or display values, or this classes corresponding Controller found in the Controller package.
 */
public class Department {
    private long ID;
    private String name;
    private long office;
    private String s_office;
    private boolean emptyOffice;

    private String buildingName;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOffice() {
        return office;
    }

    public void setOffice(long office) {
        this.office = office;
    }

    public void setS_office(String s_office){
        if(s_office.equals("")){
            System.err.println("Setting office to empty");
            this.emptyOffice = true;
        }
        else{
            this.office = Long.parseLong(s_office);
        }
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getS_office() {
        return s_office;
    }

    public boolean isEmptyOffice() {
        return emptyOffice;
    }

    public void setEmptyOffice(boolean emptyOffice) {
        this.emptyOffice = emptyOffice;
    }
}
