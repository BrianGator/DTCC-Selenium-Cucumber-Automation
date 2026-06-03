package com.dtcc.automation.database;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlDatabaseValidationTest {
    @Test(description = "Creates H2 SQL schema, loads example data, and validates settlement status counts")
    public void validatesSettlementOrdersUsingExampleSqlData() throws Exception {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:showcase;DB_CLOSE_DELAY=-1", "sa", "");
             Statement statement = connection.createStatement()) {
            statement.execute(Files.readString(Path.of("src/test/resources/sql/schema.sql")));
            statement.execute(Files.readString(Path.of("src/test/resources/sql/data.sql")));

            try (ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM settlement_orders WHERE status = 'PROCESSED'")) {
                Assert.assertTrue(rs.next());
                Assert.assertEquals(rs.getInt(1), 1, "One demo order should be processed.");
            }

            try (ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM settlement_orders WHERE amount >= 10000000")) {
                Assert.assertTrue(rs.next());
                Assert.assertEquals(rs.getInt(1), 1, "One demo order should be high value.");
            }
        }
    }
}
