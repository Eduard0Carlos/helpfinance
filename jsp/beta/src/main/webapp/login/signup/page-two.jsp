<div class="signup-page" id="signup-two">
  <form action="javascript:void(0);">
    <section class="title">
      <h1>Endereço</h1>
      <p>2/4</p>
    </section>
    <section class="inputs">
      <div class="union">
        <input name="country" id="country" type="text" placeholder="País" />
        <input name="uf" id="uf" type="text" placeholder="UF" maxlength="3" />
      </div>
      <div class="union">
        <input name="city" id="city" placeholder="Cidade" type="text" />
        <input name="cep" id="cep" placeholder="CEP" type="text" maxlength="9" />
      </div>
      <div class="union">
        <input name="district" id="district" placeholder="Bairro" type="text" />
        <input name="number" id="number" placeholder="Número" type="text" />
      </div>
      <input name="street" id="street" placeholder="Rua" type="text" />
      <p class="error" id="page-two-error"></p>
    </section>
    <section class="buttons">
      <div class="back" id="signup-two-back-btn">Voltar</div>
      <button type="submit" class="forward" id="signup-two-btn">Continuar</button>
    </section>
  </form>
</div>