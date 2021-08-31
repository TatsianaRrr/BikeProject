package controller.filter;

import controller.command.CommandHelper;
import controller.servlet.JspPageName;
import controller.servlet.RequestParameterName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static controller.servlet.RequestParameterName.INFORMATION;
import static controller.servlet.RequestParameterName.USER;

@WebFilter(filterName = "AuthenticationFilter")
/*аутентификация — это установление соответствия лица названному им идентификатору*/
public class AuthenticationFilter implements Filter {
    private String contextPath;

    @Override
    public void init(FilterConfig filterConfig) {
        contextPath = filterConfig.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession(false);
        String command = httpServletRequest.getParameter(RequestParameterName.COMMAND_NAME);
        boolean isGetCommand = command.equalsIgnoreCase(CommandHelper.CommandName.SIGN_IN.name())
                || command.equalsIgnoreCase(CommandHelper.CommandName.SIGN_UP.name())
                || command.equalsIgnoreCase(CommandHelper.CommandName.CHANGE_LOCALE.name());

        if (!isGetCommand && session != null && session.getAttribute(USER) == null) {
            httpServletResponse.sendRedirect(JspPageName.ERROR_PAGE);
            httpServletRequest.setAttribute(INFORMATION, "authentication error");
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

}
