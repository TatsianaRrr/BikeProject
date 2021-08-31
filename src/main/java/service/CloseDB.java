package service;

import service.exception.ServiceException;

public interface CloseDB {

    void closeConnections() throws ServiceException;
}
