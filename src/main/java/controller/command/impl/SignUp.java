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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SignUp implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignUp.class);
    private final UserService userService = ServiceFactory.getInstance().getUserServiceImpl();
    private static SignUp instance;

    private SignUp() {
    }

    public static synchronized SignUp getInstance() {
        if (instance == null) {
            instance = new SignUp();
        }
        return instance;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug(" try to sign up account");
                User user = null;
        try {
            String login = request.getParameter(RequestParameterName.LOGIN);
            String password = request.getParameter(RequestParameterName.PASSWORD);
            String email = request.getParameter(RequestParameterName.EMAIL);
            String name = request.getParameter(RequestParameterName.NAME);
            user = userService.signUp(login, password, email, name);
            request.getSession(true).setAttribute(RequestParameterName.LOGIN, user.getLogin());
            Cookie cookie = new Cookie(RequestParameterName.LOGIN, user.getLogin());
            response.addCookie(cookie);
            request.getSession().setAttribute(RequestParameterName.PAGE, JspPageName.MAIN_PAGE);
            LOGGER.debug(" sign up account as \"" + user.getLogin());
        } catch (ServiceException e) {
           // LOGGER.error(request.getSession().getAttribute(RequestParameterName.LOGIN), e);
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage());
        }
        return  JspPageName.USER_PAGE;
    }
}