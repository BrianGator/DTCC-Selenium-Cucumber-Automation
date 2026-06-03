-- SQL examples that mirror what an SDET might validate after API/UI actions.
SELECT order_id, transaction_ref, status, amount, currency FROM settlement_orders WHERE order_id = ?;
SELECT COUNT(*) AS processed_count FROM settlement_orders WHERE status = 'PROCESSED';
SELECT COUNT(*) AS high_value_count FROM settlement_orders WHERE amount >= 10000000;
