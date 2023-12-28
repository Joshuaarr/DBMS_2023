package project.model;

public class Miscellaneous extends ItemCategory {

  protected String description;

  public Miscellaneous(int itemCategoryID, String itemName, int maxStackSize, Double vendorPrice,
    String description) {
    super(itemCategoryID, itemName, maxStackSize, vendorPrice);
    this.description = description;
  }

  public Miscellaneous(String itemName, int maxStackSize, Double vendorPrice, String description) {
    super(itemName, maxStackSize, vendorPrice);
    this.description = description;
  }
  

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
