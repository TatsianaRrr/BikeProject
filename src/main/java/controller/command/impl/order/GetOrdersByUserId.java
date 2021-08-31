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

import static controller.servlet.RequestParameterName.ID_USER;


public class GetOrdersByUserId implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetOrdersByUserId.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderServiceImpl();
    private static GetOrdersByUserId instance;

    private GetOrdersByUserId() {
    }

    public static synchronized GetOrdersByUserId getInstance() {
        if (instance == null) {
            instance = new GetOrdersByUserId();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("GetOrdersByUserId.execute(), input data - request {}, response {}", request, response);
        String jspPageName;
       List<Order> list;
        try {
            String id = request.getParameter(ID_USER);//этот параметр в name admin.jsp
            list = orderService.getOrdersByUserId(Integer.parseInt(id));
            request.setAttribute(RequestParameterName.ORDER_LIST, list);
            jspPageName = JspPageName.GET_ORDERS;
            LOGGER.debug("GetOrdersByUserId.execute() - success");
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getCause().getMessage()); //возвр из сессии обьект
            LOGGER.error("error GetOrdersByUserId", e);
            jspPageName = JspPageName.GET_ORDERS;
        }
        return jspPageName;
    }
}
