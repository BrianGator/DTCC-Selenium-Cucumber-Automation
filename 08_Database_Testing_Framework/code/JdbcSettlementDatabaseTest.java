package examples.database;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcSettlementDatabaseTest {
    private Connection connection;

    @BeforeClass
    public void setupDatabase() throws Exception {
        connection = DriverManager.getConnection("jdbc:h2:mem:portfolio;DB_CLOSE_DELAY=-1", "sa", "");
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE settlement_orders (order_id VARCHAR(40) PRIMARY KEY, status VARCHAR(30), amount DECIMAL(18,2), currency CHAR(3))");
            statement.execute("INSERT INTO settlement_orders VALUES ('ORD1001', 'PROCESSED', 1500.25, 'USD')");
        }
    }

    @Test
    public void processedOrderShouldExistInDatabase() throws Exception {
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT status, amount, currency FROM settlement_orders WHERE order_id='ORD1001'")) {
            Assert.assertTrue(rs.next(), "Expected order row to exist.");
            Assert.assertEquals(rs.getString("status"), "PROCESSED");
            Assert.assertEquals(rs.getString("currency"), "USD");
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeDatabase() throws Exception {
        if (connection != null) connection.close();
    }
}
