package service.factory;

import service.impl.BikeServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
    private final BikeServiceImpl bikeServiceImpl = BikeServiceImpl.getInstance();
    private final OrderServiceImpl  orderServiceImpl =  OrderServiceImpl.getInstance();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserServiceImpl getUserServiceImpl() {
        return userServiceImpl;
    }

    public BikeServiceImpl getBikeServiceImpl() {
        return bikeServiceImpl;
    }

    public OrderServiceImpl getOrderServiceImpl() {
        return orderServiceImpl;
    }
}
