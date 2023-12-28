package project.dao;

import project.model.*;
import project.model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CharacterDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static CharacterDao instance = null;

	protected CharacterDao() {
		connectionManager = new ConnectionManager();
	}

	public static CharacterDao getInstance() {
		if (instance == null) {
			instance = new CharacterDao();
		}
		return instance;
	}

	public Character create(Character character) throws SQLException {
		String insertCharacter = "INSERT INTO `Character`(AccountID, FirstName, LastName) VALUES(?,?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCharacter, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, character.getAccount().getAccountID());
			insertStmt.setString(2, character.getFirstName());
			insertStmt.setString(3, character.getLastName());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int characterID = -1;
			if (resultKey.next()) {
				characterID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			character.setCharacterID(characterID);
			return character;
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
			if (resultKey != null) {
				resultKey.close();
			}
		}
	}

	public Character getCharacterByCharacterID(int characterID) throws SQLException {
		String selectCharacter = "SELECT AccountID, FirstName, LastName FROM `Character` WHERE CharacterID=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCharacter);
			selectStmt.setInt(1, characterID);
			results = selectStmt.executeQuery();
			if (results.next()) {
				Integer resultAccountID = results.getInt("AccountID");
				String resultFirstName = results.getString("FirstName");
				String resultLastName = results.getString("LastName");
				AccountDao accountDao = new AccountDao();
				Account account = accountDao.getAccountByAccountID(resultAccountID);
				Character character = new Character(characterID, account, resultFirstName, resultLastName);
				return character;
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

	// get a list of characters by last name
	public List<Character> getCharactersByLastName(String lastName) throws SQLException {
		List<Character> characters = new ArrayList<Character>();
		String selectCharacters = "SELECT CharacterID, AccountID, FirstName, LastName FROM `Character` WHERE LastName=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCharacters);
			selectStmt.setString(1, lastName);
			results = selectStmt.executeQuery();
			while (results.next()) {
				Integer resultAccountID = results.getInt("AccountID");
				Integer characterID = results.getInt("CharacterID");
				String resultFirstName = results.getString("FirstName");
				String resultLastName = results.getString("LastName");
				AccountDao accountDao = new AccountDao();
				Account account = accountDao.getAccountByAccountID(resultAccountID);
				Character character = new Character(characterID, account, resultFirstName, resultLastName);
				characters.add(character);
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
		return characters;
	}

	//通过条件查询
	public List<Character> getCharactersByCondition(String firstName, String lastName, String email)
			throws SQLException {
		List<Character> characters = new ArrayList<Character>();
		StringBuilder selectCharacters = new StringBuilder();
		selectCharacters.append(
				"SELECT c.AccountID AS accountID, c.FirstName AS firstName, c.LastName AS lastName, a.`name` AS accountName, a.email AS email, a.isActive AS isActive FROM `character` AS c LEFT JOIN account AS a ON c.AccountID = a.AccountID where 1=1 ");

		if (!"".equals(firstName) && firstName != null) {
			selectCharacters.append("AND c.FirstName LIKE '%" + firstName + "%'");
		}
		if (!"".equals(lastName) && lastName != null) {
			selectCharacters.append("AND c.LastName LIKE '%" + lastName + "%'");
		}
		if (!"".equals(email) && email != null) {
			selectCharacters.append("AND a.email LIKE '%" + email + "%'");
		}

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCharacters.toString());
			results = selectStmt.executeQuery();
			while (results.next()) {
				Integer resultAccountID = results.getInt("accountID");
				String resultFirstName = results.getString("firstName");
				String resultLastName = results.getString("lastName");
				String resultAccountName = results.getString("accountName");
				String resultEmail = results.getString("email");
				Integer resultIsActive = results.getInt("isActive");

				Account resultAccount = new Account(resultAccountID, resultAccountName, resultEmail,
						resultIsActive == 1 ? true : false);
				Character resultCharacter = new Character(resultAccount, resultFirstName, resultLastName);
				characters.add(resultCharacter);
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
		return characters;
	}

	// update character by id
	public Character updateCharacterLastName(Character character, String lastName) throws SQLException {
		String updateCharacter = "UPDATE `Character` SET LastName=? WHERE CharacterID=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCharacter);
			updateStmt.setString(1, lastName);
			updateStmt.setInt(2, character.getCharacterID());
			updateStmt.executeUpdate();
			character.setLastName(lastName);
			return character;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	// delete character by id
	public Character delete(Character character) throws SQLException {
		String deleteCharacter = "DELETE FROM `Character` WHERE CharacterID=?";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCharacter);
			deleteStmt.setInt(1, character.getCharacterID());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for CharacterID=" + character.getCharacterID());
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	// Get all Characters
	public List<Character> getAllCharacters() throws SQLException {
		List<Character> characters = new ArrayList<>();
		String selectCharacters = "SELECT CharacterID, FirstName, LastName FROM Character";
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement selectStmt = connection.prepareStatement(selectCharacters);
				ResultSet results = selectStmt.executeQuery()) {

			while (results.next()) {
				int characterID = results.getInt("CharacterID");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				characters.add(new Character(characterID, null, firstName, lastName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return characters;
	}

}
