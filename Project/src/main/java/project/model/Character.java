package project.model;

public class Character {
  protected int characterID;
  protected Account account;
  protected String firstName;
  protected String lastName;

  public Character(int characterID, Account account, String firstName, String lastName) {
    this.characterID = characterID;
    this.account = account;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Character(Account account, String firstName, String lastName) {
    this.account = account;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public int getCharacterID() {
    return characterID;
  }

  public void setCharacterID(int characterID) {
    this.characterID = characterID;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

}
