package controller.servlet;

import controller.command.Command;
import controller.command.CommandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CloseDB;
import service.exception.ServiceException;
import service.util.CloseDBServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller",
        urlPatterns = {"/controller"})

public class ServletController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CommandHelper commandHelper = CommandHelper.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(ServletController.class);


    public ServletController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    //method get command from request, execute request and forward to other page
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("start request in controller");
        Command iCommand = commandHelper.getCommand(request);
        String page = iCommand.execute(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
            logger.debug("request success");
        } else {
            errorMessageDirectlyFromResponse(response);
        }
    }

    private void errorMessageDirectlyFromResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println(" Error in servlet controller");//ответ на экране в браузере
    }

    //close database connections
    @Override
    public void destroy() {
        try {
            CloseDB closeDB = new CloseDBServiceImpl();
            closeDB.closeConnections();
        } catch (ServiceException e) {
            logger.error("don't closed database connection ", e);
        }
    }
}
