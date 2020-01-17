package service;

import dao.GoodDao;
import model.Good;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class GoodService {
  private static final String ID_PARAM = "id";
  private static final String GOODS_ATTRIBUTE = "goods";
  private static final String DELETED_ATTRIBUTE = "deleted";
  private static final String NO_SUCH_GOOD_MESSAGE = " is deleted";
  private static final String ALL_GOODS_PAGE_JSP = "allGoodsPage.jsp";
  private GoodDao goodDao = new GoodDao();

  public void getAllGoods(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    List<Good> allGoods = goodDao.getAllGoods();
    request.setAttribute(GOODS_ATTRIBUTE, allGoods);
    request.getRequestDispatcher(ALL_GOODS_PAGE_JSP)
            .forward(request, response);
  }

  public void deleteGood(final HttpServletRequest request) {
    Good good = getGoodFromSession(request);
    boolean ifNewGood = goodDao.checkGood(good);
    if (!ifNewGood) {
      goodDao.deleteGood(good);
      request.setAttribute(DELETED_ATTRIBUTE,
              good.getName() + NO_SUCH_GOOD_MESSAGE);
    } else {
      request.setAttribute(DELETED_ATTRIBUTE, NO_SUCH_GOOD_MESSAGE);
    }
  }

  private Good getGoodFromSession(final HttpServletRequest request) {
    int goodId = Integer.parseInt(request.getParameter(ID_PARAM));
    return goodDao.getGoodById(goodId);
  }
}
