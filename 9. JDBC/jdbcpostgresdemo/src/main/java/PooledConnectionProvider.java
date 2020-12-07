import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PooledConnectionProvider implements ConnectionProvider {

    private DataSource dataSource;

    private static final PooledConnectionProvider instance = new PooledConnectionProvider();

    private PooledConnectionProvider() {

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost/demo");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("root");

        hikariConfig.setMinimumIdle(5);

        hikariConfig.setMaximumPoolSize(1500);

        dataSource = new HikariDataSource(hikariConfig);

    }

    public static PooledConnectionProvider getInstance() {
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
