package project.dao;

import project.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GearDao extends EquipmentDao {

    // Single pattern: instantiation is limited to one object.
    private static GearDao instance = null;

    protected GearDao() {
        super();
    }

    public static GearDao getInstance() {
        if (instance == null) {
            instance = new GearDao();
        }
        return instance;
    }


    public Gear create(Gear gear) throws SQLException {
    	create((Equipment) gear);

        String insertGear = "INSERT INTO Gear(ItemCategoryID, DefenceRating, MagicDefenceRating) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertGear);
            insertStmt.setInt(1, gear.getItemCategoryID());
            insertStmt.setInt(2, gear.getDefenceRating());
            insertStmt.setInt(3, gear.getMagicDefenceRating());
            insertStmt.executeUpdate();
            return gear;
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

    public Gear getGearByItemCategoryID(int itemCategoryID) throws SQLException {
        String selectGear = "SELECT e.ItemLevel, e.RequiredLevel, i.ItemName, i.MaxStackSize, i.VendorPrice, DefenceRating, MagicDefenceRating " +
        		"FROM Gear g " +
        		"INNER JOIN Equipment e ON e.ItemCategoryID = g.ItemCategoryID " +
        		"INNER JOIN ItemCategory i ON g.ItemCategoryID = e.ItemCategoryID " +
        		"WHERE g.ItemCategoryID=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectGear);
            selectStmt.setInt(1, itemCategoryID);
            results = selectStmt.executeQuery();
            if (results.next()) {
            	int itemLevel = results.getInt("ItemLevel");
                int requiredLevel = results.getInt("RequiredLevel");
                String itemName = results.getString("ItemName");
                int maxStackSize = results.getInt("MaxStackSize");
                Double vendorPrice = results.getDouble("VendorPrice");
                int defenceRating = results.getInt("DefenceRating");
                int magicDefenceRating = results.getInt("MagicDefenceRating");
                Gear gear = new Gear(itemCategoryID, itemName, maxStackSize, vendorPrice, 
                		itemLevel, requiredLevel, defenceRating, magicDefenceRating) ;
                return gear;
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
