package service.util;

import dao.exception.ConnectionPoolException;
import dao.pool.CloseConnectionPool;
import dao.pool.impl.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CloseDB;
import service.exception.ServiceException;

public class CloseDBServiceImpl implements CloseDB {
    private static Logger logger = LoggerFactory.getLogger(CloseDBServiceImpl.class);
    public void closeConnections()throws ServiceException {
        logger.debug("Service.closeConnection()");
        try {
            CloseConnectionPool pool = ConnectionPool.getInstance();
            pool.releasePool();
        } catch (ConnectionPoolException e) {
            throw new ServiceException(e);
        }
        logger.debug("Service.closeConnection() - success");
    }
}
