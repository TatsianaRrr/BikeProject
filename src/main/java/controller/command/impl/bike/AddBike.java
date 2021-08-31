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


public class AddBike implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddBike.class);
    private final BikeService bikeService = ServiceFactory.getInstance().getBikeServiceImpl();
    private static AddBike instance;

    private AddBike() {
    }

    public static synchronized AddBike getInstance() {
        if (instance == null) {
            instance = new AddBike();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("AddBike.execute(), input data - request {}, response {}", request, response);
        try {
           // int id = Integer.parseInt(request.getParameter(BIKE_ID));
            String name = request.getParameter(RequestParameterName.BIKE_NAME);
            String year = request.getParameter(RequestParameterName.YEAR);
            String description = request.getParameter(RequestParameterName.DESCRIPTION);
            String price = request.getParameter(RequestParameterName.PRICE);
            String image = request.getParameter(RequestParameterName.IMAGE);
            String ordered = request.getParameter(RequestParameterName.ORDERED);
            String deleted = request.getParameter(RequestParameterName.DELETED);

            Bike bike = new Bike(name, Integer.parseInt(year), description, Double.parseDouble(price),
                    image, false, false);
            bikeService.addBike(bike);

            request.setAttribute(RequestParameterName.BIKE_LIST, bikeService.getBikes() );
            LOGGER.debug("AddBike.execute() - success");
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage());
            LOGGER.error("error AddBike", e);
        }
        return JspPageName.ADD_BIKE;
    }
}
