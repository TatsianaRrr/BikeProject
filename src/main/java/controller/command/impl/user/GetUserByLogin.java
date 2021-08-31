package controller.command.impl.user;

import bean.User;
import controller.command.Command;
import controller.servlet.JspPageName;
import controller.servlet.RequestParameterName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import service.exception.BusinessException;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.servlet.RequestParameterName.LOGIN;


public class GetUserByLogin implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserByLogin.class);
    private final UserService userService = ServiceFactory.getInstance().getUserServiceImpl();
    private static GetUserByLogin instance;

    private GetUserByLogin() {
    }

    public static synchronized GetUserByLogin getInstance() {
        if (instance == null) {
            instance = new GetUserByLogin();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("GetUserByLogin.execute(), input data - request {}, response {}", request, response);
        String jspPageName;
        String login = request.getParameter(LOGIN);
        try {
            User user = userService.getUserByLogin(login);

            request.setAttribute(RequestParameterName.USER, user);
            jspPageName = JspPageName.UPDATE_USER;
            LOGGER.debug("GetUserByLogin.execute() - success");
        } catch (ServiceException | BusinessException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage()); //возвр из сессии обьект
            LOGGER.error("error GetUserByLogin", e);
            jspPageName = JspPageName.UPDATE_USER;
        }
        return jspPageName;
    }
}
