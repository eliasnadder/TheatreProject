public class User {
  private String username;
  private AccountDetails accountDetails;

  // Constructor
  public User() {}

  public User(String username, AccountDetails accountDetails) {
    this.username = username;
    this.accountDetails = accountDetails;
  }

  // Getters and setters
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public AccountDetails getAccountDetails() {
    return accountDetails;
  }

  public void setAccountDetails(AccountDetails accountDetails) {
    this.accountDetails = accountDetails;
  }
}
