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
import java.util.List;

import static controller.servlet.RequestParameterName.BIKE_ID;

public class GetOrdersByBikeId implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetOrdersByBikeId.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderServiceImpl();
    private static GetOrdersByBikeId instance;

    private GetOrdersByBikeId() {
    }

    public static synchronized GetOrdersByBikeId getInstance() {
        if (instance == null) {
            instance = new GetOrdersByBikeId();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("GetOrdersByBikeId.execute(), input data - request {}, response {}", request, response);
        String jspPageName;
        List<Order> list;
      try {
            String id = request.getParameter(BIKE_ID);//этот параметр в name admin.jsp
            list = orderService.getOrdersByBikeId(Integer.parseInt(id));
            request.setAttribute(RequestParameterName.ORDER_LIST, list);
            jspPageName = JspPageName.GET_ORDERS;
            LOGGER.debug("GetOrdersByBikeId.execute() - success");
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage()); //возвр из сессии обьект
            LOGGER.error("error GetOrdersByBikeId", e);
            jspPageName = JspPageName.ADMIN;
        }
        return jspPageName;
    }
}
