package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import project.model.*;
import project.model.ConsumableBonus.AttributeName;

public class ConsumableBonusDao {

    // Single pattern: instantiation is limited to one object.
    private static ConsumableBonusDao instance = null;
    private ConnectionManager connectionManager;

    protected ConsumableBonusDao() {
        connectionManager = new ConnectionManager();
    }

    public static ConsumableBonusDao getInstance() {
        if (instance == null) {
            instance = new ConsumableBonusDao();
        }
        return instance;
    }

    public ConsumableBonus create(ConsumableBonus consumableBonus) throws SQLException {
        String insertConsumableBonus = "INSERT INTO ConsumableBonus(ItemCategoryID, AttributeName, AttributeBonusPercent, AttributeBonusCap) VALUES(?,?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertConsumableBonus);
            insertStmt.setInt(1, consumableBonus.getConsumable().getItemCategoryID());
            insertStmt.setString(2, consumableBonus.getAttributeName().name());
            insertStmt.setDouble(3, consumableBonus.getAttributeBonusPercent());
            insertStmt.setInt(4, consumableBonus.getAttributeBonusCap());
            insertStmt.executeUpdate();
            return consumableBonus;
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

    public ConsumableBonus getConsumableBonusByItemCategoryIDAndAttributeName(int itemCategoryID, String attributeName) throws SQLException {
        String selectConsumableBonus = "SELECT AttributeBonusPercent, AttributeBonusCap, AttributeName FROM ConsumableBonus WHERE ItemCategoryID=? AND AttributeName=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectConsumableBonus);
            selectStmt.setInt(1, itemCategoryID);
            selectStmt.setString(2, attributeName);
            results = selectStmt.executeQuery();
            if (results.next()) {
                double attributeBonusPercent = results.getDouble("AttributeBonusPercent");
                int attributeBonusCap = results.getInt("AttributeBonusCap");
                ConsumableDao consumableDao = new ConsumableDao();
                Consumable consumable = consumableDao.getConsumableByItemCategoryID(itemCategoryID);
                ConsumableBonus.AttributeName resultAttributeName = ConsumableBonus.AttributeName.valueOf(
                		results.getString("AttributeName"));
                ConsumableBonus consumableBonus = new ConsumableBonus(consumable, resultAttributeName, attributeBonusPercent,
                	    attributeBonusCap);
                return consumableBonus;
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
