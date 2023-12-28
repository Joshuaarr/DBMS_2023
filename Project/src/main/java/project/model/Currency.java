package project.model;

public class Currency {

  protected String currencyName;
  protected int totalCapAmount;
  protected int weeklyCapAmount;
  protected boolean isWeeklyCap;
  protected boolean isDiscontinued;

  public Currency(String currencyName, int totalCapAmount, int weeklyCapAmount, boolean isWeeklyCap,
    boolean isDiscontinued) {
    this.currencyName = currencyName;
    this.totalCapAmount = totalCapAmount;
    this.weeklyCapAmount = weeklyCapAmount;
    this.isWeeklyCap = isWeeklyCap;
    this.isDiscontinued = isDiscontinued;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public int getTotalCapAmount() {
    return totalCapAmount;
  }

  public void setTotalCapAmount(int totalCapAmount) {
    this.totalCapAmount = totalCapAmount;
  }

  public int getWeeklyCapAmount() {
    return weeklyCapAmount;
  }

  public void setWeeklyCapAmount(int weeklyCapAmount) {
    this.weeklyCapAmount = weeklyCapAmount;
  }

  public boolean isWeeklyCap() {
    return isWeeklyCap;
  }

  public void setWeeklyCap(boolean weeklyCap) {
    this.isWeeklyCap = weeklyCap;
  }

  public boolean isDiscontinued() {
    return isDiscontinued;
  }

  public void setDiscontinued(boolean discontinued) {
    isDiscontinued = discontinued;
  }
}
