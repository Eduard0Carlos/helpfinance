<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.helpfinance.models.MovimentationModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="grid-template-columns: 100%; grid-template-rows: 100%;" class="grid">
  <div class="investment-container">
  <div class="investments">
  <c:forEach var="inv" items="${investments}">
    <div class="investment-card">
      <h1>${inv.stockId}</h1>
      <h2>R$ ${inv.amount}</h2>
    </div>
    </c:forEach>
  </div>

  <button class="add-button" data-bs-toggle="modal" data-bs-target="#investmentModal">Adicionar</button>

  <div class="modal fade" id="investmentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Criar Investimento</h5>
        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="contents">
        <div class="inputs">
          <input type="text" id="stockId" placeholder="Id na Bolsa"/>
          <input type="number" id="amount" placeholder="Quantidade"/>
        </div>
        <p class="error" id="inv-error"></p>
      </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button type="button" id="create-inv-btn" class="btn btn-primary">Criar</button>
      </div>
    </div>
  </div>
</div>
  </div>
</div>