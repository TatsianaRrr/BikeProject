package controller.command.impl.bike;

import bean.Bike;
import controller.command.Command;
import controller.servlet.JspPageName;
import controller.servlet.RequestParameterName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.BikeService;
import service.exception.BusinessException;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.servlet.RequestParameterName.BIKE_ID;

public class GetBikeById implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetBikeById.class);
    private final BikeService bikeService = ServiceFactory.getInstance().getBikeServiceImpl();
    private static GetBikeById instance;

    private GetBikeById() {
    }

    public static synchronized GetBikeById getInstance() {
        if (instance == null) {
            instance = new GetBikeById();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("GetBikeById.execute(), input data - request {}, response {}", request, response);
        String jspPageName = JspPageName.ADMIN;
        Bike bike = null;
        // int id = Integer.parseInt(request.getParameter(BIKE_ID));
        try {
            String id = request.getParameter(BIKE_ID);//этот параметр в name admin.jsp
            bike = bikeService.getBikeById(Integer.parseInt(id));
            request.setAttribute(RequestParameterName.BIKE, bike);
            jspPageName = JspPageName.GET_BIKE_PAGE;
            LOGGER.debug("GetBikeById.execute() - success");
        } catch (ServiceException | NumberFormatException | NullPointerException | BusinessException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage()); //возвр из сессии обьект
            LOGGER.error("error GetBikeById", e);
        }
        return jspPageName;
    }
}
