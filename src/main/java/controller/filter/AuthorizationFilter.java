package controller.filter;

import bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static bean.User.UserRole.ADMIN;
import static controller.servlet.RequestParameterName.USER_ROLE;

/*авторизация — предоставление этому лицу возможностей в соответствие с положенными ему правами
или проверка наличия прав при попытке выполнить какое-либо действие*/
@WebFilter(filterName = "AuthorizationFilter")

public class AuthorizationFilter implements Filter {
    private String contextPath;

    @Override
    public void init(FilterConfig filterConfig) {
        contextPath = filterConfig.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession currentSession = request.getSession(false);
        if (currentSession == null) {
            response.sendRedirect(contextPath + "jsp/main.jsp");
        } else {
            User.UserRole userRole = (User.UserRole) request.getSession().getAttribute(USER_ROLE);
            if (userRole.equals(ADMIN)) { //check if user type is not admin
                chain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect(contextPath + "jsp/main.jsp"); //or page where you want to
            }
        }
    }


    @Override
    public void destroy() {
    }
}
