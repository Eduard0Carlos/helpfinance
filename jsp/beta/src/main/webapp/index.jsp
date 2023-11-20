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
  <script type="text/javascript" src="https://maps.google.com/maps/api/js?key=AIzaSyCwD0meL7xd3sCW8mhg0H0FUWqoKmiAkxw&sensor=false" ></script>
  <link rel="icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<main>
   <div class="home-page">
      <div class="header">
        <section class="logo">
          <%@include file="resources/images/logo.svg" %>
        </section>
        
        <section class="pages">
          <a><h1>Início</h1></a>
          <a><h1>Ajuda</h1></a> 
          <a><h1>Sobre</h1></a> 
        </section>

        <section class="login hidden">

        </section>
      </div>
      <div class="content">

      </div>
      <div class="content">
        <div class="texts">
          <h1>CONTROLE TUDO</h1>
          <h1>A QUALQUER HORA</h1>
          <h1>EM QUALQUER LUGAR</h1>
        </div>
          <div onclick="window.location.href = '/home/dashboard'" class="start-button">Comece Agora</div>
      </div>
    </div>
</main>
</body>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</html>
