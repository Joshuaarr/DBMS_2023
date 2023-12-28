package project.model;

public class Gear extends Equipment {

  protected int defenceRating;
  protected int magicDefenceRating;

  public Gear(int itemCategoryID, String itemName, int maxStackSize, Double vendorPrice,
    int itemLevel, int requiredLevel, int defenceRating, int magicDefenceRating) {
    super(itemCategoryID, itemName, maxStackSize, vendorPrice, itemLevel, requiredLevel);
    this.defenceRating = defenceRating;
    this.magicDefenceRating = magicDefenceRating;
  }

  public Gear(String itemName, int maxStackSize, Double vendorPrice, int itemLevel,
    int requiredLevel,
    int defenceRating, int magicDefenceRating) {
    super(itemName, maxStackSize, vendorPrice, itemLevel, requiredLevel);
    this.defenceRating = defenceRating;
    this.magicDefenceRating = magicDefenceRating;
  }
  

  public int getDefenceRating() {
    return defenceRating;
  }

  public void setDefenceRating(int defenceRating) {
    this.defenceRating = defenceRating;
  }

  public int getMagicDefenceRating() {
    return magicDefenceRating;
  }

  public void setMagicDefenceRating(int magicDefenceRating) {
    this.magicDefenceRating = magicDefenceRating;
  }
  
  
}
