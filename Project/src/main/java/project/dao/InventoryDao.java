package project.dao;
import project.model.*;
import project.model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InventoryDao {

    // Single pattern: instantiation is limited to one object.
    private static InventoryDao instance = null;
    private ConnectionManager connectionManager;

    protected InventoryDao() {
        connectionManager = new ConnectionManager();
    }

    public static InventoryDao getInstance() {
        if (instance == null) {
            instance = new InventoryDao();
        }
        return instance;
    }

    public Inventory create(Inventory inventory) throws SQLException {	
    	// If the slotID already occupied, and is putting in the same kind of Consumable/Miscellaneous
    	// update the stack size
    	// IF slotID exist 
    	if (this.getInventoryByCharacterIDAndSlotID(inventory.getCharacter().getCharacterID(), inventory.getSlotID()) != null) {
    		 ConsumableDao consumableDao = ConsumableDao.getInstance();
             MiscellaneousDao miscellaneousDao = MiscellaneousDao.getInstance();
             Integer itemCategoryID = inventory.getItemCategory().getItemCategoryID();
          // and ItemCategoryID belongs to Consumable or Miscellaneous
             if (consumableDao.getConsumableByItemCategoryID(itemCategoryID) != null) {
            	// And stackSize < maxStackSize
            	 if (consumableDao.getConsumableByItemCategoryID(itemCategoryID).getMaxStackSize() > inventory.getStackSize()) {
            		// update stackSize, Return
            		 this.updateInventoryStackSize(inventory);
            		 return inventory;
            	 }
             }else if (miscellaneousDao.getMiscellaneousByItemCategoryID(itemCategoryID) != null) {
            	// And stackSize < maxStackSize
            	 if (miscellaneousDao.getMiscellaneousByItemCategoryID(itemCategoryID).getMaxStackSize() > inventory.getStackSize()) {
            		// update stackSize, Return
            		 this.updateInventoryStackSize(inventory);
            		 return inventory;
            	 }
             }
          // If the slotID already occupied, and its not the same kind of Consumable/Miscellaneous
             System.out.println("Inventory create failed, the slot has been occupied.");
             return null;
    	}
    	
        String insertInventory = "INSERT INTO Inventory(CharacterID, SlotID, ItemCategoryID, StackSize) VALUES(?,?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertInventory);
            insertStmt.setInt(1, inventory.getCharacter().getCharacterID());
            insertStmt.setInt(2, inventory.getSlotID());
            insertStmt.setInt(3, inventory.getItemCategory().getItemCategoryID());
            insertStmt.setInt(4, inventory.getStackSize());
            insertStmt.executeUpdate();
            return inventory;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }
    
    
    // Helper method for create method
    private Inventory updateInventoryStackSize(Inventory inventory) throws SQLException {
    	String updateStackSize = "UPDATE Inventory SET StackSize=? WHERE CharacterID=? AND SlotID=?";
    	Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateStackSize);
			updateStmt.setInt(1, inventory.getStackSize() + 1);
			updateStmt.setInt(2, inventory.getCharacter().getCharacterID());
			updateStmt.setInt(3, inventory.getSlotID());
			updateStmt.executeUpdate();
			inventory.setStackSize(inventory.getStackSize() + 1);
			return inventory;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

    public Inventory getInventoryByCharacterIDAndSlotID(int characterID, int slotID) throws SQLException {
        String selectInventory = "SELECT ItemCategoryID, StackSize FROM Inventory WHERE CharacterID=? AND SlotID=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectInventory);
            selectStmt.setInt(1, characterID);
            selectStmt.setInt(2, slotID);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int itemCategoryID = results.getInt("ItemCategoryID");
                int stackSize = results.getInt("StackSize");
                CharacterDao characterDao = new CharacterDao();
                Character character = characterDao.getCharacterByCharacterID(characterID);
                
                
                GearDao gearDao = GearDao.getInstance();
                WeaponDao weaponDao = WeaponDao.getInstance();
                ConsumableDao consumableDao = ConsumableDao.getInstance();
                MiscellaneousDao miscellaneousDao = MiscellaneousDao.getInstance();
                
                
                Inventory inventory;
                if(gearDao.getGearByItemCategoryID(itemCategoryID) != null) {
                     Gear gear = gearDao.getGearByItemCategoryID(itemCategoryID);
                     inventory = new Inventory(character, slotID, gear, stackSize);
                } else if(weaponDao.getWeaponByItemCategoryID(itemCategoryID) != null){
                	Weapon weapon = weaponDao.getWeaponByItemCategoryID(itemCategoryID);
                	inventory = new Inventory(character, slotID, weapon, stackSize);
                } else if (consumableDao.getConsumableByItemCategoryID(itemCategoryID) != null){
                	Consumable consumable = consumableDao.getConsumableByItemCategoryID(itemCategoryID);
                	inventory = new Inventory(character, slotID, consumable, stackSize);
                } else {
                	Miscellaneous miscellaneous = miscellaneousDao.getMiscellaneousByItemCategoryID(itemCategoryID);
                	inventory = new Inventory(character, slotID, miscellaneous, stackSize);
                }
                
                return inventory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }
}
