-- Source implementation: src/test/java/com/dtcc/automation/database/SqlDatabaseValidationTest.java
-- This folder includes SQL test data for cases where no real database access is available.
CREATE TABLE settlement_orders (
  order_id VARCHAR(50), transaction_ref VARCHAR(80), status VARCHAR(30), amount DECIMAL(19,2), currency VARCHAR(3)
);
INSERT INTO settlement_orders VALUES ('ORD-DEMO-1', 'TXN-DEMO-1', 'PROCESSED', 2500000.50, 'USD');
SELECT COUNT(*) FROM settlement_orders WHERE status = 'PROCESSED';
