package project.model;

public class Consumable extends ItemCategory{

  protected int itemLevel;
  protected String description;

  public Consumable(int itemCategoryID, String itemName, int maxStackSize, Double vendorPrice,
    int itemLevel, String description) {
    super(itemCategoryID, itemName, maxStackSize, vendorPrice);
    this.itemLevel = itemLevel;
    this.description = description;
  }

  public Consumable(String itemName, int maxStackSize, Double vendorPrice, int itemLevel,
    String description) {
    super(itemName, maxStackSize, vendorPrice);
    this.itemLevel = itemLevel;
    this.description = description;
  }
  

  public int getItemLevel() {
    return itemLevel;
  }

  public void setItemLevel(int itemLevel) {
    this.itemLevel = itemLevel;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
