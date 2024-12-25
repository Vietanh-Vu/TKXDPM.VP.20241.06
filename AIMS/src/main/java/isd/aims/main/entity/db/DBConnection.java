package isd.aims.main.entity.db;

import java.sql.Connection;

public interface DBConnection {
    Connection getConnection();
}