package service;

import bean.Order;
import service.exception.BusinessException;
import service.exception.ServiceException;

import java.util.List;

public interface OrderService {

// преобразование типов в контроллере
    void addOrder(Order order) throws ServiceException;

       Order getOrderById(int idOrder) throws ServiceException, BusinessException;

    List<Order> getOrders() throws ServiceException;

    void deleteOrder(int id) throws ServiceException;

    List<Order> getOrdersByUserId(int idUser) throws ServiceException;

    List<Order> getOrdersByBikeId(int idBook) throws ServiceException;

}
