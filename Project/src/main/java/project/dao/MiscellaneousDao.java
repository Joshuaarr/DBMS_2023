package project.dao;

import project.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MiscellaneousDao extends ItemCategoryDao {

    // Single pattern: instantiation is limited to one object.
    private static MiscellaneousDao instance = null;

    protected MiscellaneousDao() {
        super();
    }

    public static MiscellaneousDao getInstance() {
        if (instance == null) {
            instance = new MiscellaneousDao();
        }
        return instance;
    }


    public Miscellaneous create(Miscellaneous miscellaneous) throws SQLException {
        create((ItemCategory) miscellaneous);

        String insertMiscellaneous = "INSERT INTO Miscellaneous(ItemCategoryID, Description) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertMiscellaneous);
            insertStmt.setInt(1, miscellaneous.getItemCategoryID());
            insertStmt.setString(2, miscellaneous.getDescription());
            insertStmt.executeUpdate();
            return miscellaneous;
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

    public Miscellaneous getMiscellaneousByItemCategoryID(int itemCategoryID) throws SQLException {
        String selectMiscellaneous = "SELECT ItemCategory.ItemCategoryID, ItemName, MaxStackSize, VendorPrice, Description FROM Miscellaneous " +
                                     "INNER JOIN ItemCategory USING(ItemCategoryID) WHERE ItemCategoryID=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectMiscellaneous);
            selectStmt.setInt(1, itemCategoryID);
            results = selectStmt.executeQuery();
            if (results.next()) {
            	String resultItemName = results.getString("ItemName");
                int resultMaxStackSize = results.getInt("MaxStackSize");
                Double resultVendorPrice = results.getDouble("VendorPrice");
                String description = results.getString("Description");
                Miscellaneous miscellaneous = new Miscellaneous(itemCategoryID, resultItemName, resultMaxStackSize, resultVendorPrice, description);
                return miscellaneous;
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
