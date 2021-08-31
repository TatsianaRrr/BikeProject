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


public class GetAllUsers implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAllUsers.class);
    private final UserService userService = ServiceFactory.getInstance().getUserServiceImpl();
    private static GetAllUsers instance;

    private GetAllUsers() {
    }

    public static synchronized GetAllUsers getInstance() {
        if (instance == null) {
            instance = new GetAllUsers();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("GetAllUsers.execute(), input data - request {}, response {}", request, response);
        String pageName = null;
        try {
            LOGGER.debug("start to GetAllUsers.execute()g");
            List<User> userList = userService.getAllUsers();

            request.setAttribute(RequestParameterName.USER_LIST, userList);
            pageName = JspPageName.GET_USERS_PAGE;
            LOGGER.debug("end GetAllUsers.execute() success");
        } catch (ServiceException e) {
            LOGGER.error("error GetAllUsers.execute() in controller", e);
            pageName = JspPageName.ERROR_PAGE;
        }
        return pageName;
    }
    }

