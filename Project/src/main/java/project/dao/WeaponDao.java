package project.dao;

import project.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WeaponDao extends EquipmentDao {

    // Single pattern: instantiation is limited to one object.
    private static WeaponDao instance = null;

    protected WeaponDao() {
        super();
    }

    public static WeaponDao getInstance() {
        if (instance == null) {
            instance = new WeaponDao();
        }
        return instance;
    }


    public Weapon create(Weapon weapon) throws SQLException {
    	create((Equipment) weapon);

        String insertWeapon = "INSERT INTO Weapon(ItemCategoryID, DamageDone, AutoAttack, AutoDelay, AssociatedJobID) " +
                "VALUES(?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertWeapon);
            insertStmt.setInt(1, weapon.getItemCategoryID());
            insertStmt.setInt(2, weapon.getDamageDone());
            insertStmt.setDouble(3, weapon.getAutoAttack());
            insertStmt.setDouble(4, weapon.getAutoDelay());
            insertStmt.setInt(5, weapon.getJob().getJobID());
            insertStmt.executeUpdate();
            return weapon;
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

    public Weapon getWeaponByItemCategoryID(int itemCategoryID) throws SQLException {
        String selectWeapon = "SELECT e.ItemLevel, e.RequiredLevel, i.ItemName, i.MaxStackSize, i.VendorPrice, DamageDone, AutoAttack, AutoDelay, AssociatedJobID " +
        		"FROM Weapon w " +
        		"INNER JOIN Equipment e ON e.ItemCategoryID = w.ItemCategoryID " +
        		"INNER JOIN ItemCategory i ON w.ItemCategoryID = e.ItemCategoryID " +
        		"WHERE w.ItemCategoryID=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectWeapon);
            selectStmt.setInt(1, itemCategoryID);
            results = selectStmt.executeQuery();
            if (results.next()) {
            	int itemLevel = results.getInt("ItemLevel");
                int requiredLevel = results.getInt("RequiredLevel");
                String itemName = results.getString("ItemName");
                int maxStackSize = results.getInt("MaxStackSize");
                Double vendorPrice = results.getDouble("VendorPrice");
                int damageDone = results.getInt("DamageDone");
                Double autoAttack = results.getDouble("AutoAttack");
                Double autoDelay = results.getDouble("AutoDelay");
                int associatedJobID = results.getInt("AssociatedJobID");
                
                JobDao jobDao = new JobDao();
                Job resultjob = jobDao.getJobByJobID(associatedJobID);

                Weapon weapon = new Weapon(itemCategoryID, itemName, maxStackSize, vendorPrice,
                	    itemLevel, requiredLevel, damageDone, autoAttack, autoDelay,
                	    resultjob);
                return weapon;
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
