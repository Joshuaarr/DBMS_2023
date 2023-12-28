package project.model;

public class Account {

  protected int accountID;
  protected String name;
  protected String email;
  protected boolean isActive;

  public Account(int accountID, String name, String email, boolean isActive) {
    this.accountID = accountID;
    this.name = name;
    this.email = email;
    this.isActive = isActive;
  }

  public Account(String name, String email, boolean isActive) {
    this.name = name;
    this.email = email;
    this.isActive = isActive;
  }

  public int getAccountID() {
    return accountID;
  }

  public void setAccountID(int accountID) {
    this.accountID = accountID;
  }

  public String getName() {
    return name;
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

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }
}
