package project.dao;

import project.model.*;
import project.model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AttributePowerDao{
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static AttributePowerDao instance = null;
	protected AttributePowerDao() {
		connectionManager = new ConnectionManager();
	}
	public static AttributePowerDao getInstance() {
		if(instance == null) {
			instance = new AttributePowerDao(); 
		}
		return instance;
	}
	

	public AttributePower create(AttributePower attributePower) throws SQLException {
		String insertAttributePower = "INSERT INTO AttributePower(AttributeName, CharacterID, AttributePower) VALUES(?,?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAttributePower);
			insertStmt.setString(1, attributePower.getAttributeName().name());
			insertStmt.setInt(2, attributePower.getCharacter().getCharacterID());
			insertStmt.setInt(3, attributePower.getAttributePower());
			insertStmt.executeUpdate();
			return attributePower;
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

	
	public AttributePower getAttributePowerByAttributeNameAndCharacterID(String attributeName, int CharacterID) throws SQLException{
		String selectAttributePower =
			"SELECT AttributeName, AttributePower.CharacterID AS CharacterID, AttributePower " +
			"FROM AttributePower INNER JOIN `Character` " +
			"ON AttributePower.CharacterID = `Character`.CharacterID " +
			"WHERE AttributeName=? AND AttributePower.CharacterID=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAttributePower);
			selectStmt.setString(1, attributeName);
			selectStmt.setInt(2, CharacterID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer resultCharacterID = results.getInt("CharacterID");
				Integer resultAttributePower = results.getInt("AttributePower");
				AttributePower.AttributeName resultAttributeName = AttributePower.AttributeName.valueOf(
						results.getString("AttributeName"));
				CharacterDao characterDao = new CharacterDao();
				Character character = characterDao.getCharacterByCharacterID(resultCharacterID);
				AttributePower attributePower = new AttributePower(resultAttributeName, character, resultAttributePower);
				return attributePower;
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
