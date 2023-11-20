<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div style="grid-template-columns: 50% 50%; grid-template-rows: 100%;" class="grid">
      <div class="card-container">
        <div class="title">
          <h1>Cartões</h1>
        </div>

        <div class="cards">
          <c:forEach var="c" items="${cards}">
          <div class="payment-card">
            <h1>${c.cardNumber}</h1>
          </div>
          </c:forEach>
        </div>

        <button class="add-button" data-bs-toggle="modal" data-bs-target="#cardModal">Adicionar</button>

        <div class="modal fade" id="cardModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
          aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Adicionar Cartão</h5>
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <div class="contents">
                  <div class="inputs">
                    <input type="number" maxlength="16" id="card-number" placeholder="Número do Cartão" />
                    <input type="text" id="nickname" placeholder="Nome no cartão" />
                    <div class="union">
                      <input type="number" maxlength="3" id="cvv" placeholder="CVV" />
                      <input type="date" id="expiration-date" placeholder="Data de Expiração" />
                    </div>
                    <div class="dropdowns">
                      <select placeholder="Bandeira" class="form-control" id="card-network">
                        <option value>Selecione</option>
                        <option value="VISA">VISA</option>
                        <option value="MASTERCARD">MASTERCARD</option>
                        <option value="AMERICAN_EXPRESS">AMERICAN EXPRESS</option>
                        <option value="HIPERCARD">HIPERCARD</option>
                        <option value="ELO">ELO</option>
                      </select>
                      <select placeholder="Tipo" class="form-control" id="card-type">
                        <option value>Selecione</option>
                        <option value="DEBIT">Débito</option>
                        <option value="CREDIT">Crédito</option>
                      </select>
                    </div>
                  </div>
                  <p class="error" id="card-error"></p>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" id="create-card-btn" class="btn btn-primary">Criar</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="bank-container">
        <div class="title">
          <h1>Bancos</h1>
        </div>
        <div class="banks">
          <c:forEach var="b" items="${banks}">
          <div class="bank">
            <h1>${b.name}</h1>
          </div>
          </c:forEach>
        </div>
        <button class="add-button" data-bs-toggle="modal" data-bs-target="#bankModal">Adicionar</button>

        <div class="modal fade" id="bankModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
          aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Adicionar Banco</h5>
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <div class="contents">
                  <div class="inputs">
                    <input type="text" id="name" placeholder="Nome do Banco" />
                    <input type="number" id="bank-account" placeholder="Número da Conta" />
                    <input type="number" id="bank-agency" placeholder="Número da Conta" />
                    <input type="text" id="integration-token" placeholder="Token de Integração" />
                  </div>
                  <p class="error" id="bank-error"></p>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" id="create-bank-btn" class="btn btn-primary">Criar</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>