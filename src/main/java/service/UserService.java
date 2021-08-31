package service;

import bean.User;
import dao.factory.DAOFactory;
import service.exception.BusinessException;
import service.exception.ServiceException;

import java.util.List;


public interface UserService {
    User signUp( String userLogin, String userPassword, String userEmail,
                String userName) throws ServiceException;
    void addUser(User user) throws ServiceException;
    User signIn(String userLogin, String userPassword) throws ServiceException;
    User getUserByLogin(String userLogin) throws ServiceException, BusinessException;
    User getUserById(int id) throws ServiceException, BusinessException;
    List<User> getAllUsers() throws ServiceException;
    User updateUser(User user) throws ServiceException;
    void deleteUser(String login) throws ServiceException;
    void setDaoFactory(DAOFactory daoFactory);
}
