package project.dao;
import project.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobDao {

    // Single pattern: instantiation is limited to one object.
    private static JobDao instance = null;
    private ConnectionManager connectionManager;

    protected JobDao() {
        connectionManager = new ConnectionManager();
    }

    public static JobDao getInstance() {
        if (instance == null) {
            instance = new JobDao();
        }
        return instance;
    }

    public Job create(Job job) throws SQLException {
        String insertJob = "INSERT INTO Job(JobName) VALUES(?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertJob, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, job.getJobName());
            insertStmt.executeUpdate();
            resultKey = insertStmt.getGeneratedKeys();
            int jobID = -1;
            if (resultKey.next()) {
                jobID = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            job.setJobID(jobID);
            return job;
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

    
    // Search job by ID
    public Job getJobByJobID(int jobID) throws SQLException {
        String selectJob = "SELECT JobName FROM Job WHERE JobID=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectJob);
            selectStmt.setInt(1, jobID);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String jobName = results.getString("JobName");

                Job job = new Job(jobID, jobName);
                return job;
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

	public List<Job> getAllJobs() throws SQLException  {
        List<Job> jobs = new ArrayList<>();
        String selectJobs = "SELECT JobID, JobName FROM Job";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectJobs);
             ResultSet results = selectStmt.executeQuery()) {

            while (results.next()) {
                int jobID = results.getInt("JobID");
                String jobName = results.getString("JobName");
                jobs.add(new Job(jobID, jobName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return jobs;
    }
    
}
