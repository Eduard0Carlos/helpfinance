<div class="signup-page" id="signup-one">
  <form action="javascript:void(0);">
    <h1>Criar uma Conta</h1>
    <section class="plataforms">
      <a class="platform"><%@include file="/resources/images/facebook-icon.svg" %></a>
      <a class="platform"><%@include file="/resources/images/google-icon.svg" %></a>
      <a class="platform"><%@include file="/resources/images/apple-icon.svg" %></a>
    </section>
    <div class="divider">
      <hr />
      <h3>ou</h3>
      <hr />
    </div>
    <section class="inputs">
      <input id="name" name="name" type="text" placeholder="Nome" />
      <input id="email" name="email" type="email" placeholder="Email" />
      <input id="birthdate" name="birthdate" type="date" placeholder="Data de Nascimento" />
      <p class="error" id="page-one-error"></p>
    </section>
    <button class="confirm" id="signup-one-btn">Continuar</button>
  </form>
</div>