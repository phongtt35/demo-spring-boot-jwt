package poly.ptpm.demojwt.dto;

public class VerifyResult {

    private String username;
    private Boolean isValid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean isValid) {
        this.isValid = isValid;
    }
}
