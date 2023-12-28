package project.dao;

import project.model.*;
import project.model.Character;
import project.model.CustomizedEquipment.Quality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomizedEquipmentDao {

    // Single pattern: instantiation is limited to one object.
    private static CustomizedEquipmentDao instance = null;
    private ConnectionManager connectionManager;

    protected CustomizedEquipmentDao() {
        connectionManager = new ConnectionManager();
    }

    public static CustomizedEquipmentDao getInstance() {
        if (instance == null) {
            instance = new CustomizedEquipmentDao();
        }
        return instance;
    }

    public CustomizedEquipment create(CustomizedEquipment customizedEquipment) throws SQLException {
    	String insertCustomizedEquipment;
    	if (customizedEquipment.getMaker() != null) {
    		insertCustomizedEquipment = "INSERT INTO CustomizedEquipment(CharacterID, SlotID, DyeColor, Quality, `Condition`, MadeByCharacterID) VALUES(?,?,?,?,?,?)";
    	}else {
    		insertCustomizedEquipment = "INSERT INTO CustomizedEquipment(CharacterID, SlotID, DyeColor, Quality, `Condition`) VALUES(?,?,?,?,?)";
    	}
        
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCustomizedEquipment);
            insertStmt.setInt(1, customizedEquipment.getOwner().getCharacterID());
            insertStmt.setInt(2, customizedEquipment.getSlotID());
            insertStmt.setString(3, customizedEquipment.getDyeColor());
            insertStmt.setString(4, customizedEquipment.getQuality().name());
            insertStmt.setString(5, customizedEquipment.getCondition());
            if (customizedEquipment.getMaker() != null) {
            	insertStmt.setInt(6, customizedEquipment.getMaker().getCharacterID());
            }
            
            insertStmt.executeUpdate();
            return customizedEquipment;
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

    public CustomizedEquipment getCustomizedEquipmentByOwnerIDAndSlotID(int ownerID, int slotID) throws SQLException {
        String selectCustomizedEquipment = "SELECT DyeColor, Quality, `Condition`, MadeByCharacterID FROM CustomizedEquipment WHERE CharacterID=? AND SlotID=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCustomizedEquipment);
            selectStmt.setInt(1, ownerID);
            selectStmt.setInt(2, slotID);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String dyeColor = results.getString("DyeColor");
                String condition = results.getString("Condition");
                int madeByCharacterID = results.getInt("MadeByCharacterID");
                CharacterDao characterDao = new CharacterDao();
                Character owner = characterDao.getCharacterByCharacterID(ownerID);
                Character maker = characterDao.getCharacterByCharacterID(madeByCharacterID);
                CustomizedEquipment.Quality resultQuality = CustomizedEquipment.Quality.valueOf(
                		results.getString("Quality"));
                CustomizedEquipment customizedEquipment = new CustomizedEquipment(owner, slotID, dyeColor, resultQuality,
                	    condition, maker);
                return customizedEquipment;
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
