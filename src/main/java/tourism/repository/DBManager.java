package tourism.repository;

import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DBManager {

    private static volatile DBManager instance;
    private final DataSource dataSource;

    @Autowired
    private DBManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static DBManager getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException, SQLException {
        return dataSource.getConnection();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setInstance(DBManager instance) {
        DBManager.instance = instance;
    }
}
