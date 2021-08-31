package dao;

import bean.Bike;
import dao.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface BikeDAO {

    List<Bike> getBikes() throws DAOException;

    List<Bike> getBikesByYearDesc() throws DAOException;

    Bike addBike(Bike bike) throws DAOException;

    void deleteBike(int idBike) throws DAOException;

    void deleteBike(Bike bike) throws DAOException;

    Optional<Bike> getBikeById(int idBike) throws DAOException;

    Bike updateBike(Bike bike) throws DAOException;
}
