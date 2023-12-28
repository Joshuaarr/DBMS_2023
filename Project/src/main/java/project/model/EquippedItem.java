package project.model;

public class EquippedItem {

  protected Character character;
  protected SlotName slotName;
  protected int slotID;

  public enum SlotName{
    MainHand, Head, Body, Hands, Feet, OffHand, Earring, Wrist, Ring
  }

  public EquippedItem(Character character, SlotName slotName, int slotID) {
    this.character = character;
    this.slotName = slotName;
    this.slotID = slotID;
  }

  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }

  public SlotName getSlotName() {
    return slotName;
  }

  public void setSlotName(SlotName slotName) {
    this.slotName = slotName;
  }

  public int getSlotID() {
    return slotID;
  }

  public void setSlotID(int slotID) {
    this.slotID = slotID;
  }
}
