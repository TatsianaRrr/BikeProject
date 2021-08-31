package controller.command.impl;

import controller.command.Command;
import controller.servlet.JspPageName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowUserPage implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowUserPage.class);
    private final UserService userService = ServiceFactory.getInstance().getUserServiceImpl();
    private static ShowUserPage instance;

    private ShowUserPage() {
    }

    public static synchronized ShowUserPage getInstance() {
        if (instance == null) {
            instance = new ShowUserPage();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("ShowUserPage.execute(), input data - request {}, response {}", request, response);
        LOGGER.debug("start to ShowUserPage.execute()g");
        String pageName = JspPageName.USER_PAGE;
        LOGGER.debug("end ShowUserPage.execute() success");
        return pageName;
    }
}

