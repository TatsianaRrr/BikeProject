package controller.command.impl;

import controller.command.Command;
import controller.servlet.JspPageName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowAdminPage implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowAdminPage.class);
    private final UserService userService = ServiceFactory.getInstance().getUserServiceImpl();
    private static ShowAdminPage instance;
//
    private ShowAdminPage() {
    }

    public static synchronized ShowAdminPage getInstance() {
        if (instance == null) {
            instance = new ShowAdminPage();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("ShowAdminPage.execute(), input data - request {}, response {}", request, response);
        LOGGER.debug("start to ShowAdminPage.execute()");
        String pageName = JspPageName.ADMIN;
        LOGGER.debug("end ShowAdminPage.execute() success");
        return pageName;
    }
}

