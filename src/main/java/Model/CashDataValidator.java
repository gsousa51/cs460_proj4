package Model;

public class CashDataValidator {
    private CashData cashData;
    private boolean valid;
    private String message;

    public CashDataValidator(CashData cashData) {
        this.cashData = cashData;
        this.valid = false;
        this.message = "";
    }

    public CashData getCashData() {
        return cashData;
    }

    public void setCashData(CashData cashData) {
        this.cashData = cashData;
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

    public void validateAddCashData() {
        if (cashData.getEID() == 0 || cashData.getXactID() == 0 || cashData.getPID() == 0 ||
                cashData.getStatus() == null || cashData.getAmount() <= 0 || cashData.getDueDate() == null) {
            message = "Error: a required field was left blank or zero.\n";
            return;
        }
        if (!cashData.getStatus().equals("paid") && !cashData.getStatus().equals("unpaid") &&
                !cashData.getStatus().equals("late")) {
            message = "Error: status must be 'paid', 'unpaid', or 'late'.\n";
            return;
        }
        valid = true;
    }

    public void validateUpdateCashData() {
        if (cashData.getEID() == 0 || cashData.getXactID() == 0 || cashData.getStatus() == null ||
                cashData.getAmount() <= 0 || cashData.getDueDate() == null) {
            message = "Error: a required field was left blank or zero.\n";
            return;
        }
        if (!cashData.getStatus().equals("paid") && !cashData.getStatus().equals("unpaid") && !cashData.getStatus()
                .equals("late")) {
            message = "Error: status must be 'paid', 'unpaid', or 'late'.\n";
            return;
        }
        valid = true;
    }
}
