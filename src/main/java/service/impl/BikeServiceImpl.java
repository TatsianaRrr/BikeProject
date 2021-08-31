package service.impl;

import bean.Bike;
import dao.BikeDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.BikeService;
import service.exception.BusinessException;
import service.exception.ServiceException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BikeServiceImpl implements BikeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BikeServiceImpl.class);
    private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
    private final static BikeDAO SQL_BIKE_DAO = DAO_FACTORY.getSqlBikeDAO();
    private static BikeServiceImpl instance;

    private BikeServiceImpl() {
    }

    public static synchronized BikeServiceImpl getInstance() {
        if (instance == null) {
            instance = new BikeServiceImpl();
        }
        return instance;
    }

    @Override
    public void addBike(Bike bike) throws ServiceException {
        LOGGER.debug("BikeServiceImpl.addBike(), input data - bike {}", bike);

        try {
            SQL_BIKE_DAO.addBike(bike);
            LOGGER.debug("BikeServiceImpl.addBike - success");
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Bike updateBike(Bike bike) throws ServiceException {
        LOGGER.debug("BikeServiceImpl.updateBike, input data - bike {}", bike);
        Bike result;
        try {
            result = SQL_BIKE_DAO.updateBike(bike);
            LOGGER.debug("BikeServiceImpl.updateBike() - success");
            return result;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public Bike getBikeById(int idBike) throws ServiceException, BusinessException {
        LOGGER.debug("BikeServiceImpl.getBike() , input data - idBike {}", idBike);
        try {
            Optional<Bike> bikeOptional = SQL_BIKE_DAO.getBikeById(idBike);
            if (bikeOptional.isPresent()) {
                LOGGER.debug("BikeServiceImpl.updateBike - success");
                return bikeOptional.get();
            } else {
                throw new BusinessException("cant find bike with this id " + idBike);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public List<Bike> getBikes() throws ServiceException {
        LOGGER.debug("BikeServiceImpl.getBikes - run, no input data");
        List<Bike> list;
        try {
            list = SQL_BIKE_DAO.getBikes();
            LOGGER.debug("BikeServiceImpl.getBikes - success");
            return list;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public List<Bike> getBikesByYearDesc() throws ServiceException {
        LOGGER.debug("BikeServiceImpl.getBikesByYearDesc - run, no input data");
        List<Bike> list;
        try {
            list = SQL_BIKE_DAO.getBikesByYearDesc();
            LOGGER.debug("BikeServiceImpl.getBikesByYearDesc - success");
            return list;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public void deleteBike(int id) throws ServiceException {
        LOGGER.debug("BikeServiceImpl.deleteBike(), input data - id {}", id);
        try {
            SQL_BIKE_DAO.deleteBike(id);
            LOGGER.debug("BikeServiceImpl.deleteBike - - success");
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    //@Override
    public List<Bike> findBikeByName(String nameBike) throws ServiceException {
        LOGGER.debug("BikeServiceImpl.findBikeByName(), input data - nameBike {}", nameBike);
        List<Bike> listBikesByName = new LinkedList<>();
        try {
            List<Bike> list = SQL_BIKE_DAO.getBikes();
            for (Bike bike : list) {
                String name = bike.getName();
                if (name.equalsIgnoreCase(nameBike)) {
                    listBikesByName.add(bike);
                }
            }
            LOGGER.debug("BikeServiceImpl.findBikeByName - - success");
            return listBikesByName;
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            LOGGER.debug("BikeServiceImpl.findBikeByName - executed");
        }
    }

    @Override
    public List<Bike> sortBikeByName() throws ServiceException {
        LOGGER.debug("BikeServiceImpl.sortBikeByName - run, no input data");
        List<Bike> list;
        try {
            list = SQL_BIKE_DAO.getBikes();
            list.sort(new Bike.NameComparator());
            LOGGER.debug("BikeServiceImpl.sortBikeByName - success");
            return list;
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            LOGGER.debug("BikeServiceImpl.sortBikeByName - executed");
        }
    }


    @Override
    public List<Bike> sortBikeByYear() throws ServiceException {
        LOGGER.debug("BikeServiceImpl.sortBikeByYear - run, no input data");
        List<Bike> list;
        try {
            list = SQL_BIKE_DAO.getBikes();
            list.sort(new Bike.YearComparator());
            LOGGER.debug("BikeServiceImpl.sortBikeByYear - success");
            return list;
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            LOGGER.debug("BikeServiceImpl.sortBikeByYear - executed");
        }
    }

}
