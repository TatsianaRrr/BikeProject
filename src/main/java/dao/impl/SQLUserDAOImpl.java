package dao.impl;

import bean.User;
import dao.UserDAO;
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

public class SQLUserDAOImpl implements UserDAO {
    private static final String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT `iduser`,`login`, `password`, `name`, `email`, `userRole`  FROM `user` WHERE `login`=? and `password`=?";
    private static final String SIGN_UP = "INSERT INTO `user` (`login`, `password`, `name`, `email`) VALUES (?, ?, ?, ?);";
    private static final String ADD_USER = "INSERT INTO `user` (`login`, `password`, `name`, `email`, `userRole`) VALUES ( ?, ?, ?, ?, ?);";
    private static final String GET_ALL_USERS = "SELECT iduser, login, password, name, email, userRole FROM `user`";
    private static final String DELETE_USER = "DELETE FROM `user` WHERE `login`=?";
    private static final String UPDATE_USER = "UPDATE `user` SET `iduser`=?, `login`=?,`password`=?, `name`=?, `email`=?, `userRole`=? WHERE `login`=?";
    private static final String GET_USER_BY_LOGIN = "SELECT `iduser`,`login`,`password`,`name`, `email`, `userRole` FROM `user` WHERE `login`=?";
    private static final String GET_USER_BY_ID = "SELECT `iduser`,`login`,`password`,`name`, `email`, `userRole` FROM `user` WHERE `iduser`=?";

    private static final Logger LOGGER = LoggerFactory.getLogger(SQLUserDAOImpl.class);

    private static SQLUserDAOImpl instance;

    public static synchronized SQLUserDAOImpl getInstance() {
        if (instance == null) {
            instance = new SQLUserDAOImpl();
        }
        return instance;
    }

    private SQLUserDAOImpl() {
    }


    @Override
    public Optional<User> getUserByLoginAndPassword(User user) throws DAOException {//sign in
        LOGGER.debug("UserDAOImpl.getUserByLoginAndPassword(), input data - user {}", user);
        LOGGER.debug("UserDAOImpl.getUserByLoginAndPassword() start");
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            ps = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = mapToUser(resultSet);
                LOGGER.debug("UserDAOImpl.getUserByLoginAndPassword() - success");
                return Optional.of(user);
            } else {
                return Optional.empty();
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database - UserDAOImpl.getUserByLoginAndPassword()", e);
        } catch (SQLException e) {
            throw new DAOException("Operation failed in database (UserDAOImpl.getUserByLoginAndPassword())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }

    @Override
    public User signUp(User user) throws DAOException {
        LOGGER.debug("UserDAOImpl.signUp(), input data - user {}", user);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(SIGN_UP);

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());

            ps.executeUpdate();
            connection.commit();
            LOGGER.debug("UserDAOImpl.signUp() - success");
            return user;
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database - UserDAOImpl.signUp()", e);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Operation failed in rollback", e);
            }
            throw new DAOException("Operation failed in database (UserDAOImpl.signUp())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }

    @Override
    public User addUser(User user) throws DAOException {
        LOGGER.debug("UserDAOImpl.addUser(), input data - user {}", user);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(ADD_USER);

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getUserRole().getName());

            ps.executeUpdate();
            connection.commit();
            LOGGER.debug("UserDAOImpl.addUser() - success");
            return user;
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database - UserDAOImpl.addUser()", e);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Operation failed in rollback (UserDAOImpl.addUser())", e);
            }
            throw new DAOException("Operation failed in database (UserDAOImpl.addUser())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        LOGGER.debug("UserDAOImpl.getAllUsers(), have no input data");
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<User> users = new LinkedList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            ps = connection.prepareStatement(GET_ALL_USERS);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = mapToUser(resultSet);
                users.add(user);
            }
            LOGGER.debug("UserDAOImpl.getAllUsers() - success");
            return users;
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database - UserDAOImpl.getAllUsers()", e);
        } catch (SQLException e) {
            throw new DAOException("Operation failed in database (UserDAOImpl.getAllUsers())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }

    @Override
    public Optional<User> getUserByLogin(String login) throws DAOException {
        LOGGER.debug("UserDAOImpl.getUserByLogin(), input data - login {}", login);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        User user;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            ps = connection.prepareStatement(GET_USER_BY_LOGIN);
            ps.setString(1, login);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = mapToUser(resultSet);
                LOGGER.debug("UserDAOImpl.getUserByLogin() - success");
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database - UserDAOImpl.getUserByLogin()", e);
        } catch (SQLException e) {
            throw new DAOException("Operation failed in database (UserDAOImpl.getUserByLogin())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }

    @Override
    public Optional<User> getUserById(int id) throws DAOException {
        LOGGER.debug("UserDAOImpl.getUserById(), input data - id {}", id);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        User user;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            ps = connection.prepareStatement(GET_USER_BY_ID);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = mapToUser(resultSet);
                LOGGER.debug("UserDAOImpl.getUserById() - success");
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database - UserDAOImpl.getUserById()", e);
        } catch (SQLException e) {
            throw new DAOException("Operation failed in database (UserDAOImpl.getUserById())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }

    @Override
    public User updateUser(User user) throws DAOException {
        LOGGER.debug("UserDAOImpl.updateUser(), input data - user {}", user);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_USER);

            ps.setInt(1, user.getId());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getUserRole().getName());
            ps.setString(7, user.getLogin());
            ps.executeUpdate();
            connection.commit();
            LOGGER.debug("UserDAOImpl.updateUser() - success");
            return user;
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database - UserDAOImpl.updateUser()", e);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Operation failed in rollback (UserDAOImpl.updateUser())", e);
            }
            throw new DAOException("Operation failed in database (UserDAOImpl.updateUser())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }

    @Override
    public void deleteUserByLogin(String login) throws DAOException {
        LOGGER.debug("UserDAOImpl.deleteUser(), input data - login {}", login);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        User user = new User();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(DELETE_USER);
            ps.setString(1, login);
            ps.executeUpdate();
            connection.commit();
            LOGGER.debug("UserDAOImpl.getUserByLogin() - success");
        } catch (ConnectionPoolException e) {
            throw new DAOException("can't get connection in database - UserDAOImpl.deleteUser()", e);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Operation failed in rollback (UserDAOImpl.deleteUser())", e);
            }
            throw new DAOException("Operation failed in database (UserDAOImpl.deleteUser())", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, resultSet, ps);
            }
        }
    }

    private User mapToUser(ResultSet resultSet) throws SQLException {
        LOGGER.debug("UserDAOImpl.setUserParameters(), input data - resultSet {}", resultSet);
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setLogin(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setName(resultSet.getString(4));
        user.setEmail(resultSet.getString(5));
        user.setUserRole(User.UserRole.valueOf(resultSet.getString(6).toUpperCase()));
        LOGGER.debug("UserDAOImpl.setUserParameters() - success");
        return user;
    }


}

