package project.dao;

import project.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AccountDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AccountDao instance = null;
	protected AccountDao() {
		connectionManager = new ConnectionManager();
	}
	public static AccountDao getInstance() {
		if(instance == null) {
			instance = new AccountDao();
		}
		return instance;
	}

	/**
	 * Save the Account instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Account create(Account account) throws SQLException {
		String insertAccount = "INSERT INTO Account(name, email, isActive) VALUES(?,?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAccount, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, account.getName());
			insertStmt.setString(2, account.getEmail());
			insertStmt.setBoolean(3, account.isActive());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int accountID = -1;
			if(resultKey.next()) {
				accountID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			account.setAccountID(accountID);
			return account;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	
	/**
	 * Get the Persons record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 */
	public Account getAccountByAccountID(int accountID) throws SQLException {
		String selectAccount = "SELECT accountID, name, email, isActive FROM Account WHERE accountID=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAccount);
			selectStmt.setInt(1, accountID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer resultAccountID = results.getInt("AccountID");
				String name = results.getString("name");
				String email = results.getString("email");
				Boolean isActive = results.getBoolean("isActive");
				Account account = new Account(resultAccountID, name, email, isActive);
				return account;
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
