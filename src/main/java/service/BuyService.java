package service;

import dao.CodeDao;
import model.Code;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BuyService {
  private static final MailService MAIL_SERVICE = new MailService();
  private  CodeDao codeDao = new CodeDao();
  private static final String BUY_CONFIRMATION_JSP = "buyConfirmation.jsp";
  private static final String CONFIRMATION_JSP = "confirmation.jsp";
  private static final String GOOD_ID = "goodId";
  private static final String ID = "id";
  private static final String CODE_PARAM = "code";
  private static final String USER_ATTRIBUTE = "user";
  private static final String PAYMENT_ATTRIBUTE = "payment";
  private static final String PAYMENT_SUCCESS_MESSAGE = "Payment success";
  private static final String WRONG_CODE_MESSAGE = "Wrong code";

  public final void pay(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    int goodId = getGoodIdFromSession(request);
    String codeValue = getCodeFromSession(request);
    User user = getUserFromSession(request);
    Code code = new Code(codeValue, user.getId(), goodId);
    if (codeDao.checkCode(code)) {
      request.setAttribute(PAYMENT_ATTRIBUTE, PAYMENT_SUCCESS_MESSAGE);
      request.getRequestDispatcher(CONFIRMATION_JSP)
              .forward(request, response);
    } else {
      request.setAttribute(PAYMENT_ATTRIBUTE, WRONG_CODE_MESSAGE);
      request.getRequestDispatcher(CONFIRMATION_JSP)
              .forward(request, response);
    }
  }

  public final void confirmPay(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    int goodId = getGoodIdFromSession(request);
    User user = getUserFromSession(request);
    String randomCode = getCodeAndSendItToUser(user.getEmail());
    codeDao.addCode(new Code(randomCode, user.getId(), goodId));
    request.setAttribute(GOOD_ID, goodId);
    request.getRequestDispatcher(BUY_CONFIRMATION_JSP)
            .forward(request, response);
  }

  private User getUserFromSession(final HttpServletRequest request) {
    return (User) request.getSession().getAttribute(USER_ATTRIBUTE);
  }

  private int getGoodIdFromSession(final HttpServletRequest request) {
    return Integer.parseInt(request.getParameter(ID));
  }

  private String getCodeAndSendItToUser(final String email) {
    return MAIL_SERVICE.sendMail(email);
  }

  private String getCodeFromSession(final HttpServletRequest request) {
    return request.getParameter(CODE_PARAM);
  }
}
