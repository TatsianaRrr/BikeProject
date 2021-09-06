package dao;

import bean.User;
import dao.exception.DAOException;

import java.util.List;
import java.util.Optional;


public interface UserDAO {
    Optional<User> getUserByLoginAndPassword(User user) throws DAOException;

    User signUp(User user) throws DAOException;

    User addUser(User user) throws DAOException;

    List<User> getAllUsers() throws DAOException;

    Optional<User> getUserByLogin(String login) throws DAOException;

    Optional<User> getUserById(int id) throws DAOException;

    void deleteUserByLogin(String login) throws DAOException;

    User updateUser(User user) throws DAOException;

    public void setAdminRight(int idUser) throws DAOException;

    public void setUserRight(int idUser) throws DAOException;

}
