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

import static controller.servlet.RequestParameterName.LOGIN;


public class DeleteUser implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteUser.class);
    private final UserService userService = ServiceFactory.getInstance().getUserServiceImpl();
    private static DeleteUser instance;

    private DeleteUser() {
    }

    public static synchronized DeleteUser getInstance() {
        if (instance == null) {
            instance = new DeleteUser();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("DeleteUser.execute(), input data - request {}, response {}", request, response);
        String jspPageName;
        try {
            String userLogin = request.getParameter(LOGIN);
            userService.deleteUser(userLogin);
            //сделать INFORMATION
          //  request.setAttribute(RequestParameterName.INFORMATION, "User deleted");
            List<User> userList = userService.getAllUsers();
            request.setAttribute(RequestParameterName.USER_LIST, userList);
            jspPageName = JspPageName.GET_USERS_PAGE;
            LOGGER.debug("DeleteUser.execute() - success");
        } catch (ServiceException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage()); //возвр из сессии обьект
            LOGGER.error("error delete user", e);
            jspPageName = JspPageName.ERROR_PAGE;
        }
        return jspPageName;
    }
}
