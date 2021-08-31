package service.impl;

import bean.Order;
import dao.OrderDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.OrderService;
import service.exception.BusinessException;
import service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
    private static final OrderDAO ORDER_DAO = DAO_FACTORY.getSqlOrderDAO();

    private static OrderServiceImpl instance;

    private OrderServiceImpl() {
    }

    public static synchronized OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public void addOrder(Order order) throws ServiceException {
        LOGGER.debug("OrderServiceImpl.addOrder(), input data - order {}", order);
        try {
            ORDER_DAO.addOrder(order);
            LOGGER.debug("OrderServiceImpl.addOrder - success");
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

       @Override
    public Order getOrderById(int idOrder) throws ServiceException, BusinessException {
        LOGGER.debug("OrderServiceImpl.getOrder(), input data - idOrder {}", idOrder);
        try {
            Optional<Order> optional = ORDER_DAO.getOrderById(idOrder);
            if (optional.isPresent()) {
                LOGGER.debug("OrderServiceImpl.getOrderById() - success");
                return optional.get();
            } else {
                throw new BusinessException(" can not find order with this id" + idOrder);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrders() throws ServiceException {
        LOGGER.debug("OrderServiceImpl.getOrders - run, no input data");
        List<Order> list;
        try {
            list = ORDER_DAO.getOrders();
            LOGGER.debug("OrderServiceImpl.getOrders() - success)");
            return list;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteOrder(int id) throws ServiceException {
        LOGGER.debug("OrderServiceImpl.deleteOrder(), input data - id {}", id);
        try {
            ORDER_DAO.deleteOrder(id);
            LOGGER.debug("OrderServiceImpl.deleteOrder() - success");
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int idUser) throws ServiceException {
        LOGGER.debug("OrderServiceImpl.getOrderByUserId(), input data - idUser {}", idUser);
        List<Order> list;
        try {
            list= ORDER_DAO.getOrdersByUserId(idUser);
            LOGGER.debug("OrderServiceImpl.getOrderByUserId() - success");
            return list;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrdersByBikeId(int idBike) throws ServiceException {
        LOGGER.debug("OrderServiceImpl.getOrderByBikeId(), input data - idBike {}", idBike);
        try {
            LOGGER.debug("OrderServiceImpl.getOrderByBikeId() - success");
            return DAO_FACTORY.getSqlOrderDAO().getOrdersByBikeId(idBike);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
