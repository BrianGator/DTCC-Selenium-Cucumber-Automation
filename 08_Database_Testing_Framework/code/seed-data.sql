INSERT INTO settlement_orders (order_id, transaction_ref, status, amount, currency)
VALUES ('ORD1001', 'TXN1001', 'PROCESSED', 1500.25, 'USD');

INSERT INTO settlement_orders (order_id, transaction_ref, status, amount, currency)
VALUES ('ORD1002', 'TXN1002', 'PENDING_REVIEW', 2500.00, 'USD');

INSERT INTO order_audit (order_id, previous_status, new_status, changed_by)
VALUES ('ORD1001', 'ACCEPTED', 'PROCESSED', 'automation-framework');
