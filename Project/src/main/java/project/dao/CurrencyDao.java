package project.dao;

import project.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CurrencyDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static CurrencyDao instance = null;

    protected CurrencyDao() {
        connectionManager = new ConnectionManager();
    }

    public static CurrencyDao getInstance() {
        if (instance == null) {
            instance = new CurrencyDao();
        }
        return instance;
    }

    /**
     * Save the Currency instance by storing it in your MySQL instance.
     * This runs an INSERT statement.
     */
    public Currency create(Currency currency) throws SQLException {
        String insertCurrency = "INSERT INTO Currency(CurrencyName, TotalCapAmount, WeeklyCapAmount, isWeeklyCap, isDiscontinued) VALUES(?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCurrency);
            insertStmt.setString(1, currency.getCurrencyName());
            insertStmt.setInt(2, currency.getTotalCapAmount());
            insertStmt.setInt(3, currency.getWeeklyCapAmount());
            insertStmt.setBoolean(4, currency.isWeeklyCap());
            insertStmt.setBoolean(5, currency.isDiscontinued());
            insertStmt.executeUpdate();
            return currency;
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

    /**
     * Get the Currency record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Currency instance.
     */
    public Currency getCurrencyByCurrencyName(String currencyName) throws SQLException {
        String selectCurrency = "SELECT CurrencyName, TotalCapAmount, WeeklyCapAmount, isWeeklyCap, isDiscontinued FROM Currency WHERE CurrencyName=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCurrency);
            selectStmt.setString(1, currencyName);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String resultCurrencyName = results.getString("CurrencyName");
                int totalCapAmount = results.getInt("TotalCapAmount");
                int weeklyCapAmount = results.getInt("WeeklyCapAmount");
                boolean isWeeklyCap = results.getBoolean("isWeeklyCap");
                boolean isDiscontinued = results.getBoolean("isDiscontinued");
                Currency currency = new Currency(resultCurrencyName, totalCapAmount, weeklyCapAmount, isWeeklyCap, isDiscontinued);
                return currency;
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
