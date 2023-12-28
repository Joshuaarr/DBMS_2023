package project.model;

public class AttributePower {

  public enum AttributeName {
    Strength, Dexterity, Vitality, Mind, Intelligence
  }

  protected AttributeName AttributeName;
  protected Character character;
  protected int attributePower;

  public AttributePower(AttributeName AttributeName, Character character, int attributePower) {
    this.AttributeName = AttributeName;
    this.character = character;
    this.attributePower = attributePower;
  }



public AttributeName getAttributeName() {
    return AttributeName;
  }

  public void setAttribute(AttributeName AttributeName) {
    this.AttributeName = AttributeName;
  }

  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }

  public int getAttributePower() {
    return attributePower;
  }

  public void setAttributePower(int attributePower) {
    this.attributePower = attributePower;
  }
}
