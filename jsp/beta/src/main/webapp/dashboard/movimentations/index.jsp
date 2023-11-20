<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.helpfinance.models.MovimentationModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="grid-template-columns: 100%; grid-template-rows: 100%;" class="grid">
  <div class="full-container">
  <div class="movimentations">

<c:forEach var="movi" items="${movimentations}">
<div class="movimentation-card">
           <h1>${movi.title}</h1>
            <div class="values ${movi.movType}">
                <c:choose>
                    <c:when test="${movi.movType == 'INCOMING'}">
                        <jsp:include page="/resources/images/up-icon.svg" />
                    </c:when>    
                    <c:otherwise>
                        <jsp:include page="/resources/images/down-icon.svg" />
                    </c:otherwise>
                </c:choose>
                <h2>R$ ${movi.amount}</h2>
            </div>
            <h3>${movi.date}</h3>
</div>
</c:forEach>
   
  </div>

  <button class="add-button" data-bs-toggle="modal" data-bs-target="#movCard">Adicionar</button>

  <div class="modal fade" id="movCard" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Criar Movimentação</h5>
        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="contents">
        <div class="inputs">
          <input type="text" id="name" placeholder="Nome"/>
          <input type="number" id="value" placeholder="Valor"/>
          <input type="date" id="date" placeholder="Data"/>
        </div>
        <div class="dropdowns">
          <select placeholder="Categoria" class="form-control" id="category">
            <option value>Selecione</option>
            <option value="FOOD">Comida</option>
            <option value="BILL">Conta</option>
            <option value="RECREATION">Lazer</option>
          </select>
          <select placeholder="Tipo" class="form-control" id="type">
            <option value>Selecione</option>
            <option value="INCOMING">Entrada</option>
            <option value="OUTGOING">Saída</option>
          </select>
        </div>
        <p class="error" id="mov-error"></p>
      </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button type="button" id="create-mov-btn" class="btn btn-primary">Criar</button>
      </div>
    </div>
  </div>
</div>
</div>
</div>