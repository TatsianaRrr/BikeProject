package controller.command.impl.bike;

import bean.Bike;
import controller.command.Command;
import controller.servlet.JspPageName;
import controller.servlet.RequestParameterName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.BikeService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SortBikesByName implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(SortBikesByName.class);
    private final BikeService bikeService = ServiceFactory.getInstance().getBikeServiceImpl();
    private static SortBikesByName instance;

    private SortBikesByName() {
    }

    public static synchronized SortBikesByName getInstance() {
        if (instance == null) {
            instance = new SortBikesByName();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("SortBikesByName.execute(), input data - request {}, response {}", request, response);
        String pageName = null;
        try {
            LOGGER.debug("start to SortBikesByName.execute()");
            List<Bike> bikeList = bikeService.sortBikeByName();
            request.setAttribute(RequestParameterName.BIKE_LIST, bikeList);
            pageName = JspPageName.GET_BIKES_PAGE;
            LOGGER.debug("the end SortBikesByName.execute() success");
        } catch (ServiceException e) {
            LOGGER.error("error SortBikesByName.execute() in controller", e);
            pageName = JspPageName.ERROR_PAGE;
        }
        return pageName;
    }
}
