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

public class GetAllOrders implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAllOrders.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderServiceImpl();
    private static GetAllOrders instance;

    private GetAllOrders() {
    }

    public static synchronized GetAllOrders getInstance() {
        if (instance == null) {
            instance = new GetAllOrders();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("GetAllOrders.execute(), input data - request {}, response {}", request, response);
        String pageName = null;
        try {
            LOGGER.debug("start to GetAllOrders.execute()");
            List<Order> orderList = orderService.getOrders();
            request.setAttribute(RequestParameterName.ORDER_LIST, orderList);
            pageName = JspPageName.GET_ORDERS;
            LOGGER.debug("end GetAllOrders.execute() success");
        } catch (ServiceException e) {
            LOGGER.error("error GetAllOrders.execute() in controller", e);
            pageName = JspPageName.ERROR_PAGE;
        }
        return pageName;
    }
}
