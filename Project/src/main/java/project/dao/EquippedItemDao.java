package project.dao;

import project.model.*;
import project.model.Character;
import project.model.EquippedItem.SlotName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class EquippedItemDao{
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static EquippedItemDao instance = null;
	protected EquippedItemDao() {
		connectionManager = new ConnectionManager();
	}
	public static EquippedItemDao getInstance() {
		if(instance == null) {
			instance = new EquippedItemDao(); 
		}
		return instance;
	}
	

	public EquippedItem create(EquippedItem equippedItem) throws SQLException {
		String insertEquippedItem = "INSERT INTO EquippedItem(CharacterID, SlotName, SlotID) VALUES(?,?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertEquippedItem);
			insertStmt.setInt(1, equippedItem.getCharacter().getCharacterID());
			insertStmt.setString(2, equippedItem.getSlotName().name());
			insertStmt.setInt(3, equippedItem.getSlotID());
			insertStmt.executeUpdate();
			return equippedItem;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	
	public EquippedItem getEquippedItemBySlotNameAndCharacterID(String slotName, int CharacterID) throws SQLException{
		String selectEquippedItem =
			"SELECT EquippedItem.CharacterID AS CharacterID, SlotName, SlotID " +
			"FROM EquippedItem INNER JOIN `Character` " +
			"ON EquippedItem.CharacterID = `Character`.CharacterID " +
			"WHERE SlotName=? AND EquippedItem.CharacterID=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEquippedItem);
			selectStmt.setString(1, slotName);
			selectStmt.setInt(2, CharacterID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer resultCharacterID = results.getInt("CharacterID");
				EquippedItem.SlotName resultSlotName = EquippedItem.SlotName.valueOf(
						results.getString("SlotName"));
				Integer resultSlotID = results.getInt("SlotID");
				CharacterDao characterDao = new CharacterDao();
				Character character = characterDao.getCharacterByCharacterID(resultCharacterID);
				EquippedItem equippedItem = new EquippedItem(character, resultSlotName, resultSlotID);
				return equippedItem;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
}
