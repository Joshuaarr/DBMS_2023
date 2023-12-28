package project.dao;

import project.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JobLevelDao {

    // Single pattern: instantiation is limited to one object.
    private static JobLevelDao instance = null;
    private ConnectionManager connectionManager;

    protected JobLevelDao() {
        connectionManager = new ConnectionManager();
    }

    public static JobLevelDao getInstance() {
        if (instance == null) {
            instance = new JobLevelDao();
        }
        return instance;
    }

    public JobLevel create(JobLevel jobLevel) throws SQLException {
        String insertJobLevel = "INSERT INTO JobLevel(MinEXP, MaxEXP, isMaxLevel) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertJobLevel, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, jobLevel.getMinExp());
            insertStmt.setInt(2, jobLevel.getMinExp());
            insertStmt.setBoolean(3, jobLevel.isMaxLevel());
            insertStmt.executeUpdate();
            resultKey = insertStmt.getGeneratedKeys();
            int levelID = -1;
            if (resultKey.next()) {
                levelID = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            jobLevel.setLevelID(levelID);
            return jobLevel;
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

    public JobLevel getJobLevelByLevelID(int levelID) throws SQLException {
        String selectJobLevel = "SELECT MinEXP, MaxEXP, isMaxLevel FROM JobLevel WHERE LevelID=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectJobLevel);
            selectStmt.setInt(1, levelID);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int minEXP = results.getInt("MinEXP");
                int maxEXP = results.getInt("MaxEXP");
                boolean isMaxLevel = results.getBoolean("isMaxLevel");

                JobLevel jobLevel = new JobLevel(levelID, minEXP, maxEXP, isMaxLevel);
                return jobLevel;
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
