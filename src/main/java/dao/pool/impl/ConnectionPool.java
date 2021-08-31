package dao.pool.impl;

import dao.exception.ConnectionPoolException;
import dao.pool.CloseConnectionPool;
import dao.pool.DBParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPool implements CloseConnectionPool {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;
    private final BlockingQueue<Connection> connectionQueue;
    private final BlockingQueue<Connection> givenAwayConQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;


    private ConnectionPool() throws ConnectionPoolException {
        int poolSize;
        try {
            DBResourceManager dbResourceManager = DBResourceManager.getInstance();
            logger.info("System found database property file");
            this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
            this.user = dbResourceManager.getValue(DBParameter.DB_USER);
            this.url = dbResourceManager.getValue(DBParameter.DB_URL);
            this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
            poolSize = Integer.parseInt(dbResourceManager.getValue((DBParameter.DB_POOL_SIZE)));
            Class.forName(driverName);
            logger.info("Database driver was loaded");
        } catch (NumberFormatException e) {
            logger.warn("No correct value in database property file");
            poolSize = 5;
        } catch (ClassNotFoundException e) {
            logger.error("Driver load exception: " + driverName, e);
            throw new ConnectionPoolException("Driver load exception: " + driverName, e);
        } catch (MissingResourceException e) {
            logger.error("Error of upload config: ", e);
            throw new ConnectionPoolException("Error of upload config: ", e);
        }
        connectionQueue = new ArrayBlockingQueue<>(poolSize);
        givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            connectionQueue.add(getConnection());
        }
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {//Высокая производительность
        ConnectionPool localInstance = instance;
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool();
                }
            }
        }
        return localInstance;
    }

    private Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Get connection from database");
            return connection;
        } catch (SQLException e) {
            logger.warn("Can't get connection from database", e);
            throw new ConnectionPoolException("Can't get connection from database", e);
        }
    }

    public Connection retrieve() throws ConnectionPoolException {//извлечь
        Connection connection;
        if (connectionQueue.size() == 0) {
            connection = getConnection();
        } else {
            try {
                connection = connectionQueue.take();
            } catch (InterruptedException e) {
                logger.warn("Can't get connection from database", e);
                throw new ConnectionPoolException("Can't get connection from database", e);
            }
        }
        givenAwayConQueue.add(connection);
        return connection;
    }

    //не выбрасываю исключение на верхний слой, т.к. считаю, что нужно
    // попытаться забрать все объекты в данных методах
    public void putBackConnection(Connection con, ResultSet resultSet, Statement st) {
        if (con != null) {
            givenAwayConQueue.remove(con);
            connectionQueue.add(con);
            logger.info("Connection is back");
        } else {
            logger.info("Connection is null");
        }

        if (resultSet != null) {
            try {
                resultSet.close();
                logger.info("resultSet  closed");
            } catch (SQLException e) {
                logger.info("resultSet don't closed", e);
            }
        }
        if (st != null) {
            try {
                st.close();
                logger.info("statement closed");
            } catch (SQLException e) {
                logger.info("statement don't closed", e);
            }
        }
    }

    @Override
    public void releasePool() {
        while (!givenAwayConQueue.isEmpty()) {
            try {
                Connection connection = givenAwayConQueue.take();
                connection.close();
            } catch (InterruptedException | SQLException e) {
                logger.error("Can't close con", e);
            }
        }
        while (!connectionQueue.isEmpty()) {
            try {
                Connection connection = connectionQueue.take();
                connection.close();
            } catch (InterruptedException | SQLException e) {
                logger.error("Can't close con", e);
            }
        }
    }

    public static final class DBResourceManager {
        private static final DBResourceManager instance = new DBResourceManager();
        private final ResourceBundle bundle = ResourceBundle.getBundle("db.db");

        private DBResourceManager() {
        }

        public static DBResourceManager getInstance() {
            return instance;
        }

        public String getValue(String key) {
            return bundle.getString(key);
        }
    }
}



