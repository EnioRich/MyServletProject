package servlet;

import service.GoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/goods")
public final class AllGoodsServlet extends HttpServlet {
  private final GoodService goodService = new GoodService();

  protected void doPost(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    goodService.getAllGoods(request, response);
  }
}
