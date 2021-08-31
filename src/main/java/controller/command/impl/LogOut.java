package controller.command.impl;

import controller.command.Command;
import controller.servlet.JspPageName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogOut implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(LogOut.class);
    private static LogOut instance;

    private LogOut() {
    }

    public static synchronized LogOut getInstance() {
        if (instance == null) {
            instance = new LogOut();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("LogOut.execute(), input data - request {}, response {}", request, response);
        LOGGER.debug("start to LogOut.execute()g");
        String pageName = JspPageName.MAIN_PAGE;
        request.getSession().invalidate();
        LOGGER.debug("end LogOut.execute() success");
        return pageName;
    }
}

