package com.helpfinance.controllers.movimentation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import com.helpfinance.models.MovimentationModel;
import com.helpfinance.models.UserModel;
import com.helpfinance.services.ApiService;
import com.helpfinance.utils.jsonUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard/movimentations")
public class MovimentationController extends HttpServlet {

  public MovimentationController() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    var loggedUser = (UserModel) req.getSession().getAttribute("loggedUser");
    var loggedUserImage = "https://ui-avatars.com/api/?name=" + (loggedUser == null ? "N F" : loggedUser.name)
        + "&background=random";

    req.setAttribute("loggedUserName", loggedUser == null ? "" : loggedUser.name);
    req.setAttribute("loggedUserImage", loggedUserImage);

    req.setAttribute("dashboardClass", "");
    req.setAttribute("documentClass", "active");
    req.setAttribute("graphClass", "");
    req.setAttribute("profileClass", "");

    req.setAttribute("title", "Movimentações");

    var queryParams = new HashMap<String, String>();
    queryParams.put("from", "0001-01-01T00:00:00");
    queryParams.put("to", "3099-01-01T00:00:00");

    var movimentations = ApiService.doGet("v1/movimentation/" + loggedUser.id, MovimentationModel[].class, queryParams);

    for (var movimentation : movimentations.data) {
      try {
        var formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        var format = formatter.format(formatter.parse(movimentation.date));
        movimentation.date = format;
      } catch (Exception e) {
        // TODO: handle exception
      }

    }

    req.getSession().setAttribute("movimentations", Arrays.asList(movimentations.data));

    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    var loggedUser = (UserModel) request.getSession().getAttribute("loggedUser");
    var movModel = new MovimentationModel();

    movModel.userId = loggedUser.id;
    movModel.title = (String) request.getParameter("name");
    movModel.amount = (String) request.getParameter("value");
    movModel.date = (String) request.getParameter("date");
    movModel.category = (String) request.getParameter("category");
    movModel.movType = request.getParameter("type");

    var apiResult = ApiService.doPost("v1/movimentation", jsonUtils.toJson(movModel), MovimentationModel.class);

    if (!apiResult.success) {
      request.setAttribute("errorMessage", apiResult.errors != null ? apiResult.errors.get(0) : "");
    }

    response.sendRedirect("/home/dashboard/movimentation");
  }
}