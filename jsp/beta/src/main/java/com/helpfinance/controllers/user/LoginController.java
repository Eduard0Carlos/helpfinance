package com.helpfinance.controllers.user;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {

  public LoginController() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    var loggedUser = req.getSession().getAttribute("loggedUser");

    if (loggedUser != null) {
      resp.sendRedirect("/home");
      return;
    }

    req.getRequestDispatcher("login/index.jsp").forward(req, resp);
  }
}