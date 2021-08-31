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

import static controller.servlet.RequestParameterName.BIKE_ID;

public class UpdateBike implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateBike.class);
    private final BikeService bikeService = ServiceFactory.getInstance().getBikeServiceImpl();
    private static UpdateBike instance;

    private UpdateBike() {
    }

    public static synchronized UpdateBike getInstance() {
        if (instance == null) {
            instance = new UpdateBike();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("UpdateBike.execute(), input data - request {}, response {}", request, response);
        String jspPageName;
        Bike bike = null;
        try {
            String id = request.getParameter(BIKE_ID);
            String name = request.getParameter(RequestParameterName.BIKE_NAME);
            String year = request.getParameter(RequestParameterName.YEAR);
            String description = request.getParameter(RequestParameterName.DESCRIPTION);
            String price = request.getParameter(RequestParameterName.PRICE);
            String image = request.getParameter(RequestParameterName.IMAGE);
            String ordered = request.getParameter(RequestParameterName.ORDERED);
            String deleted = request.getParameter(RequestParameterName.DELETED);

            bikeService.updateBike(new Bike(name, Integer.parseInt(year), description, Double.parseDouble(price),
                    image, false, false));
            jspPageName = JspPageName.UPDATE_BIKE;
            request.setAttribute(RequestParameterName.BIKE, bike);
            LOGGER.debug("UpdateBike.execute() - success");
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage()); //возвр из сессии обьект
            LOGGER.error("error UpdateBike", e);
            // jspPageName=JspPageName.ERROR_PAGE;
        }
        return JspPageName.ADMIN;
    }
}
