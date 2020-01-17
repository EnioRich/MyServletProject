package filter;

import model.Role;
import model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = {
        "/adminPage.jsp",
        "/deleteGood.jsp",
        "/deleteUser.jsp",
        "/deleteGood",
        "/deleteUser"
})
public final class AdminFilter implements Filter {
  private static final String USER = "user";
  private static final String ACCESS_DENIED_JSP = "accessDenied.jsp";

  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(
          final ServletRequest servletRequest,
          final ServletResponse servletResponse,
          final FilterChain filterChain)
          throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    User user = (User) request.getSession().getAttribute(USER);
    if (user != null && !user.getRole().equals(String.valueOf(Role.ADMIN))) {
      request
              .getRequestDispatcher(ACCESS_DENIED_JSP)
              .forward(request, servletResponse);
    } else {
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }

  @Override
  public void destroy() {
  }
}