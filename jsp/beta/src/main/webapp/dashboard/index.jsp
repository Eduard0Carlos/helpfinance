<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
      <title>Help Finance</title>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <meta name="theme-color" content="#333333" />
      <meta name="description" content="Fintech" />
        
      <link rel="icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css" />
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css" />
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard/index.css" />
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard/movimentations/index.css" />
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard/settings/index.css" />
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard/investments/index.css" />      
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard/overview/index.css" />

      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

      <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.2.1/axios.min.js"></script>

      <script src="https://cdn.jsdelivr.net/npm/md5js@1.0.7/dist/md5.min.js"></script>

      <script type="text/javascript"
        src="https://maps.google.com/maps/api/js?key=AIzaSyCwD0meL7xd3sCW8mhg0H0FUWqoKmiAkxw&sensor=false"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    </head>

    <main>
    <div class="dashboard">
      <div class="screen">
        <div class="left-navbar">
          <a href="/home" class="logo">
            <%@include file="/resources/images/logo-icon.svg" %>
          </a>
          <a href="/home/dashboard" class="${requestScope.dashboardClass}">
            <%@include file="/resources/images/dashboard-icon.svg" %>
          </a>
          <a href="/home/dashboard/movimentations" class="${requestScope.documentClass}">
            <%@include file="/resources/images/document-icon.svg" %>
          </a>
          <a href="/home/dashboard/investments" class="${requestScope.graphClass}">
            <%@include file="/resources/images/graph-icon.svg" %>
          </a>
          <a href="/home/dashboard/settings" class="${requestScope.profileClass}">
            <%@include file="/resources/images/profile-icon.svg" %>
          </a>
        </div>
        <div class="container">
          <div class="header">
            <section class="page-title">
              <h1>${requestScope.title}</h1>
            </section>
            <section>
              <div class="profile">
                <%@include file="/resources/images/notifications.svg" %>
                <div class="money-box">
                  <%@include file="/resources/images/visibility_off.svg" %>
                  <h1 class="money-box-balance">*********</h1>
                </div>
                <img src="${requestScope.loggedUserImage}"/>
              </div>
            </section>
          </div>

          <c:if test="${requestScope.title == 'Dashboard'}">
            <c:import url="./overview/index.jsp" charEncoding="UTF-8"/>
          </c:if>

          <c:if test="${requestScope.title == 'Movimentações'}">
            <c:import url="./movimentations/index.jsp" charEncoding="UTF-8"/>
          </c:if>

          <c:if test="${requestScope.title == 'Configurações'}">
            <c:import url="./settings/index.jsp" charEncoding="UTF-8"/>
          </c:if>

          <c:if test="${requestScope.title == 'Investimentos'}">
            <c:import url="./investments/index.jsp" charEncoding="UTF-8"/>
          </c:if>

          <div>
        </div>
      </div>
    </div>
    </main>
  </body>

  <script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/dashboard/script.js"></script>

</html>