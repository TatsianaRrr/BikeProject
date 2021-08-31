package dao.impl;

import bean.Bike;
import dao.BikeDAO;
import dao.exception.ConnectionPoolException;
import dao.exception.DAOException;
import dao.pool.impl.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SQLBikeDAOImpl implements BikeDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(SQLBikeDAOImpl.class);
    private static final String ADD_BIKE = "INSERT INTO `bike` ( `name`, `year`, `description`, `price`, `image`, `ordered`, `deleted`) VALUES (  ?, ?, ?, ?, ?, ?, ? );";
    private static final String GET_ALL_BIKES = "SELECT `idbike`, `name`, `year`, `description`, `price`, `image`, `ordered`, `deleted` FROM `bike`";
    private static final String DELETE_BIKE = "DELETE from `bike` where `idbike` = ?;";
    private static final String UPDATE_BIKE = "UPDATE `bike` SET `idbike`=?, `name`=?, `year`=?, `description`=?, `price`=?, `image`=?, `ordered`=?, `deleted`=? " +
            "WHERE `idbike`=?";
    private static final String GET_BIKE_BY_NAME = "SELECT `idbike`, `name`, `year`, `description`, `price`, `image`, `ordered`, `deleted` FROM `bike` " +
            "WHERE `name`=?";
    private static final String GET_BIKE_BY_ID = "SELECT `idbike`, `name`, `year`, `description`, `price`, `image`, `ordered`, `deleted` FROM `bike` " +
            "WHERE `idbike`=?";
    private static final String GET_BIKES_BY_YEAR = "SELECT `idbike`, `name`, `year`, `description`, `price`, `image`, `ordered`, `deleted` FROM `bike` " +
            "WHERE `year`=?";
    private static final String GET_BIKES_BY_PRICE = "SELECT `idbike`, `name`, `year`, `description`, `price`, `image`, `ordered`, `deleted` FROM `bike` " +
            "WHERE `price`=?";


    private static final String SORT_BIKES_BY_YEAR_ASC = " SELECT * FROM `bike` ORDER BY `year` ASC";//по возрастанию года - сначала старые, потом новые
    private static final String SORT_BIKES_BY_YEAR_DESC = " SELECT * FROM `bike` ORDER BY `year` DESC";//по возрастанию года - сначала новые, потом старые
    private static final String SORT_BIKES_BY_ID = " SELECT * FROM `bike` ORDER BY `idbike` ";
    private static final String SORT_BIKES_BY_NAME = " SELECT * FROM `bike` ORDER BY `name` ";
    private static final String SORT_BIKES_BY_PRICE_ASC = " SELECT * FROM `bike` ORDER BY `price` ASC"; //по возрастанию цены
/*?characterEncoding=utf8*/

    private static SQLBikeDAOImpl instance;

    public static synchronized SQLBikeDAOImpl getInstance() {
        if (instance == null) {
            instance = new SQLBikeDAOImpl();
        }
        return instance;
    }

    private SQLBikeDAOImpl() {
    }

    private Bike mapToBike(ResultSet resultSet) throws SQLException {
        LOGGER.debug("BikeDAOImpl.mapToBike() start");
        LOGGER.debug("BikeDAOImpl.mapToBike(), input data - resultSet {}", resultSet);
        Bike bike = new Bike();
        bike.setId(resultSet.getInt(1));
        bike.setName(resultSet.getString(2));
        bike.setYear(resultSet.getInt(3));
        bike.setDescription(resultSet.getString(4));
        bike.setPrice(resultSet.getDouble(5));
        bike.setImage(resultSet.getString(6));
        bike.setOrdered(resultSet.getBoolean(7));
        bike.setDeleted(resultSet.getBoolean(8));
        LOGGER.debug("BikeDAOImpl.mapToBike() - success");
        return bike;
    }

    @Override
    public List<Bike> getBikes() throws DAOException {
        LOGGER.debug("BikeDAOImpl.getBikes() start");
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Bike> bikes = new LinkedList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            ps = connection.prepareStatement(GET_ALL_BIKES);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Bike bike = mapToBike(resultSet);
                bikes.add(bike);
            }
            LOGGER.debug("BikeDAOImpl.getBikes() - success");
            return bikes;
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database - BikeDAOImpl.getBikes()", e);
        } catch (SQLException e) {
            throw new DAOException("Operation failed in database (BikeDAOImpl.getBikes())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }
    @Override
    public List<Bike> getBikesByYearDesc() throws DAOException {
        LOGGER.debug("BikeDAOImpl.GetBikesByYearDesc() start");
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Bike> bikes = new LinkedList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            ps = connection.prepareStatement(SORT_BIKES_BY_YEAR_DESC);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Bike bike = mapToBike(resultSet);
                bikes.add(bike);
            }
            LOGGER.debug("BikeDAOImpl.GetBikesByYearDesc() - success");
            return bikes;
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database - BikeDAOImpl.GetBikesByYearDesc()", e);
        } catch (SQLException e) {
            throw new DAOException("Operation failed in database (BookDAOImpl.GetBikesByYearDesc())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }

    @Override
    public Optional<Bike> getBikeById(int idBike) throws DAOException {
        LOGGER.debug("BikeDAOImpl.getBike() start");
        LOGGER.debug("BikeDAOImpl.getBike(), input data - idBike {}", idBike);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Bike bike;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            ps = connection.prepareStatement(GET_BIKE_BY_ID);
            ps.setInt(1, idBike);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                bike = mapToBike(resultSet);
                LOGGER.debug("BikeDAOImpl.getBikeById() - success");
                return Optional.of(bike);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("no have connection - BikeDAOImpl.getBike()", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }

    @Override
    public Bike addBike(Bike bike) throws DAOException {
        LOGGER.debug("BikeDAOImpl.addBike() start");
        LOGGER.debug("BikeDAOImpl.addBike(), input data - bike {}", bike);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ADD_BIKE);
            preparedStatement.setString(1, bike.getName());
            preparedStatement.setInt(2, bike.getYear());
            preparedStatement.setString(3, bike.getDescription());
            preparedStatement.setDouble(4, bike.getPrice());
            preparedStatement.setString(5, bike.getImage());
            preparedStatement.setBoolean(6, bike.isDeleted());
            preparedStatement.setBoolean(7, bike.isOrdered());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.debug("BikeDAOImpl.addBike() - success");//
            return bike;
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database (BikeDAOImpl.addBike())", e);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Operation failed in rollback (BikeDAOImpl.addBike())", e);
            }
            throw new DAOException("Operation failed in database (BikeDAOImpl.addBike())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, preparedStatement);
            }
        }
    }

    @Override
    public Bike updateBike(Bike bike) throws DAOException {
        LOGGER.debug("BikeDAOImpl.updateBike(), input data - bike {}", bike);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_BIKE);

            ps.setInt(1, bike.getId());
            ps.setString(2, bike.getName());
            ps.setInt(3, bike.getYear());
            ps.setString(4, bike.getDescription());
            ps.setDouble(5, bike.getPrice());
            ps.setString(6, bike.getImage());
            ps.setBoolean(7, bike.isDeleted());
            ps.setBoolean(8, bike.isOrdered());
            ps.setInt(9, bike.getId());
            ps.executeUpdate();
            connection.commit();
            LOGGER.debug("BikeDAOImpl.updateBike() - success");
            return bike;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("Operation failed in rollback (BikeDAOImpl.updateBike())", e);
            }
            throw new DAOException("Operation failed in database (BikeDAOImpl.updateBike())", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database (BikeDAOImpl.updateBike())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }

    }

    @Override
    public void deleteBike(int idBike) throws DAOException {
        LOGGER.debug("BikeDAOImpl.deleteBike(), input data - idBike {}", idBike);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BIKE);
            preparedStatement.setInt(1, idBike);
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.debug("BookDAOImpl.deleteBike() - success");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("Operation failed in rollback (BikeDAOImpl.deleteBike())", e);
            }
            throw new DAOException("SQL exception in BikeDAOImpl.deleteBike()", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("no database connection - BikeDAOImpl.deleteBike()", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, null, preparedStatement);
            }
        }
    }

    @Override
    public void deleteBike(Bike bike) {
        LOGGER.debug("BikeDAOImpl.deleteBike(), input data - bike {}", bike);
        throw new UnsupportedOperationException("method deleteBike(Bike bike) is not supported yet");
    }
}
