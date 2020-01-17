package servlet;

import service.GoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteGood")
public final class DeleteGoodServlet extends HttpServlet {
  private static final String DELETE_GOOD_JSP = "/deleteGood.jsp";
  private final GoodService goodService = new GoodService();

  protected void doPost(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    goodService.deleteGood(request);
    doGet(request, response);
  }

  protected void doGet(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    request.getRequestDispatcher(DELETE_GOOD_JSP)
            .forward(request, response);
  }
}