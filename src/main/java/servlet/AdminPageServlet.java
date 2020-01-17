package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/adminPage")
public final class AdminPageServlet extends HttpServlet {
  private static final String ADMIN_PAGE = "/adminPage.jsp";
  private UserService userService = new UserService();

  protected void doPost(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    userService.changePasswordByAdmin(request);
    userService.changeUserNameByAdmin(request);
    doGet(request, response);
  }

  @Override
  protected void doGet(
          final HttpServletRequest req,
          final HttpServletResponse resp)
          throws ServletException, IOException {
    req.getRequestDispatcher(ADMIN_PAGE).forward(req, resp);
  }
}