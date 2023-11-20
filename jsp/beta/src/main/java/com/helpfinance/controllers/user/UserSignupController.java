package com.helpfinance.controllers.user;

import java.io.IOException;

import com.helpfinance.models.AddressModel;
import com.helpfinance.models.JobModel;
import com.helpfinance.models.UserModel;
import com.helpfinance.services.ApiService;
import com.helpfinance.utils.jsonUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/signup")
public class UserSignupController extends HttpServlet {

    public UserSignupController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var userModel = new UserModel();

        userModel.name = (String) request.getParameter("name");
        userModel.email = (String) request.getParameter("email");
        userModel.password = (String) request.getParameter("password");
        userModel.birthdate = request.getParameter("date");

        if (request.getParameter("jobCompany").trim() != "") {
            userModel.job = new JobModel();

            userModel.job.companyName = (String) request.getParameter("jobCompany");
            if (request.getParameter("jobSallary") != null && request.getParameter("jobSallary") != "")
                userModel.job.netSallary = Integer.valueOf((String) request.getParameter("jobSallary"));
            userModel.job.title = (String) request.getParameter("jobRole");
        }

        userModel.address = new AddressModel();

        userModel.address.cep = (String) request.getParameter("addressCep");
        userModel.address.country = (String) request.getParameter("addressCountry");
        userModel.address.district = (String) request.getParameter("addressDistrict");
        userModel.address.city = (String) request.getParameter("addressCity");
        userModel.address.houseNumber = (String) request.getParameter("addressHouseNumber");
        userModel.address.state = (String) request.getParameter("addressUf");
        userModel.address.street = (String) request.getParameter("addressStreet");

        var apiResult = ApiService.doPost("v1/users", jsonUtils.toJson(userModel), UserModel.class);

        if (!apiResult.success) {
            request.setAttribute("errorMessage", apiResult.errors != null ? apiResult.errors.get(0) : "");
            response.sendRedirect("/home");
            return;
        }

        request.getSession().setAttribute("loggedUser", apiResult.data);

        response.sendRedirect("/home/dashboard");
    }
}
