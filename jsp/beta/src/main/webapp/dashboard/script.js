const isNullOrWhitespace = (value) => {
  return (value == null || value == undefined || value == "" || value.trim() == "");
};

function post(path, params, method) {

  //Create form 
  const hidden_form = document.createElement('form');

  // Set method to post by default 
  hidden_form.method = method || 'post';

  // Set path 
  hidden_form.action = path;

  for (const key in params) {
    if (params.hasOwnProperty(key)) {
      const hidden_input = document.createElement
        ('input');
      hidden_input.type = 'hidden';
      hidden_input.name = key;
      hidden_input.value = params[key];

      hidden_form.appendChild(hidden_input);
    }
  }

  document.body.appendChild(hidden_form);
  hidden_form.submit();
}

const validateField = (input, isEmail) => {

  if (isNullOrWhitespace(input.val()))
    return input.attr("placeholder") + " não pode estar vazio!";

  return undefined;
};

const createMovButton = document.getElementById("create-mov-btn");
const createInvestmentButton = document.getElementById("create-inv-btn");
const createPaymentCardButton = document.getElementById("create-card-btn");
const createBankButton = document.getElementById("create-bank-btn");

if (createMovButton)
  createMovButton.addEventListener("click", () => {
    var errorField = $("#mov-error");
    errorField.removeClass("active");
    errorField.text("");

    var fields = [
      $("#name"),
      $("#value"),
      $("#date"),
      $("#category"),
      $("#type")
    ];

    for (field of fields) {
      var validationResult = validateField(field);

      if (validationResult) {
        errorField.addClass("active");
        errorField.append(validationResult);
        return;
      }
    }

    post("/home/dashboard/movimentation",
      {
        name: document.getElementById("name").value,
        value: document.getElementById("value").value,
        date: document.getElementById("date").value,
        category: document.getElementById("category").value,
        type: document.getElementById("type").value
      });
  });

if (createInvestmentButton)
  createInvestmentButton.addEventListener("click", () => {
    var errorField = $("#inv-error");
    errorField.removeClass("active");
    errorField.text("");

    var fields = [
      $("#stockId"),
      $("#amount")
    ];

    for (field of fields) {
      var validationResult = validateField(field);

      if (validationResult) {
        errorField.addClass("active");
        errorField.append(validationResult);
        return;
      }
    }

    post("/home/dashboard/investments",
      {
        stockId: document.getElementById("stockId").value,
        amount: document.getElementById("amount").value,
      });
  });

if (createPaymentCardButton)
  createPaymentCardButton.addEventListener("click", () => {
    console.log("Passei pelo card");
    
    var errorField = $("#card-error");
    errorField.removeClass("active");
    errorField.text("");

    var fields = [
      $("#card-number"),
      $("#cvv"),
      $("#nickname"),
      $("#expiration-date"),
      $("#card-network"),
      $("#card-type")
    ];

    for (field of fields) {
      var validationResult = validateField(field);

      if (validationResult) {
        errorField.addClass("active");
        errorField.append(validationResult);
        return;
      }
    }

    post("/home/dashboard/settings",
      {
        requestFor: "card",
        cardNumber: document.getElementById("card-number").value,
        cvv: document.getElementById("cvv").value,
        nickname: document.getElementById("nickname").value,
        expirationDate: document.getElementById("expiration-date").value,
        cardNetwork: document.getElementById("card-network").value,
        cardType: document.getElementById("card-type").value,
      });
  });

  if (createBankButton)
  createBankButton.addEventListener("click", () => {
    console.log("Passei pelo bank");
    var errorField = $("#bank-error");
    errorField.removeClass("active");
    errorField.text("");

    var fields = [
      $("#name"),
      $("#bank-account"),
      $("#bank-agency"),
      $("#integration-token")
    ];

    for (field of fields) {
      var validationResult = validateField(field);

      if (validationResult) {
        errorField.addClass("active");
        errorField.append(validationResult);
        return;
      }
    }

    post("/home/dashboard/settings",
      {
        requestFor: "bank",
        name: document.getElementById("name").value,
        bankAccount: document.getElementById("bank-account").value,
        bankAgency: document.getElementById("bank-agency").value,
        integrationToken: document.getElementById("integration-token").value,
      });
  });
