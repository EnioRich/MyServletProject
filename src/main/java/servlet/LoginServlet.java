package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public final class LoginServlet extends HttpServlet {
  private static final String LOGIN_JSP = "/login.jsp";
  private UserService userService = new UserService();

  protected void doPost(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    userService.login(request, response);
    doGet(request, response);
  }

  @Override
  protected void doGet(
          final HttpServletRequest req,
          final HttpServletResponse resp)
          throws ServletException, IOException {
    req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
  }
}