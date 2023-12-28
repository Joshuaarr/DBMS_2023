package project.model;

public class CustomizedEquipment {


  protected Character owner;
  protected int slotID;
  protected String dyeColor;
  protected Quality quality;
  protected String condition;
  protected Character maker;
  
  public enum Quality {
	  high, normal
	  }
  
  

  public CustomizedEquipment(Character owner, int slotID, String dyeColor, Quality quality,
    String condition, Character maker) {
    this.owner = owner;
    this.slotID = slotID;
    this.dyeColor = dyeColor;
    this.quality = quality;
    this.condition = condition;
    this.maker = maker;
  }
  
  public CustomizedEquipment(Character owner, int slotID, String dyeColor, Quality quality,
		    String condition) {
		    this.owner = owner;
		    this.slotID = slotID;
		    this.dyeColor = dyeColor;
		    this.quality = quality;
		    this.condition = condition;
		    this.maker = null;
		  }

  public Character getOwner() {
    return owner;
  }

  public void setOwner(Character owner) {
    this.owner = owner;
  }

  public int getSlotID() {
    return slotID;
  }

  public void setSlotID(int slotID) {
    this.slotID = slotID;
  }

  public String getDyeColor() {
    return dyeColor;
  }

  public void setDyeColor(String dyeColor) {
    this.dyeColor = dyeColor;
  }

  public Quality getQuality() {
    return quality;
  }

  public void setQuality(Quality quality) {
    this.quality = quality;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public Character getMaker() {
    return maker;
  }

  public void setMaker(Character maker) {
    this.maker = maker;
  }
}
