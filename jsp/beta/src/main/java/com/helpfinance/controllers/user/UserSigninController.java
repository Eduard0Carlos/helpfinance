package com.helpfinance.controllers.user;

import java.io.IOException;
import java.util.HashMap;

import com.helpfinance.models.UserModel;
import com.helpfinance.services.ApiService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/signin")
public class UserSigninController extends HttpServlet {

    public UserSigninController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var email = (String) request.getParameter("email");
        var password = (String) request.getParameter("password");

        var queryParams = new HashMap<String, String>();

        queryParams.put("email", email);        
        queryParams.put("password", password);

        var apiResult = ApiService.doGet("v1/users", UserModel.class, queryParams);

        if (!apiResult.success) {
            request.setAttribute("errorMessage", apiResult.errors.get(0));
            response.sendRedirect("/home/login");
            return;
        }

        request.getSession().setAttribute("loggedUser", apiResult.data);
        
        response.sendRedirect("/home/dashboard");
    }
}
