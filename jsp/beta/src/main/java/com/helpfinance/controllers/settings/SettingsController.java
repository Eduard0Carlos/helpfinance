package com.helpfinance.controllers.settings;

import java.io.IOException;
import java.util.Arrays;

import com.helpfinance.models.BankModel;
import com.helpfinance.models.PaymentCardModel;
import com.helpfinance.models.UserModel;
import com.helpfinance.services.ApiService;
import com.helpfinance.utils.jsonUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard/settings")
public class SettingsController extends HttpServlet {

  public SettingsController() {
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
    req.setAttribute("graphClass", "");
    req.setAttribute("profileClass", "active");

    req.setAttribute("title", "Configurações");

    var cards = ApiService.doGet("v1/paymentcard/" + loggedUser.id, PaymentCardModel[].class);
    req.getSession().setAttribute("cards", Arrays.asList(cards.data));

    var banks = ApiService.doGet("v1/bank/" + loggedUser.id, BankModel[].class);
    req.getSession().setAttribute("banks", Arrays.asList(banks.data));

    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    var loggedUser = (UserModel) request.getSession().getAttribute("loggedUser");

    var requestFor = request.getParameter("requestFor");
    if (requestFor.toLowerCase().contains("card")) {
      var paymentCardModel = new PaymentCardModel();

      paymentCardModel.userId = loggedUser.id;
      paymentCardModel.cardNumber = request.getParameter("cardNumber");
      paymentCardModel.cvv = request.getParameter("cvv");
      paymentCardModel.expirationDate = request.getParameter("expirationDate");
      paymentCardModel.nickname = request.getParameter("nickname");
      paymentCardModel.paymentNetwork = request.getParameter("cardNetwork");
      paymentCardModel.paymentType = request.getParameter("cardType");

      var apiResult = ApiService.doPost("v1/paymentcard", jsonUtils.toJson(paymentCardModel), PaymentCardModel.class);

      if (!apiResult.success) {
        request.setAttribute("errorMessage", apiResult.errors != null ? apiResult.errors.get(0) : "");
      }
    } else {
      var bankModel = new BankModel();

      bankModel.userId = loggedUser.id;
      bankModel.bankAccountNumber = Integer.valueOf(request.getParameter("bankAccount"));
      bankModel.bankAgencyNumber = Integer.valueOf(request.getParameter("bankAgency"));
      bankModel.integrationToken = request.getParameter("integrationToken");
      bankModel.name = request.getParameter("name");

      var apiResult = ApiService.doPost("v1/bank", jsonUtils.toJson(bankModel), BankModel.class);

      if (!apiResult.success) {
        request.setAttribute("errorMessage", apiResult.errors != null ? apiResult.errors.get(0) : "");
      }
    }

    response.sendRedirect("/home/dashboard/settings");
  }
}
