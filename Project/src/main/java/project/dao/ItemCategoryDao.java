package project.dao;

import project.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemCategoryDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static ItemCategoryDao instance = null;

    protected ItemCategoryDao() {
        connectionManager = new ConnectionManager();
    } 

    public static ItemCategoryDao getInstance() {
        if (instance == null) {
            instance = new ItemCategoryDao();
        }
        return instance;
    }

    public ItemCategory create(ItemCategory itemCategory) throws SQLException {
        String insertItemCategory = "INSERT INTO ItemCategory(ItemName, MaxStackSize, VendorPrice) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertItemCategory, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, itemCategory.getItemName());
            insertStmt.setInt(2, itemCategory.getMaxStackSize());
            insertStmt.setDouble(3, itemCategory.getVendorPrice());
            insertStmt.executeUpdate();
            resultKey = insertStmt.getGeneratedKeys();
            int itemCategoryID = -1;
            if (resultKey.next()) {
                itemCategoryID = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            itemCategory.setItemCategoryID(itemCategoryID);
            return itemCategory;
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
}
