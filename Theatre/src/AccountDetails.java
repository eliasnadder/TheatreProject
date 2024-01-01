public class AccountDetails {
    private String name;
    private String email;
    private String accountNum;

    public AccountDetails() {
    }

    public AccountDetails(String name, String email, String accountNum) {
        this.name = name;
        this.email = email;
        this.accountNum = accountNum;
    }

    public String getName() {
        return name;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
