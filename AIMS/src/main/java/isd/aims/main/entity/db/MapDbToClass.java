package isd.aims.main.entity.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MapDbToClass<T> {
    T mapRow(ResultSet resultSet) throws SQLException;
}
