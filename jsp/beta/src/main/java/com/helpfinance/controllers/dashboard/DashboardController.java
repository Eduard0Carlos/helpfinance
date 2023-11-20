package com.helpfinance.controllers.dashboard;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;

import com.helpfinance.models.InvestmentModel;
import com.helpfinance.models.MovimentationModel;
import com.helpfinance.models.PaymentCardModel;
import com.helpfinance.models.UserModel;
import com.helpfinance.services.ApiService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {

  public DashboardController() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    var loggedUser = (UserModel) req.getSession().getAttribute("loggedUser");
    var loggedUserImage = "https://ui-avatars.com/api/?name=" + (loggedUser == null ? "N F" : loggedUser.name)
        + "&background=random";

    var queryParams = new HashMap<String, String>();
    queryParams.put("from", "0001-01-01T00:00:00");
    queryParams.put("to", "3099-01-01T00:00:00");

    var today = new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now()));

    var todayParams = new HashMap<String, String>();
    todayParams.put("from", today + "T00:00:00");
    todayParams.put("to", today + "T23:59:59");

    var todayMovimentations = ApiService.doGet("v1/movimentation/" + loggedUser.id, MovimentationModel[].class,
        todayParams);

    var movimentations = ApiService.doGet("v1/movimentation/" + loggedUser.id, MovimentationModel[].class, queryParams);

    var cards = ApiService.doGet("v1/paymentcard/" + loggedUser.id, PaymentCardModel[].class);

    var investments = ApiService.doGet("v1/investment/" + loggedUser.id, InvestmentModel[].class);

    if (movimentations.data != null)
      req.setAttribute("totalMovimentations", Arrays.asList(movimentations.data).size());

    if (cards.data != null)
      req.setAttribute("totalCards", Arrays.asList(cards.data).size());

    if (investments.data != null)
      req.setAttribute("totalInvestments", Arrays.asList(investments.data).size());

    req.setAttribute("loggedUserName", loggedUser == null ? "" : loggedUser.name);
    req.setAttribute("loggedUserImage", loggedUserImage);

    req.setAttribute("currentBalance", loggedUser.balance);

    if (todayMovimentations.data != null) {
      var todayOutgoing = Arrays.asList(todayMovimentations.data).stream().mapToInt(x -> Integer.valueOf(x.amount))
          .sum();

      var width = todayOutgoing != 0 && loggedUser.monthlySpendingsLimit != 0
          ? String.format("%.2f", ((todayOutgoing / loggedUser.monthlySpendingsLimit) * 100))
          : "0";

      width = Integer.valueOf(width) > 100 ? "100" : width;

      req.setAttribute("width", width);
      req.setAttribute("todayOutgoing", todayOutgoing);
      req.setAttribute("diaryLimitWidth", width);
      req.setAttribute("diaryLimit", loggedUser.monthlySpendingsLimit);
    } else {
      req.setAttribute("width", 0);
      req.setAttribute("todayOutgoing", 0);
      req.setAttribute("diaryLimitWidth", 0);
      req.setAttribute("diaryLimit", 0);
    }

    req.setAttribute("dashboardClass", "active");
    req.setAttribute("documentClass", "");
    req.setAttribute("graphClass", "");
    req.setAttribute("profileClass", "");

    req.setAttribute("title", "Dashboard");

    req.getRequestDispatcher("dashboard/index.jsp").forward(req, resp);
  }
}