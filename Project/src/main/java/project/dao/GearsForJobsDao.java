package project.dao;
import project.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GearsForJobsDao {

    // Single pattern: instantiation is limited to one object.
    private static GearsForJobsDao instance = null;
    private ConnectionManager connectionManager;

    protected GearsForJobsDao() {
        connectionManager = new ConnectionManager();
    }

    public static GearsForJobsDao getInstance() {
        if (instance == null) {
            instance = new GearsForJobsDao();
        }
        return instance;
    }

    
    public GearsForJobs create(GearsForJobs gearsForJobs) throws SQLException {
        String insertGearsForJobs = "INSERT INTO GearsForJobs(ItemCategoryID, JobID) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertGearsForJobs);
            insertStmt.setInt(1, gearsForJobs.getGear().getItemCategoryID());
            insertStmt.setInt(2, gearsForJobs.getAssociatedJob().getJobID());
            insertStmt.executeUpdate();
            return gearsForJobs;
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
    
    public GearsForJobs getGearsForJobsByItemCategoryID(int itemCategoryID) throws SQLException {
        String selectGearsForJobs = "SELECT JobID FROM GearsForJobs WHERE ItemCategoryID=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectGearsForJobs);
            selectStmt.setInt(1, itemCategoryID);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int jobID = results.getInt("JobID");
                
                GearDao gearDao = new GearDao();
                Gear gear = gearDao.getGearByItemCategoryID(itemCategoryID);
                
                JobDao jobDao = new JobDao();
                Job associatedJob = jobDao.getJobByJobID(jobID);

                GearsForJobs gearsForJobs = new GearsForJobs(gear, associatedJob);
                return gearsForJobs;
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
