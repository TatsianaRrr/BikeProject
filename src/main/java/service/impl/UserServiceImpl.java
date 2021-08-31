package service.impl;

import bean.User;
import dao.UserDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import service.exception.BusinessException;
import service.exception.ServiceException;
import service.util.Util;
import service.util.exception.UtilException;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static final UserDAO USER_DAO = daoFactory.getSqlUserDAO();

    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User signUp(String userLogin, String userPassword, String userEmail,
                       String userName) throws ServiceException {
        LOGGER.debug("UserServiceImpl.signUp(), input data - userLogin {}, userPassword {}, userEmail {}, userName {} ",
                userLogin, userPassword, userEmail, userName);
        User user;

        try {
            //  Util.isNull(userLogin, userPassword, userEmail, userName);
            //  Util.isEmptyString(userLogin, userPassword, userEmail, userName);
            //  Util.matchCorrectString(userName);
            //  Util.matchEmail(userEmail);
            user = new User(userLogin, userPassword, userEmail, userName);
            User result = USER_DAO.signUp(user);
            LOGGER.debug("UserServiceImpl.signUp() - success");
            return result;
        } catch (/*UtilException | */DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addUser(User user) throws ServiceException {
        LOGGER.debug("UserServiceImpl.addUser(), input data - user {}", user);
        try {
            USER_DAO.addUser(user);
            LOGGER.debug("UserServiceImpl.addUser() - success");
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User signIn(String userLogin, String userPassword) throws ServiceException {
        LOGGER.debug("UserServiceImpl.signIn(), input data - userLogin {}, userPassword {}", userLogin, userPassword);
        User user = null;
        try {
            Util.isNull(userLogin, userPassword);
            Util.isEmptyString(userLogin, userPassword);
            user = new User(userLogin, userPassword);
            Optional<User> result = USER_DAO.getUserByLoginAndPassword(user);
            if (result.isPresent()) {
                LOGGER.debug("UserServiceImpl.signIn() - success");
                return result.get();
            } else {
                throw new BusinessException(" cant find bike with this login " + userLogin + " and password " + userPassword);
            }
        } catch (DAOException | UtilException | BusinessException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException, BusinessException {
        LOGGER.debug("UserServiceImpl.getUserByLogin(), input data - login {}", login);
        try {
            Optional<User> userOptional = USER_DAO.getUserByLogin(login);
            if (userOptional.isPresent()) {
                LOGGER.debug("UserServiceImpl.getUserByLogin() - success");
                return userOptional.get();
            } else {
                throw new BusinessException("cant find user with this login " + login);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserById(int id) throws ServiceException, BusinessException {
        LOGGER.debug("UserServiceImpl.getUserById(), input data - id {}", id);
        try {
            Optional<User> userOptional = USER_DAO.getUserById(id);
            if (userOptional.isPresent()) {
                LOGGER.debug("UserServiceImpl.getUserById() - success");
                return userOptional.get();
            } else {
                throw new BusinessException("cant find user with this id " + id);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        LOGGER.debug("UserServiceImpl.getAllUsers(), no input data");
        List<User> list;
        try {
            list = USER_DAO.getAllUsers();
            LOGGER.debug("UserServiceImpl.getAllUsers() - success");
            return list;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User updateUser(User user) throws ServiceException {
        LOGGER.debug("UserServiceImpl.updateUser(), input data - user {}", user);
        User result;
        try {
            result = USER_DAO.updateUser(user);
            LOGGER.debug("UserServiceImpl.updateUser() - success");
            return result;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(String login) throws ServiceException {
        LOGGER.debug("UserServiceImpl.getUserByLogin(), input data - login {}", login);
        try {
            USER_DAO.deleteUserByLogin(login);
            LOGGER.debug("UserServiceImpl.deleteUser() - success");
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setDaoFactory(DAOFactory daoFactory) {
        UserServiceImpl.daoFactory = daoFactory;
    }
}
