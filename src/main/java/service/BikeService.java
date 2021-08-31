package service;



import bean.Bike;
import service.exception.BusinessException;
import service.exception.ServiceException;

import java.util.List;

public interface BikeService {

// преобразование типов в контроллере
    void addBike(Bike bike) throws ServiceException;

    Bike updateBike(Bike bike) throws ServiceException;

    Bike getBikeById(int idBike) throws ServiceException, BusinessException;

    List<Bike> getBikes()throws ServiceException;

    List<Bike> getBikesByYearDesc() throws ServiceException;

    void deleteBike(int id) throws ServiceException;

    List<Bike> sortBikeByName() throws ServiceException;

    List<Bike> sortBikeByYear() throws ServiceException;





}
