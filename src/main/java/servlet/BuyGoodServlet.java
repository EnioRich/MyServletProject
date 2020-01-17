package servlet;

import service.BuyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/buy")
public final class BuyGoodServlet extends HttpServlet {
  private BuyService buyService = new BuyService();

  protected void doPost(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    buyService.pay(request, response);
  }

  protected void doGet(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    buyService.confirmPay(request, response);
  }
}