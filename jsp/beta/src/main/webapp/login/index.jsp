<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login/index.css" />
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
      <div class="login-page">
        <div class="transition"></div>
        <div class="container" id="container">
          <%-- paginas --%>

          <c:import url="./signup/page-one.jsp" charEncoding="UTF-8"/>
          <c:import url="./signup/page-two.jsp" charEncoding="UTF-8"/>
          <c:import url="./signup/page-three.jsp" charEncoding="UTF-8"/>
          <c:import url="./signup/page-four.jsp" charEncoding="UTF-8"/>

          <c:import url="./signin/index.jsp" charEncoding="UTF-8"/>

          <%-- overlay container --%>
            <div class="overlay-container">


              <div class="overlay">

                <div class="overlay-content left">
                  <%@include file="/resources/images/logo.svg" %>
                  <section class="texts">
                    <h1>Bem vindo</h1>
                    <p>Preencha seus detalhes pessoais e <br />comece a controlar suas finanças ainda hoje!</p>
                  </section>
                  <button class="ghost-button" id="signin-overlay">
                    Entrar
                  </button>
                </div>

                <div class="overlay-content right">
                  <%@include file="/resources/images/logo.svg" %>
                  <section class="texts">
                    <h1>Bem vindo novamente</h1>
                    <p>Para continuar conectado conosco, <br />entre com suas informações pessoais</p>
                  </section>
                  <button class="ghost-button" id="signup-overlay">
                    Criar Conta
                  </button>
                </div>
              </div>

            </div>
        </div>
      </div>
    </main>
    </body>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/login/script.js"></script>

    </html>