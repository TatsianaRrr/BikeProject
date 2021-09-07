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


public class DeleteOrder implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteOrder.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderServiceImpl();
    private static DeleteOrder instance;

    private DeleteOrder() {
    }

    public static synchronized DeleteOrder getInstance() {
        if (instance == null) {
            instance = new DeleteOrder();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("DeleteOrder.execute(), input data - request {}, response {}", request, response);
        String jspPageName;
        try {
            int id = Integer.parseInt(request.getParameter(RequestParameterName.ORDER_ID));
            orderService.deleteOrder(id);
            request.setAttribute(RequestParameterName.INFORMATION, "Order deleted");
            List<Order> orderList = orderService.getOrders();
            request.setAttribute(RequestParameterName.ORDER_LIST, orderList);
            jspPageName = JspPageName.GET_ORDERS;
            LOGGER.debug("DeleteOrder.execute() - success");
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage()); //возвр из сессии обьект
            LOGGER.error("error DeleteOrder", e);
            jspPageName = JspPageName.ADMIN;
        }
        return jspPageName;
    }
}
