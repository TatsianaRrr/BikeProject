package controller.command.impl;

import controller.command.Command;
import controller.servlet.JspPageName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowUserAccount implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowUserAccount.class);
    private final UserService userService = ServiceFactory.getInstance().getUserServiceImpl();
    private static ShowUserAccount instance;

    private ShowUserAccount() {
    }

    public static synchronized ShowUserAccount getInstance() {
        if (instance == null) {
            instance = new ShowUserAccount();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("ShowUserAccount.execute(), input data - request {}, response {}", request, response);
        LOGGER.debug("start to ShowUserAccount.execute()g");
        String pageName = JspPageName.USER_ACCOUNT;
        LOGGER.debug("end ShowUserAccount.execute() success");
        return pageName;
    }
}

