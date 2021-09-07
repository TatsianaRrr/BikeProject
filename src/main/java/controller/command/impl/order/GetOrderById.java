package controller.command.impl.order;

import bean.Order;
import controller.command.Command;
import controller.servlet.JspPageName;
import controller.servlet.RequestParameterName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.OrderService;
import service.exception.BusinessException;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.servlet.RequestParameterName.ORDER_ID;


public class GetOrderById implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetOrderById.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderServiceImpl();
    private static GetOrderById instance;

    private GetOrderById() {
    }

    public static synchronized GetOrderById getInstance() {
        if (instance == null) {
            instance = new GetOrderById();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("GetOrderById.execute(), input data - request {}, response {}", request, response);
        String jspPageName;
        Order order;
        try {
            String id = request.getParameter(ORDER_ID);//этот параметр в name admin.jsp
            order = orderService.getOrderById(Integer.parseInt(id));
            request.setAttribute(RequestParameterName.ORDER, order);
            jspPageName = JspPageName.GET_ORDER;
            LOGGER.debug("GetOrderById.execute() - success");
        } catch (ServiceException | NumberFormatException | BusinessException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage()); //возвр из сессии обьект
            LOGGER.error("error GetOrderById", e);
            jspPageName = JspPageName.ADMIN;
        }
        return jspPageName;
    }
}
