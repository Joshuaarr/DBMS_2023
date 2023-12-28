package project.dao;

import project.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumableDao extends ItemCategoryDao {

    // Single pattern: instantiation is limited to one object.
    private static ConsumableDao instance = null;

    protected ConsumableDao() {
        super();
    }

    public static ConsumableDao getInstance() {
        if (instance == null) {
            instance = new ConsumableDao();
        }
        return instance;
    }

    public Consumable create(Consumable consumable) throws SQLException {
    	create((ItemCategory) consumable);

        String insertConsumable = "INSERT INTO Consumable(ItemCategoryID, ItemLevel, Description) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertConsumable);
            insertStmt.setInt(1, consumable.getItemCategoryID());
            insertStmt.setInt(2, consumable.getItemLevel());
            insertStmt.setString(3, consumable.getDescription());
            insertStmt.executeUpdate();
            return consumable;
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

    public Consumable getConsumableByItemCategoryID(int itemCategoryID) throws SQLException {
        String selectConsumable = "SELECT ItemLevel, Description, ItemName, MaxStackSize, VendorPrice " +
        		"FROM Consumable c INNER JOIN ItemCategory i " +
        		"ON c.ItemCategoryID =  i.ItemCategoryID " +
        		"WHERE c.ItemCategoryID=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectConsumable);
            selectStmt.setInt(1, itemCategoryID);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int itemLevel = results.getInt("ItemLevel");
                String description = results.getString("Description");
                String itemName = results.getString("ItemName");
                int maxStackSize = results.getInt("MaxStackSize");
                Double vendorPrice = results.getDouble("VendorPrice");

                Consumable consumable = new Consumable(itemCategoryID, itemName, maxStackSize, vendorPrice,
                	    itemLevel, description);
                return consumable;
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
