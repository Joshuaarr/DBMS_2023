package project.model;

public abstract class Equipment extends ItemCategory{

  protected int itemLevel;
  protected int requiredLevel;

  public Equipment(int itemCategoryID, String itemName, int maxStackSize, Double vendorPrice,
    int itemLevel, int requiredLevel) {
    super(itemCategoryID, itemName, maxStackSize, vendorPrice);
    this.itemLevel = itemLevel;
    this.requiredLevel = requiredLevel;
  }

  protected Equipment(String itemName, int maxStackSize, Double vendorPrice, int itemLevel,
    int requiredLevel) {
    super(itemName, maxStackSize, vendorPrice);
    this.itemLevel = itemLevel;
    this.requiredLevel = requiredLevel;
  }
  
  public Equipment(int itemCategoryID, int itemLevel, int requiredLevel) {
      super(itemCategoryID);
      this.itemLevel = itemLevel;
      this.requiredLevel = requiredLevel;
  }
  
  public Equipment(int itemCategoryID) {
      super(itemCategoryID);
  }

  public int getItemLevel() {
    return itemLevel;
  }

  public void setItemLevel(int itemLevel) {
    this.itemLevel = itemLevel;
  }

  public int getRequiredLevel() {
    return requiredLevel;
  }

  public void setRequiredLevel(int requiredLevel) {
    this.requiredLevel = requiredLevel;
  }
}
