records = [{"status": "PROCESSED"}, {"status": "REJECTED"}]
assert sum(1 for row in records if row["status"] == "PROCESSED") == 1
