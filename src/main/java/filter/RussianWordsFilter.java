package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = "/*")
public final class RussianWordsFilter implements Filter {
    private static final String UTF_8 = "UTF-8";
    private static final String CONTENT_TYPE = "text/html";

  @Override
  public void init(final FilterConfig filterConfig)
          throws ServletException {
  }

  @Override
  public void doFilter(
          final ServletRequest servletRequest,
          final ServletResponse servletResponse,
          final FilterChain filterChain)
          throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    request.setCharacterEncoding(UTF_8);
    response.setCharacterEncoding(UTF_8);
    response.setContentType(CONTENT_TYPE);
    filterChain.doFilter(request, response);
  }

  @Override
  public void destroy() {
  }
}