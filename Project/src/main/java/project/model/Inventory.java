package project.model;

public class Inventory {

  protected Character character;
  protected int slotID;
  protected ItemCategory itemCategory;
  protected int stackSize;

  public Inventory(Character character, int slotID, ItemCategory itemCategory, int stackSize) {
    this.character = character;
    this.slotID = slotID;
    this.itemCategory = itemCategory;
    this.stackSize = stackSize;
  }

  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }

  public int getSlotID() {
    return slotID;
  }

  public void setSlotID(int slotID) {
    this.slotID = slotID;
  }

  public ItemCategory getItemCategory() {
    return itemCategory;
  }

  public void setItemCategory(ItemCategory itemCategory) {
    this.itemCategory = itemCategory;
  }

  public int getStackSize() {
    return stackSize;
  }

  public void setStackSize(int stackSize) {
    this.stackSize = stackSize;
  }
}
