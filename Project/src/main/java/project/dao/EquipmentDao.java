package project.dao;

import project.model.*;
import project.model.ItemCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EquipmentDao extends ItemCategoryDao {

    // Single pattern: instantiation is limited to one object.
    private static EquipmentDao instance = null;

    protected EquipmentDao() {
        super();
    }

    public static EquipmentDao getInstance() {
        if (instance == null) {
            instance = new EquipmentDao();
        }
        return instance;
    }


    public Equipment create(Equipment equipment) throws SQLException {
    	create((ItemCategory) equipment);
        String insertEquipment = "INSERT INTO Equipment(ItemCategoryID, ItemLevel, RequiredLevel) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertEquipment);
            insertStmt.setInt(1, equipment.getItemCategoryID());
            insertStmt.setInt(2, equipment.getItemLevel());
            insertStmt.setInt(3, equipment.getRequiredLevel());
            insertStmt.executeUpdate();
            return equipment;
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
}