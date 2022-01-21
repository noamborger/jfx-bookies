package il.ac.hit.jfxbookies;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class JdbcDriverSetup {

    private static ConnectionSource connectionSource;

    @Autowired
    private JdbcDriverSetup(ConnectionSource connectionSource) {
        JdbcDriverSetup.connectionSource = connectionSource;
    }

    public static <T, ID> Dao<T, ID> getDao(Class<T> clazz) {
        return DaoManager.lookupDao(connectionSource, clazz);
    }

}
