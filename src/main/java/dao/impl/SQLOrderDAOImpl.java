package dao.impl;

import bean.Order;
import dao.OrderDAO;
import dao.exception.ConnectionPoolException;
import dao.exception.DAOException;
import dao.pool.impl.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SQLOrderDAOImpl implements OrderDAO {
    private static Logger logger = LoggerFactory.getLogger(SQLOrderDAOImpl.class);
    private static final String ADD_ORDER = "INSERT INTO `order` (`user_iduser`, `bike_idbike`, `description`) VALUES (?, ?, ?)";
    private static final String GET_ALL_ORDERS = "SELECT  `idorder`, `user_iduser`, `bike_idbike`, `description` FROM `order`";
    private static final String DELETE_ORDER = "DELETE from `order` where `idorder` = ?;";
    private static final String UPDATE_ORDER = "UPDATE `order` set `description` = ? where idorder = ?;";
    private static final String GET_ORDER_BY_ID = "SELECT  `idorder`, `user_iduser`, `bike_idbike`, `description` FROM `order` WHERE `idorder`=?";
    private static final String GET_ORDERS_BY_BIKE_ID = "SELECT `order`.`idorder`, `order`.`user_iduser`, `bike`.`idbike`, `order`.`description` " +
            "FROM mydb.bike " +
            "join mydb.order on mydb.order.bike_idbike=mydb.bike.idbike " +
            "where `order`.`user_iduser`=? " +
            "and `order`.`description` is not null " +
            "order by `order`.`idorder`";

    private static final String GET_ORDERS_BY_USER_ID = "SELECT `order`.`idorder`, `user`.`iduser`, `order`.`bike_idbike`, `order`.`description` " +
            "FROM mydb.user " +
            "join mydb.order on mydb.order.user_iduser=mydb.user.iduser " +
            "where `order`.`bike_idbike`=? " +
            "and `order`.`description` is not null " +
            "order by `order`.`idorder`";


    private static final String SORT_ORDERS_BY_ID = " SELECT * FROM `order` ORDER BY `id` ";
    //private static final String SORT_ORDERS_BY_PRICE = " SELECT * FROM `order` ORDER BY `price` ASC"; //по возрастанию цены


    private static SQLOrderDAOImpl instance;

    public static synchronized SQLOrderDAOImpl getInstance() {
        if (instance == null) {
            instance = new SQLOrderDAOImpl();
        }
        return instance;
    }

    private SQLOrderDAOImpl() {
    }

    private Order mapToOrder(ResultSet resultSet) throws SQLException {
        logger.debug("OrderDAOImpl.mapToOrder(), input data - resultSet {}", resultSet);
        Order order = new Order();
        order.setId(resultSet.getInt(1));
        order.setBikeId(resultSet.getInt(2));
        order.setUserId(resultSet.getInt(3));
        order.setDescription(resultSet.getString(4));
        logger.debug("OrderDAOImpl.mapToOrder() - success");
        return order;
    }

    @Override
    public List<Order> getOrders() throws DAOException {
        logger.debug("OrderDAOImpl.getOrders() start, no input data");
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Order> orders = new LinkedList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            ps = connection.prepareStatement(GET_ALL_ORDERS);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Order order = mapToOrder(resultSet);
                orders.add(order);
            }
            logger.debug("OrderDAOImpl.getAllOrders() - success");
            return orders;
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database (OrderDAOImpl.getAllOrders())", e);
        } catch (SQLException e) {
            throw new DAOException("Operation failed in database (OrderDAOImpl.getAllOrders())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }

    @Override
    public Optional<Order> getOrderById(int idOrder) throws DAOException {
        logger.debug("OrderDAOImpl.getOrderById() start, input data - idOrder {}", idOrder);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Order order;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            ps = connection.prepareStatement(GET_ORDER_BY_ID);
            ps.setInt(1, idOrder);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                order = mapToOrder(resultSet);
                logger.debug("OrderDAOImpl.getOrderById() - success");
                return Optional.of(order);//optional
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("wrong sql request - OrderDAOImpl.getOrderById()", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("no have connection - OrderDAOImpl.getOrderById()", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }

    }

    @Override
    public Order addOrder(Order order) throws DAOException {
        logger.debug("OrderDAOImpl.addOrder() start, input data - order {}", order);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ADD_ORDER);

            //  preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getBikeId());
            preparedStatement.setString(3, order.getDescription());

            preparedStatement.executeUpdate();
            connection.commit();
            logger.debug("OrderDAOImpl.addOrder() - success");
            return order;
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database (OrderDAOImpl.addOrder())", e);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Operation failed in rollback (OrderDAOImpl.addOrder())", e);
            }
            throw new DAOException("Operation failed in database (OrderDAOImpl.addOrder())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, preparedStatement);
            }
        }
    }


    @Override
    public void deleteOrder(int idOrder) throws DAOException {
        logger.debug("OrderDAOImpl.deleteOrder() start, input data - idOrder {}", idOrder);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_ORDER);
            preparedStatement.setInt(1, idOrder);
            preparedStatement.executeUpdate();
            connection.commit();
            logger.debug("OrderDAOImpl.deleteOrder() - success");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Operation failed in rollback (OrderDAOImpl.deleteOrder())", e);

            }
            throw new DAOException("error deleting Order", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("no database connection (OrderDAOImpl.deleteOrder())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, null, preparedStatement);
            }
        }
    }

    @Override
    public void deleteOrder(Order order) {
        logger.debug("OrderDAOImpl.deleteOrder(), input data - order {}", order);
        throw new UnsupportedOperationException("method deleteOrder(Order order) is not supported yet");
    }


    @Override
    public List<Order> getOrdersByUserId(int idUser) throws DAOException {
        logger.debug("OrderDAOImpl.getOrdersByUserId() start, input data - idUser {}", idUser);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Order> orders = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            ps = connection.prepareStatement(GET_ORDERS_BY_USER_ID);
            ps.setInt(1, idUser);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Order order = mapToOrder(resultSet);
                orders.add(order);
            }
            logger.debug("OrderDAOImpl.getOrdersByUserId() - success");
            return orders;
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database - OrderDAOImpl.getOrdersByUserId()", e);
        } catch (SQLException e) {
            throw new DAOException("Operation failed in database (OrderDAOImpl.getOrdersByUserId())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }

    @Override
    public List<Order> getOrdersByBikeId(int idBike) throws DAOException {
        logger.debug("OrderDAOImpl.getOrdersByBikeId() start, input data - idBike {}", idBike);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Order> orders = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            ps = connection.prepareStatement(GET_ORDERS_BY_BIKE_ID);
            ps.setInt(1, idBike);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Order order = mapToOrder(resultSet);
                orders.add(order);
            }
            logger.debug("OrderDAOImpl.getOrdersByBikeId() - success");
            return orders;
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database (OrderDAOImpl.getOrdersByBikeId())", e);
        } catch (SQLException e) {
            throw new DAOException("Operation failed in database (OrderDAOImpl.getOrdersByBikeId())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }
}
