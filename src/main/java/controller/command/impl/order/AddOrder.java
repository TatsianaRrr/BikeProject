package controller.command.impl.order;

import bean.Order;
import controller.command.Command;
import controller.servlet.JspPageName;
import controller.servlet.RequestParameterName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.OrderService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddOrder implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddOrder.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderServiceImpl();
    private static AddOrder instance;

    private AddOrder() {
    }

    public static synchronized AddOrder getInstance() {
        if (instance == null) {
            instance = new AddOrder();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("AddOrder.execute(), input data - request {}, response {}", request, response);
        String jspPageName = null;
        try {
            //   int id = Integer.parseInt(request.getParameter(ORDER_ID));
            int bikeId = Integer.parseInt(request.getParameter(RequestParameterName.BIKE_ID));
            int userId = Integer.parseInt(request.getParameter(RequestParameterName.ID_USER));
            String description = request.getParameter(RequestParameterName.DESCRIPTION);

            Order order = new Order(userId, bikeId, description);
            orderService.addOrder(order);
                        LOGGER.debug("AddOrder.execute() - success");
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage());
            LOGGER.error("error AddOrder", e);
            jspPageName = JspPageName.ERROR_PAGE;
        }
        return JspPageName.ADD_ORDER;
    }
}
