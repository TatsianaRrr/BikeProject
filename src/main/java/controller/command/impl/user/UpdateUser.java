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
import java.util.List;

import static controller.servlet.RequestParameterName.*;


public class UpdateUser implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUser.class);
    private final UserService userService = ServiceFactory.getInstance().getUserServiceImpl();
    private static UpdateUser instance;

    private UpdateUser() {
    }

    public static synchronized UpdateUser getInstance() {
        if (instance == null) {
            instance = new UpdateUser();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("UpdateUser.execute(), input data - request {}, response {}", request, response);
        String jspPageName;
        try {
            int id = Integer.parseInt(request.getParameter(ID_USER));
            String login1 = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            String name = request.getParameter(NAME);
            String email = request.getParameter(EMAIL);
            String userRole = request.getParameter(USER_ROLE);

            User user = new User(id, login1, password, name, email, User.UserRole.valueOf(userRole));
            userService.updateUser(user);
            List<User> userList = userService.getAllUsers();
            request.setAttribute(RequestParameterName.USER_LIST, userList);
            jspPageName = JspPageName.GET_USERS_PAGE;
            LOGGER.debug("UpdateUser.execute() - success");
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage()); //возвр из сессии обьект
            LOGGER.error("error UpdateUser", e);
            jspPageName = JspPageName.ERROR_PAGE;
        }
        return jspPageName;
    }
}
