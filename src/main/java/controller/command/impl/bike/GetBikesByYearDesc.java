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

public class GetBikesByYearDesc implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetBikesByYearDesc.class);
    private final BikeService bikeService = ServiceFactory.getInstance().getBikeServiceImpl();
    private static GetBikesByYearDesc instance;

    private GetBikesByYearDesc() {
    }

    public static synchronized GetBikesByYearDesc getInstance() {
        if (instance == null) {
            instance = new GetBikesByYearDesc();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("GetBikesByYearDesc.execute(), input data - request {}, response {}", request, response);
        String pageName = null;
        try {
            LOGGER.debug("start to GetBikesByYearDesc.execute()");
            List<Bike> bikeList = bikeService.getBikesByYearDesc();
            request.setAttribute(RequestParameterName.BIKE_LIST, bikeList);
            pageName = JspPageName.GET_BIKES_PAGE;
            LOGGER.debug("end GetBikesByYearDesc.execute() success");
        } catch (ServiceException e) {
            LOGGER.error("error GetBikesByYearDesc.execute() in controller", e);
            pageName = JspPageName.ERROR_PAGE;
        }
        return pageName;
    }
}
