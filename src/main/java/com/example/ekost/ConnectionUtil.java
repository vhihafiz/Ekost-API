package com.example.ekost;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtil {
    private static final HikariDataSource dataSource ;

    static {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/tokonyadia_db");
        config.setUsername("postgres");
        config.setPassword("postgres");

        config.setMaximumPoolSize(10); // Menentukan ukuran connection pool
        config.setMinimumIdle(5); // Menentukan jumlah koneksi pada saat tidak sampai poolsize maksimum
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10*60_000);

        dataSource = new HikariDataSource(config);

    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}
