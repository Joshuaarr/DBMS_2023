package project.model;

public abstract class ItemCategory {

  protected int itemCategoryID;
  protected String itemName;
  protected int maxStackSize;
  protected Double vendorPrice;

  public ItemCategory(int itemCategoryID, String itemName, int maxStackSize, Double vendorPrice) {
    this.itemCategoryID = itemCategoryID;
    this.itemName = itemName;
    this.maxStackSize = maxStackSize;
    this.vendorPrice = vendorPrice;
  }

  protected ItemCategory(String itemName, int maxStackSize, Double vendorPrice) {
    this.itemName = itemName;
    this.maxStackSize = maxStackSize;
    this.vendorPrice = vendorPrice;
  }
  
  public ItemCategory(int itemCategoryID) {
	    this.itemCategoryID = itemCategoryID;
	  }

  public int getItemCategoryID() {
    return itemCategoryID;
  }

  public void setItemCategoryID(int itemCategoryID) {
    this.itemCategoryID = itemCategoryID;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public int getMaxStackSize() {
    return maxStackSize;
  }

  public void setMaxStackSize(int maxStackSize) {
    this.maxStackSize = maxStackSize;
  }

  public Double getVendorPrice() {
    return vendorPrice;
  }

  public void setVendorPrice(Double vendorPrice) {
    this.vendorPrice = vendorPrice;
  }
}
