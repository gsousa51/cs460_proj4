package Model;

public class Department {
    private int ID;
    private String name;
    private int office;
    private String s_office;
    private boolean emptyOffice;

    //TODO: The spec says we should be able to update the building name...
    private String buildingName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public void setS_office(String s_office){
        if(s_office.equals("")){
            System.err.println("Setting office to empty");
            this.emptyOffice = true;
        }
        else{
            this.office = Integer.parseInt(s_office);
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
