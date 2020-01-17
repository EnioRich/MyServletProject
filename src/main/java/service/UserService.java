package service;

import dao.HibernateImplementationDao;
import dao.UserDao;
import model.Role;
import model.User;
import utils.HashPassword;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class UserService {
  private static final String USERNAME_PARAM = "username";
  private static final String PASSWORD_PARAM = "password";
  private static final String USER_LIST_PARAM = "userList";
  private static final String ID_PARAM = "id";
  private static final String NEW_PASSWORD_PARAM = "newpassword";
  private static final String NEW_USERNAME_PARAM = "newusername";
  private static final String LOGIN_ATTRIBUTE = "login";
  private static final String USER_ATTRIBUTE = "user";
  private static final String UPDATES_ATTRIBUTE = "updates";
  private static final String REGISTERED_ATTRIBUTE = "registered";
  private static final String DELETED_ATTRIBUTE = "deleted";
  private static final String NEW_PASSWORD_IS = "New password is: ";
  private static final String NEW_USERNAME_IS = "New username is: ";
  private static final String IS_DELETED = " is deleted";
  private static final String IS_REGISTERED = " is registered";
  private static final String NEW_EMAIL = "linken.td@gmail.com";
  private static final String ADMIN_PAGE_JSP = "adminPage.jsp";
  private static final String GOODS_URL = "/goods";
  private static final String USER_NAME_ALREADY_EXIST_MESSAGE =
          "Username already exist";
  private static final String NO_SUCH_USER_MESSAGE =
          "There is no such user!";
  private static final String INCORRECT_USER_NAME_MESSAGE =
          "Incorrect username or password";
  private static final String UNABLE_FIND_USER_MESSAGE =
          "Couldn't find user";
  private UserDao userDao = new HibernateImplementationDao();

  public void getAllUsers(final HttpServletRequest request) {
    request.setAttribute(
            USER_LIST_PARAM,
            userDao.getAllUsers());
  }

  public void login(
          final HttpServletRequest request,
          final HttpServletResponse response)
          throws ServletException, IOException {
    String username = request.getParameter(USERNAME_PARAM);
    String password = request.getParameter(PASSWORD_PARAM);
    String hashedPasswordForCheck = getHashedPassword(username, password);
    User user = userDao.getUserByName(username);
    if (user.getPassword().equals(hashedPasswordForCheck)) {
      request.getSession().setAttribute(USER_ATTRIBUTE, user);
      if (user.getRole().equals(String.valueOf(Role.ADMIN))) {
        request.getRequestDispatcher(ADMIN_PAGE_JSP).forward(request, response);
      } else {
        request.getRequestDispatcher(GOODS_URL).forward(request, response);
      }
    } else {
      request.setAttribute(LOGIN_ATTRIBUTE, INCORRECT_USER_NAME_MESSAGE);
    }
  }

  public void changePasswordByAdmin(final HttpServletRequest request) {
    String requestPassword = request.getParameter(NEW_PASSWORD_PARAM);
    User user = getUserFromSession(request);
    boolean isNoUser = userDao.checkUser(user);
    if (!isNoUser) {
      String newPassword = requestPassword;
      newPassword = getHashedPassword(
              user.getUsername(), newPassword);
      userDao.updatePassword(user, newPassword);
      request.setAttribute(UPDATES_ATTRIBUTE,
              NEW_PASSWORD_IS + requestPassword);
    } else {
      request.setAttribute(UPDATES_ATTRIBUTE, UNABLE_FIND_USER_MESSAGE);
    }
  }

  public void changeUserNameByAdmin(final HttpServletRequest request) {
    String requestUsername = request.getParameter(NEW_USERNAME_PARAM);
    User user = getUserFromSession(request);
    boolean isNoUser = userDao.checkUser(user);
    if (!isNoUser) {
      userDao.updateUsername(user, requestUsername);
      request.setAttribute(UPDATES_ATTRIBUTE,
              NEW_USERNAME_IS + requestUsername);
    } else {
      request.setAttribute(UPDATES_ATTRIBUTE, UNABLE_FIND_USER_MESSAGE);
    }
  }

  public void deleteUser(final HttpServletRequest request) {
    User user = getUserFromSession(request);
    boolean ifUser = userDao.checkUser(user);
    if (!ifUser) {
      userDao.deleteUser(user);
      request.setAttribute(DELETED_ATTRIBUTE,
              user.getUsername() + IS_DELETED);
    } else {
      request.setAttribute(DELETED_ATTRIBUTE, NO_SUCH_USER_MESSAGE);
    }
  }

  public void registerUser(
          final HttpServletRequest request
  ) {
    User newUser = getUserParamsForRegistration(request);
    boolean isNewUser = userDao.checkUser(newUser);
    if (isNewUser) {
      String newHashedPassword = getHashedPassword(
              newUser.getUsername(), newUser.getPassword());
      newUser.setPassword(newHashedPassword);
      userDao.addUser(newUser);
      request.setAttribute(REGISTERED_ATTRIBUTE,
              newUser.getUsername() + IS_REGISTERED);
    } else {
      request.setAttribute(REGISTERED_ATTRIBUTE,
              USER_NAME_ALREADY_EXIST_MESSAGE);
    }
  }

  private User getUserParamsForRegistration(
          final HttpServletRequest request) {
    String username = request.getParameter(USERNAME_PARAM);
    String password = request.getParameter(PASSWORD_PARAM);
    return new User(username, NEW_EMAIL, password, Role.USER.toString());
  }

  private User getUserFromSession(final HttpServletRequest request) {
    int userId = Integer.parseInt(request.getParameter(ID_PARAM));
    return userDao.getUserById(userId);
  }

  private String getHashedPassword(
          final String username,
          final String password) {
    User user = userDao.getUserByName(username);
    return HashPassword.getShaSecurePassword(password, user.getSalt());
  }
}
