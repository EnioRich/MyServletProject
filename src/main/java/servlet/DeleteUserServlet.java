package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteUser")
public final class DeleteUserServlet extends HttpServlet {
  private static final String DELETE_USER_JSP = "/deleteUser.jsp";
  private UserService userService = new UserService();

  protected void doPost(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    userService.deleteUser(request);
    doGet(request, response);
  }

  @Override
  protected void doGet(
          final HttpServletRequest req,
          final HttpServletResponse resp)
          throws ServletException, IOException {
    req.getRequestDispatcher(DELETE_USER_JSP).forward(req, resp);
  }
}