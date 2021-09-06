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

import java.util.Locale;
import java.util.Objects;

import static controller.servlet.RequestParameterName.LOGIN;
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
        LOGGER.debug(request.getHeader("User-Agent") + " try to sign in account");
        String jspPageName = JspPageName.MAIN_PAGE;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(RequestParameterName.PASSWORD);

        try {
            User user = userService.signIn(login, password);
            if (user.getUserRole() != null) {
                Cookie cookieLogin = new Cookie(LOGIN, user.getLogin());
                response.addCookie(cookieLogin);
                request.getSession(true).setAttribute(LOGIN, user.getLogin());
                if (user.getUserRole().getName().equals(RequestParameterName.ADMIN)) {
                    request.getSession().setAttribute(RequestParameterName.USER_ROLE, RequestParameterName.ADMIN);
                } else {
                    request.getSession().setAttribute(RequestParameterName.USER_ROLE, RequestParameterName.USER);
                }
            }
            request.getSession().setAttribute(LOGIN, user.getLogin());
            request.getSession().setAttribute(RequestParameterName.PAGE, JspPageName.USER_PAGE);
            jspPageName = JspPageName.USER_PAGE;
        } catch (ServiceException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getCause().getMessage());
            LOGGER.error(((String) request.getSession().getAttribute(RequestParameterName.LOGIN)), e);
        }
        return jspPageName;
    }
}
