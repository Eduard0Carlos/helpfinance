package com.helpfinance.controllers.investment;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

import com.helpfinance.models.InvestmentModel;
import com.helpfinance.models.MovimentationModel;
import com.helpfinance.models.UserModel;
import com.helpfinance.services.ApiService;
import com.helpfinance.utils.jsonUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard/investments")
public class InvestmentController extends HttpServlet {

  public InvestmentController() {
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
    req.setAttribute("documentClass", "");
    req.setAttribute("graphClass", "active");
    req.setAttribute("profileClass", "");

    req.setAttribute("title", "Investimentos");

    var investments = ApiService.doGet("v1/investment/" + loggedUser.id, InvestmentModel[].class);

    req.getSession().setAttribute("investments", Arrays.asList(investments.data));

    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }

    @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    var loggedUser = (UserModel) request.getSession().getAttribute("loggedUser");
    var investmentModel = new InvestmentModel();

    investmentModel.userId = loggedUser.id;
    investmentModel.stockId = request.getParameter("stockId");
    investmentModel.amount = Integer.parseInt(request.getParameter("amount"));

    var apiResult = ApiService.doPost("v1/investment", jsonUtils.toJson(investmentModel), InvestmentModel.class);

    if (!apiResult.success) {
      request.setAttribute("errorMessage", apiResult.errors != null ? apiResult.errors.get(0) : "");
    }

    response.sendRedirect("/home/dashboard/investments");
  }
}