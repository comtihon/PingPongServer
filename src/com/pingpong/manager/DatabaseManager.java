package com.pingpong.manager;

import com.pingpong.core.Logger;
import com.pingpong.server.Config;
import org.h2.jdbcx.JdbcConnectionPool;

import java.io.File;
import java.sql.*;

/**
 * Created by tihon on 04.04.14.
 */
public class DatabaseManager {

    private JdbcConnectionPool pool;
    private String tableName;

    private static class SingletonHolder {
        private static final DatabaseManager instance = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return SingletonHolder.instance;
    }

    public void connectToDB() throws ClassNotFoundException, SQLException {
        Config config = Config.getInstance();
        String pathToDB = config.getProperty("path_to_db");
        String user = config.getProperty("db_user");
        tableName = config.getProperty("db_table");
        if (pathToDB == null || pathToDB.isEmpty()
                || user == null || user.isEmpty()
                || tableName == null || tableName.isEmpty())
            throw new UnsupportedOperationException("Database config is not set properly!");

        Class.forName("org.h2.Driver");

        if (!new File(pathToDB + ".h2.db").exists())
            createDB(pathToDB, user);

        pool = JdbcConnectionPool.create("jdbc:h2:" + pathToDB + ";CACHE_SIZE=32000;", user, "");
    }

    public int findUser(String userId) throws SQLException {
        Connection connection = null;
        ResultSet res = null;
        try {
            connection = pool.getConnection();
            res = connection.createStatement().executeQuery(
                    String.format("Select ping from %s " +
                                    "where uid='%s';",
                            tableName, userId
                    )
            );
            if (res.next()) {
                return res.getInt("ping");
            }
        } catch (SQLException e) {
            Logger.e("Error getting connection from pool");
            e.printStackTrace();
        } finally {
            if (res != null)
                res.close();
            if (connection != null)
                connection.close();
        }
        return -1;
    }

    /**
     * Creates database and table for storing Pingers
     *
     * @param pathToDb
     * @param user
     * @throws SQLException
     */
    private void createDB(String pathToDb, String user) throws SQLException {
        Connection connectionToDB = DriverManager.getConnection("jdbc:h2:" + pathToDb, user, "");
        connectionToDB.setAutoCommit(false);

        Statement stmt = connectionToDB.createStatement();
        stmt.executeUpdate("CREATE TABLE " + tableName +
                " (uid VARCHAR(50) PRIMARY KEY NOT NULL, " +
                " ping BIGINT)");
        stmt.executeUpdate("CREATE INDEX uid_idx ON " + tableName + "(uid)");
        stmt.close();
        connectionToDB.commit();
        connectionToDB.close();
    }
}
