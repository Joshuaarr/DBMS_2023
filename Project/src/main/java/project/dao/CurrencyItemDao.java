package project.dao;

import project.model.*;
import project.model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CurrencyItemDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static CurrencyItemDao instance = null;

    protected CurrencyItemDao() {
        connectionManager = new ConnectionManager();
    }

    public static CurrencyItemDao getInstance() {
        if (instance == null) {
            instance = new CurrencyItemDao();
        }
        return instance;
    }

    public CurrencyItem create(CurrencyItem currencyItem) throws SQLException {
        String insertCurrencyItem = "INSERT INTO CurrencyItem(CharacterID, CurrencyName, TotalAmount, WeeklyAmount) VALUES(?,?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCurrencyItem);
            insertStmt.setInt(1, currencyItem.getCharacter().getCharacterID());
            insertStmt.setString(2, currencyItem.getCurrency().getCurrencyName());
            insertStmt.setInt(3, currencyItem.getTotalAmount());
            insertStmt.setInt(4, currencyItem.getWeeklyAmount());
            insertStmt.executeUpdate();
            return currencyItem;
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

    public CurrencyItem getCurrencyItemByCharacterIDAndCurrencyName(int characterID, String currencyName) throws SQLException {
        String selectCurrencyItem =
                "SELECT CharacterID, CurrencyName, TotalAmount, WeeklyAmount " +
                        "FROM CurrencyItem " +
                        "WHERE CharacterID=? AND CurrencyName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCurrencyItem);
            selectStmt.setInt(1, characterID);
            selectStmt.setString(2, currencyName);
            results = selectStmt.executeQuery();
            if (results.next()) {
                Integer resultCharacterID = results.getInt("CharacterID");
                String resultCurrencyName = results.getString("CurrencyName");
                Integer resultTotalAmount = results.getInt("TotalAmount");
                Integer resultWeeklyAmount = results.getInt("WeeklyAmount");
                CharacterDao characterDao = new CharacterDao();
                Character resultCharacter = characterDao.getCharacterByCharacterID(resultCharacterID);
                
                CurrencyDao currencyDao = new CurrencyDao();
                Currency resultCurrency = currencyDao.getCurrencyByCurrencyName(resultCurrencyName);

                CurrencyItem currencyItem = new CurrencyItem(resultCharacter, resultCurrency, resultTotalAmount, resultWeeklyAmount);
                return currencyItem;
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
