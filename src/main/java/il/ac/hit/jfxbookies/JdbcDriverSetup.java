package il.ac.hit.jfxbookies;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class JdbcDriverSetup {

    private static ConnectionSource connectionSource;


    public static ConnectionSource getConnection() {
        synchronized (JdbcDriverSetup.class) {
            if (connectionSource == null) {
                try {
                    connectionSource = new JdbcConnectionSource("jdbc:sqlite:src/main/resources/bookies.db");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            return connectionSource;
        }

    }

    public static <T, ID> Dao<T, ID> getDao(Class<T> clazz) {
        return DaoManager.lookupDao(JdbcDriverSetup.getConnection(), clazz);
    }

    public static <T> void createDao(Class<T> clazz) throws SQLException {
        DaoManager.createDao(getConnection(), clazz);
    }

}
