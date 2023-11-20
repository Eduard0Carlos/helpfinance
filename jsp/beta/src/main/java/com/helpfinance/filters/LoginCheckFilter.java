package com.helpfinance.filters;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCheckFilter implements Filter {

  public static final String APPLICATION_URL = "http://localhost:8081";
  public static final String[] ALLOWED_URLS = { "/home", "/home/login", "", "/login", "/home/login/", "/home/", "/",
      "/login/", "/home/user/signin", "/home/user/signin/", "user/signin", "user/signin/", "/home/user/signup",
      "/home/user/signup/", "user/signup", "user/signup/" };

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    try {
      var req = (HttpServletRequest) request;
      var res = (HttpServletResponse) response;

      var urlFormatted = req.getRequestURL().toString().replace(APPLICATION_URL, "");

      if ((!urlFormatted.contains(".jsp") && urlFormatted.contains(".")) ||
          Arrays.asList(ALLOWED_URLS).contains(urlFormatted)) {
        chain.doFilter(request, response);
        return;
      }

      if (req.getSession().getAttribute("loggedUser") == null) {
        res.sendRedirect("/home/login");
        return;
      }

      chain.doFilter(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      var res = (HttpServletResponse) response;
      res.sendRedirect("/home/notfound");
    }
  }

  @Override
  public void destroy() {

  }
}
