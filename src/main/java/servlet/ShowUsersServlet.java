package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/userlist")
public final class ShowUsersServlet extends HttpServlet {
  private static UserService userService = new UserService();
  private static final String USERS_JSP = "/users.jsp";
  private static final String USER_LIST_PARAM = "userList";

  protected void doPost(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    request.setAttribute(
            USER_LIST_PARAM,
            userService.getAllUsers());
    doGet(request, response);
  }

  protected void doGet(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    request.getRequestDispatcher(USERS_JSP)
            .forward(request, response);
  }
}