package project.model;

public class EquipmentBonus {

  protected Equipment equipment;
  protected AttributeName attributeName;
  protected int attributeBonus;

  public enum AttributeName{
    Strength, Dexterity, Vitality, Mind, Intelligence
  }

  public EquipmentBonus(Equipment equipment, AttributeName attributeName, int attributeBonus) {
    this.equipment = equipment;
    this.attributeName = attributeName;
    this.attributeBonus = attributeBonus;
  }

  public Equipment getEquipment() {
    return equipment;
  }

  public void setEquipment(Equipment equipment) {
    this.equipment = equipment;
  }

  public AttributeName getAttributeName() {
    return attributeName;
  }

  public void setAttributeName(AttributeName attributeName) {
    this.attributeName = attributeName;
  }

  public int getAttributeBonus() {
    return attributeBonus;
  }

  public void setAttributeBonus(int attributeBonus) {
    this.attributeBonus = attributeBonus;
  }
}
