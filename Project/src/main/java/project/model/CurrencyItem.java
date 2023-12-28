package project.model;

public class CurrencyItem {

  protected Character character;
  protected Currency currency;
  protected int totalAmount;
  protected int weeklyAmount;

  public CurrencyItem(Character character, Currency currency, int totalAmount, int weeklyAmount) {
    this.character = character;
    this.currency = currency;
    this.totalAmount = totalAmount;
    this.weeklyAmount = weeklyAmount;
  }

  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public int getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(int totalAmount) {
    this.totalAmount = totalAmount;
  }

  public int getWeeklyAmount() {
    return weeklyAmount;
  }

  public void setWeeklyAmount(int weeklyAmount) {
    this.weeklyAmount = weeklyAmount;
  }
}
