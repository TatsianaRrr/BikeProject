package controller.command.impl;

import controller.command.Command;
import controller.servlet.JspPageName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.OrderService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowOrderPage implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowOrderPage.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderServiceImpl();
    private static ShowOrderPage instance;

    private ShowOrderPage() {
    }

    public static synchronized ShowOrderPage getInstance() {
        if (instance == null) {
            instance = new ShowOrderPage();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("ShowOrderPage.execute(), input data - request {}, response {}", request, response);
        LOGGER.debug("start to ShowOrderPage.execute()g");
        String pageName = JspPageName.ORDER_PAGE;
        LOGGER.debug("end ShowOrderPage.execute() success");
        return pageName;
    }
}

