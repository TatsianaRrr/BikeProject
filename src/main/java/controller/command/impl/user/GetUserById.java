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

import static controller.servlet.RequestParameterName.ID_USER;


public class GetUserById implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserById.class);
    private final UserService userService = ServiceFactory.getInstance().getUserServiceImpl();
    private static GetUserById instance;

    private GetUserById() {
    }

    public static synchronized GetUserById getInstance() {
        if (instance == null) {
            instance = new GetUserById();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("GetUserById.execute(), input data - request {}, response {}", request, response);
        String jspPageName;
        User user;
        String id = request.getParameter(ID_USER);
        try {
            user = userService.getUserById(Integer.parseInt(id));

            request.setAttribute(RequestParameterName.USER, user);
            jspPageName = JspPageName.UPDATE_USER;
            LOGGER.debug("GetUserById.execute() - success");
        } catch (ServiceException | BusinessException e) {
            request.setAttribute(RequestParameterName.INFORMATION, e.getMessage()); //возвр из сессии обьект
            LOGGER.error("error GetUserById", e);
            jspPageName = JspPageName.UPDATE_USER;
        }
        return jspPageName;
    }
}
