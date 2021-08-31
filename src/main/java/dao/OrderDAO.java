package dao;

import bean.Order;
import dao.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {

    List<Order> getOrders() throws DAOException;

    Order addOrder(Order order) throws DAOException;

    void deleteOrder(int idOrder) throws DAOException;

    void deleteOrder(Order order) throws DAOException;

    Optional<Order> getOrderById(int idOrder) throws DAOException;

    List<Order> getOrdersByUserId(int idUser) throws DAOException;

    List<Order> getOrdersByBikeId(int idBook) throws DAOException;
}
