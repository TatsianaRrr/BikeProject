package controller.command.impl;

import bean.User;
import controller.command.Command;
import controller.servlet.JspPageName;
import controller.servlet.RequestParameterName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.servlet.RequestParameterName.USER;


public class SignIn implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignIn.class);
    private final UserService userService = ServiceFactory.getInstance().getUserServiceImpl();
    private static SignIn instance;

    private SignIn() {
    }

    public static synchronized SignIn getInstance() {
        if (instance == null) {
            instance = new SignIn();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("SignIn.execute(), input data - request {}, response {}", request, response);
        String jspPageName = JspPageName.MAIN_PAGE;
        String login = request.getParameter(RequestParameterName.LOGIN);
        String password = request.getParameter(RequestParameterName.PASSWORD);

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorData", "enter login and password");
            return jspPageName;
        }
        try {
            User user = userService.signIn(login, password);
            request.getSession().setAttribute(USER, user);
            User.UserRole userRole = user.getUserRole();
            switch (userRole) {
                case USER:
                    jspPageName = JspPageName.USER_PAGE;
                    break;
                case ADMIN:
                    jspPageName = JspPageName.ADMIN;
                    break;
                default:
                    throw new IllegalArgumentException(("Unknown role: " + userRole.getName()));
            }
            LOGGER.debug("SignIn.execute() - success");
        } catch (ServiceException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage()); //возвр из сессии обьект
            LOGGER.error("error sign in", e);
            jspPageName = JspPageName.MAIN_PAGE;
        }//
        return jspPageName;
    }
}
