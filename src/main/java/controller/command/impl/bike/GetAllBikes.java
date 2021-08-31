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

public class GetAllBikes implements Command {
    private static final Logger logger = LoggerFactory.getLogger(GetAllBikes.class);
    private final BikeService bikeService = ServiceFactory.getInstance().getBikeServiceImpl();
    private static GetAllBikes instance;

    private GetAllBikes() {
    }

    public static synchronized GetAllBikes getInstance() {
        if (instance == null) {
            instance = new GetAllBikes();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("GetAllBikes.execute(), input data - request {}, response {}", request, response);
        String pageName = null;
        try {
            logger.debug("start to GetAllBikes.execute()");
            List<Bike> bikeList = bikeService.getBikes();
            request.setAttribute(RequestParameterName.BIKE_LIST, bikeList);
            pageName = JspPageName.GET_BIKES_PAGE;
            logger.debug("end GetAllBikes.execute() success");
        } catch (ServiceException e) {
            logger.error("error GetAllBikes.execute() in controller", e);
            pageName = JspPageName.ERROR_PAGE;
        }
        return pageName;
    }
}
