package controller.command.impl.user;

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

import static controller.servlet.RequestParameterName.*;


public class AddUser implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddUser.class);
    private final UserService userService = ServiceFactory.getInstance().getUserServiceImpl();
    private static AddUser instance;

    private AddUser() {
    }

    public static synchronized AddUser getInstance() {
        if (instance == null) {
            instance = new AddUser();
        }
        return instance;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("AddUser.execute(), input data - request {}, response {}", request, response);
        try {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            String name = request.getParameter(NAME);
            String email = request.getParameter(EMAIL);
            String userRole = request.getParameter(USER_ROLE);

            User user = new User(login, password, email, name,
                    User.UserRole.USER);
            userService.addUser(user);
            request.setAttribute(RequestParameterName.USER_LIST, userService.getAllUsers());
            LOGGER.debug("AddUser() - success ");
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage());
            LOGGER.error(e.getMessage());
        }
        return JspPageName.ADD_USER;
    }
}