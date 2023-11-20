<div class="signin">
  <form action="javascript:void(0);">
    <h1>Entrar</h1>
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
      <input id="signin-email" name="signin-email" type="email" placeholder="Email" />
      <input id="signin-password" name="signin-password" type="password" placeholder="Senha" />
      <p class="error" id="signin-error"></p>
    </section>
    <button type="submit" id="signin-confirm" class="confirm">Entrar</button>
  </form>
</div>