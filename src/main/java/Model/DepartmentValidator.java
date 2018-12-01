package Model;

public class DepartmentValidator {
    private Department department;
    private boolean valid;
    private String message;

    public DepartmentValidator(Department department) {
        this.department = department;
        this.valid = false;
        this.message = "";
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void validateAddDepartment() {
        if (department.getID() == 0 || department.getName().equals("") || department.getOffice() == 0) {
            message = "Error: a required field was left blank.\n";
            return;
        }
        if (department.getOffice() < 0 || department.getOffice() > 172) {
            message = "Error: invalid office number.\n";
            return;
        }
        valid = true;
    }

    public void validateUpdateDepartment() {
        if (department.getName().equals("") || department.getOffice() == 0) {
            message = "Error: a required field was left blank.\n";
            return;
        }
        if (department.getOffice() < 0 || department.getOffice() > 172) {
            message = "Error: invalid office number.\n";
            return;
        }
        valid = true;
    }
}
