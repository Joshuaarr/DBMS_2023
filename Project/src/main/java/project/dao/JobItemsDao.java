package project.dao;
import project.model.*;
import project.model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JobItemsDao {

    // Single pattern: instantiation is limited to one object.
    private static JobItemsDao instance = null;
    private ConnectionManager connectionManager;

    protected JobItemsDao() {
        connectionManager = new ConnectionManager();
    }

    public static JobItemsDao getInstance() {
        if (instance == null) {
            instance = new JobItemsDao();
        }
        return instance;
    }

    public JobItems create(JobItems jobItems) throws SQLException {
        String insertJobItems = "INSERT INTO JobItems(CharacterID, JobID, EXP, LevelID, isCurrentJob) VALUES(?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
        	 connection = connectionManager.getConnection();
             insertStmt = connection.prepareStatement(insertJobItems);
            insertStmt.setInt(1, jobItems.getCharacter().getCharacterID());
            insertStmt.setInt(2, jobItems.getJob().getJobID());
            insertStmt.setInt(3, jobItems.getExp());
            insertStmt.setInt(4, jobItems.getJobLevel().getLevelID());
            insertStmt.setBoolean(5, jobItems.isCurrentJob());
            insertStmt.executeUpdate();
            return jobItems;
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

    public JobItems getJobItemsByCharacterIDAndJobID(int characterID, int jobID) throws SQLException {
        String selectJobItems = "SELECT EXP, LevelID, isCurrentJob FROM JobItems WHERE CharacterID=? AND JobID=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectJobItems);
            selectStmt.setInt(1, characterID);
            selectStmt.setInt(2, jobID);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int exp = results.getInt("EXP");
                int levelID = results.getInt("LevelID");
                boolean isCurrentJob = results.getBoolean("isCurrentJob");
                CharacterDao characterDao = new CharacterDao();
                Character character = characterDao.getCharacterByCharacterID(characterID);
                
                JobDao jobDao = new JobDao();
                Job job = jobDao.getJobByJobID(jobID);
                
                JobLevelDao jobLevelDao = new JobLevelDao();
                JobLevel jobLevel = jobLevelDao.getJobLevelByLevelID(levelID);

                JobItems jobItems = new JobItems(character, job, exp, jobLevel, isCurrentJob);
                return jobItems;
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
