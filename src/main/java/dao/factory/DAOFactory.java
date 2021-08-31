package dao.factory;


import dao.BikeDAO;
import dao.OrderDAO;
import dao.UserDAO;
import dao.impl.SQLBikeDAOImpl;
import dao.impl.SQLOrderDAOImpl;
import dao.impl.SQLUserDAOImpl;

public class DAOFactory {
    private static final DAOFactory INSTANCE = new DAOFactory();
    private final UserDAO sqlUserDAO = SQLUserDAOImpl.getInstance();
    private final BikeDAO sqlBikeDAO = SQLBikeDAOImpl.getInstance();
    private final OrderDAO sqlOrderDAO = SQLOrderDAOImpl.getInstance();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    public UserDAO getSqlUserDAO() {
        return sqlUserDAO;
    }

    public BikeDAO getSqlBikeDAO() {
        return sqlBikeDAO;
    }

    public OrderDAO getSqlOrderDAO() { return sqlOrderDAO; }
}
