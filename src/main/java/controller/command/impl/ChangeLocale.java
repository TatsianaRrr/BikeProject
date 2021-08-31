package controller.command.impl;

import controller.command.Command;
import controller.servlet.JspPageName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangeLocale implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(ChangeLocale.class);
    private static ChangeLocale instance;

    private ChangeLocale() {
    }

    public static synchronized ChangeLocale getInstance() {
        if (instance == null) {
            instance = new ChangeLocale();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("ChangeLocale.execute(), input data - request {}, response {}", request, response);
        LOGGER.debug("start to ChangeLocale.execute()g");
        String pageName = JspPageName.MAIN_PAGE;
        LOGGER.debug("end ChangeLocale.execute() success");
        return pageName;
    }
}

