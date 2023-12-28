package project.dao;

import project.model.*;
import project.model.EquipmentBonus.AttributeName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EquipmentBonusDao {

    // Single pattern: instantiation is limited to one object.
    private static EquipmentBonusDao instance = null;
    private ConnectionManager connectionManager;

    protected EquipmentBonusDao() {
        connectionManager = new ConnectionManager();
    }

    public static EquipmentBonusDao getInstance() {
        if (instance == null) {
            instance = new EquipmentBonusDao();
        }
        return instance;
    }

    public EquipmentBonus create(EquipmentBonus equipmentBonus) throws SQLException {
        String insertEquipmentBonus = "INSERT INTO EquipmentBonus(ItemCategoryID, AttributeName, AttributeBonus) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
      
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertEquipmentBonus);
            insertStmt.setInt(1, equipmentBonus.getEquipment().getItemCategoryID());
            insertStmt.setString(2, equipmentBonus.getAttributeName().name());
            insertStmt.setInt(3, equipmentBonus.getAttributeBonus());
            insertStmt.executeUpdate();
            return equipmentBonus;
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

    public EquipmentBonus getEquipmentBonusByItemCategoryIDAndAttributeName(int itemCategoryID, String attributeName) throws SQLException {
        String selectEquipmentBonus = "SELECT AttributeBonus, AttributeName FROM EquipmentBonus WHERE ItemCategoryID=? AND AttributeName=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectEquipmentBonus);
            selectStmt.setInt(1, itemCategoryID);
            selectStmt.setString(2, attributeName);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int attributeBonus = results.getInt("AttributeBonus");
                EquipmentBonus.AttributeName resultAttributeName = EquipmentBonus.AttributeName.valueOf(
                		results.getString("AttributeName"));
                GearDao gearDao = GearDao.getInstance();
                WeaponDao weaponDao = WeaponDao.getInstance();
                EquipmentBonus equipmentBonus;
                if(gearDao.getGearByItemCategoryID(itemCategoryID) != null) {
                     Gear gear = gearDao.getGearByItemCategoryID(itemCategoryID);
                     equipmentBonus = new EquipmentBonus(gear, resultAttributeName, attributeBonus);
                } else {
                	Weapon weapon = weaponDao.getWeaponByItemCategoryID(itemCategoryID);
                    equipmentBonus = new EquipmentBonus(weapon, resultAttributeName, attributeBonus);
                }
                return equipmentBonus;
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
