package com.dtcc.automation.database;

import java.sql.*;

public class DatabaseClient implements AutoCloseable {
    private final Connection connection;

    public DatabaseClient(String jdbcUrl, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection(jdbcUrl, user, password);
    }

    public void initializeSettlementTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS settlement_orders (order_id VARCHAR(50) PRIMARY KEY, transaction_ref VARCHAR(80), status VARCHAR(30), amount DECIMAL(19,2), currency VARCHAR(3))");
        }
    }

    public void insertSettlementOrder(String orderId, String transactionRef, String status, double amount, String currency) throws SQLException {
        String sql = "MERGE INTO settlement_orders KEY(order_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, orderId);
            ps.setString(2, transactionRef);
            ps.setString(3, status);
            ps.setDouble(4, amount);
            ps.setString(5, currency);
            ps.executeUpdate();
        }
    }

    public boolean orderExistsWithStatus(String orderId, String transactionRef, String expectedStatus) throws SQLException {
        String sql = "SELECT status FROM settlement_orders WHERE order_id = ? AND transaction_ref = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, orderId);
            ps.setString(2, transactionRef);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && expectedStatus.equals(rs.getString("status"));
            }
        }
    }

    public int countOrdersByStatus(String status) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM settlement_orders WHERE status = ?")) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
