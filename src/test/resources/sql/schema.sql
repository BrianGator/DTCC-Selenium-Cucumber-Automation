-- Example in-memory H2 schema for SQL database tests when no enterprise database is available.
CREATE TABLE IF NOT EXISTS settlement_orders (
  order_id VARCHAR(50) PRIMARY KEY,
  transaction_ref VARCHAR(80) NOT NULL,
  status VARCHAR(30) NOT NULL,
  amount DECIMAL(19,2) NOT NULL,
  currency VARCHAR(3) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
