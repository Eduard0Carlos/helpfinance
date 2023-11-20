<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <div style="grid-template-columns: 50% 50%; grid-template-rows: 50% 50%;" class="grid">
          <div class="balance-overview-container">
            <div class="title">
              <h1>Saldo</h1>
            </div>
            <div class="money-box-background">
              <div class="money-box">
                <%@include file="/resources/images/visibility_on.svg" %>
                  <h1 class="money-box-balance">${currentBalance}</h1>
              </div>
            </div>
            <div class="title">
              <h1>Limite Diário</h1>
            </div>
            <div class="bar-chart build-in">
              <div class="bar-chart-bars">
                <rect
                  style="animation-name: width-animation; animation-duration: 2s; animation-direction: forwards; width: ${width}%; background-color: #39DAC5; filter: drop-shadow(0 0 5px #39DAC5)"
                  class="value"></rect>
                <rect class="background"></rect>
                <div class="infos">
                  <h1 class="current-value">${todayOutgoing}</h1>
                  <h1 class="porcentage">${diaryLimitWidth}%</h1>
                  <h1 class="total-value">${diaryLimit}</h1>
                </div>
              </div>
            </div>
          </div>
          <div class="mov-overview-container">
            <div class="title">
              <h1>Movimentações</h1>
            </div>
            <div class="count">${totalMovimentations}</div>
          </div>
          <div class="cards-overview-container">
            <div class="title">
              <h1>Cartões</h1>
            </div>
            <div class="count">${totalCards}</div>
          </div>
          <div class="investments-overview-container">
            <div class="title">
              <h1>Investimentos</h1>
            </div>
            <div class="count">${totalInvestments}</div>
          </div>
        </div>