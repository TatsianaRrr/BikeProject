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

import static controller.servlet.RequestParameterName.BIKE_ID;


public class DeleteBike implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteBike.class);
    private final BikeService bikeService = ServiceFactory.getInstance().getBikeServiceImpl();
    private static DeleteBike instance;

    private DeleteBike() {
    }

    public static synchronized DeleteBike getInstance() {
        if (instance == null) {
            instance = new DeleteBike();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("DeleteBike.execute(), input data - request {}, response {}", request, response);
        String jspPageName = null;
        try {
            int bikeId = Integer.parseInt(request.getParameter(BIKE_ID));
            bikeService.deleteBike(bikeId);
            request.setAttribute(RequestParameterName.INFORMATION, "Bike deleted");
            List<Bike> bikeList = bikeService.getBikes();
            request.setAttribute(RequestParameterName.BIKE_LIST, bikeList);
            jspPageName = JspPageName.GET_BIKES_PAGE;
            LOGGER.debug("DeleteBike.execute() - success");
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage()); //возвр из сессии обьект
            LOGGER.error("error DeleteBike", e);
            jspPageName = JspPageName.ERROR_PAGE;
        }
        return jspPageName;
    }
}
