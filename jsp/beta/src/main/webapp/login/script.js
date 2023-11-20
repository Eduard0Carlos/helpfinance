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

const specialChars = /[ `!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?~]/;

const isNullOrWhitespace = (value) => {
  return (value == null || value == undefined || value == "" || value.trim() == "");
};

const validateField = (input, isEmail) => {

  if (isNullOrWhitespace(input.val()))
    return input.attr("placeholder") + " não pode estar vazio!";

  if (isEmail && input.val() && (!input.val().includes("@") || !input.val().includes(".com"))) {
    return "Informe um email válido";
  }
  return undefined;
};

const validatePassword = (password, confirmedPassword) => {

  if (!/[0-9]/.test(password.val())) {
    return "A senha deve ter ao menos um número";
  }

  if (!/[A-Za-z]/.test(password.val())) {
    return "A senha deve ter ao menos uma letra";
  }

  if (!/[A-Z]/.test(password.val())) {
    return "A senha deve ter ao menos uma letra maíuscula";
  }

  if (!specialChars.test(password.val())) {
    return "A senha deve ter ao menos um caracter especial";
  }

  if (password.val() != confirmedPassword.val()) {
    return "As senhas não conhecidem";
  }

  return undefined;
};

const container = document.getElementById("container");
const registerBtn = document.getElementById("signup-overlay");
const loginBtn = document.getElementById("signin-overlay");

const signupPageOneContainer = document.getElementById("signup-one");
const signupPageOneButton = document.getElementById("signup-one-btn");

const signupPageTwoContainer = document.getElementById("signup-two");
const signupPageTwoBackButton = document.getElementById("signup-two-back-btn");
const signupPageTwoButton = document.getElementById("signup-two-btn");

const signupPageThreeContainer = document.getElementById("signup-three");
const signupPageThreeBackButton = document.getElementById("signup-three-back-btn");
const signupPageThreeButton = document.getElementById("signup-three-btn");

const signupPageFourContainer = document.getElementById("signup-four");
const signupPageFourBackButton = document.getElementById("signup-four-back-btn");
const signupPageFourButton = document.getElementById("signup-four-btn");

const signinPageButton = document.getElementById("signin-confirm");

const toggleSideFunction = () => {
  if (container.classList.contains("active"))
    container.classList.remove("active");
  else
    container.classList.add("active");
};

const toggleRegister = () => {
  toggleSideFunction();
  signupPageOneContainer.classList.add("in-use");
}

registerBtn.addEventListener("click", toggleRegister);
loginBtn.addEventListener("click", toggleSideFunction);


//Forward - Page 1
signupPageOneButton.addEventListener("click", () => {
  var errorField = $("#page-one-error");
  errorField.removeClass("active");
  errorField.text("");

  var nameValidationResult = validateField($("#name"));
  var emailValidationResult = validateField($("#email"), true);
  var dateValidationResult = validateField($("#birthdate"));

  if (nameValidationResult) {
    errorField.addClass("active");
    errorField.append(nameValidationResult);
    return;
  }

  if (emailValidationResult) {
    errorField.addClass("active");
    errorField.append(emailValidationResult);
    return;
  }

  if (dateValidationResult) {
    errorField.addClass("active");
    errorField.append(dateValidationResult);
    return;
  }

  signupPageTwoContainer.classList.add("in-use");

  signupPageOneContainer.classList.add("complete");
  signupPageOneContainer.classList.remove("in-use");
});

//Forward - Page 2
signupPageTwoButton.addEventListener("click", () => {
  var errorField = $("#page-two-error");
  errorField.removeClass("active");
  errorField.text("");

  var addressFields = [
    $("#country"),
    $("#uf"),
    $("#city"),
    $("#cep"),
    $("#district"),
    $("#number"),
    $("#street")
  ];

  for (field of addressFields) {
    var validationResult = validateField(field);

    if (validationResult) {
      errorField.addClass("active");
      errorField.append(validationResult);
      return;
    }
  }

  signupPageTwoContainer.classList.add("complete");
  signupPageTwoContainer.classList.remove("in-use");

  signupPageThreeContainer.classList.add("in-use");
});

//Forward - Page 3
signupPageThreeButton.addEventListener("click", () => {
  signupPageThreeContainer.classList.add("complete");
  signupPageThreeContainer.classList.remove("in-use");

  signupPageFourContainer.classList.add("in-use");
});


//Back - Page 2
signupPageTwoBackButton.addEventListener("click", () => {
  signupPageTwoContainer.classList.remove("in-use");

  signupPageOneContainer.classList.remove("complete");
  signupPageOneContainer.classList.add("in-use");
});

//Back - Page 3
signupPageThreeBackButton.addEventListener("click", () => {
  signupPageThreeContainer.classList.remove("in-use");

  signupPageTwoContainer.classList.remove("complete");
  signupPageTwoContainer.classList.add("in-use");
});

//Back - Page 4
signupPageFourBackButton.addEventListener("click", () => {
  signupPageFourContainer.classList.remove("in-use");

  signupPageThreeContainer.classList.remove("complete");
  signupPageThreeContainer.classList.add("in-use");
});

const refreshInputsByCep = (cep) => {
  fetch(`https://viacep.com.br/ws/${cep}/json/`).then(response => {
    if (!response.ok)
      return;

    response.json().then(apiResult => {
      $("#country").val("Brasil");
      $("#uf").val(apiResult.uf);
      $("#city").val(apiResult.localidade);
      $("#district").val(apiResult.bairro);
      $("#street").val(apiResult.logradouro);
    });

  });
};

document.querySelector("#cep").addEventListener("change", (e) => {
  refreshInputsByCep(e.target.value.replace("-", ""));
});

const confirmSignup = () => {
  post("/home/user/signup",
    {
      name: document.getElementById("name").value,
      email: document.getElementById("email").value,
      date: document.getElementById("birthdate").value,
      password: md5.md5(document.getElementById("password").value, 32),
      addressCountry: document.getElementById("country").value,
      addressUf: document.getElementById("uf").value,
      addressCity: document.getElementById("city").value,
      addressCep: document.getElementById("cep").value,
      addressDistrict: document.getElementById("district").value,
      addressHouseNumber: document.getElementById("number").value,
      addressStreet: document.getElementById("street").value,
      jobCompany: document.getElementById("company").value,
      jobRole: document.getElementById("role").value,
      jobSallary: document.getElementById("sallary").value
    });

};

const confirmSignin = () => {
  post("/home/user/signin",
    {
      email: document.getElementById("signin-email").value,
      password: md5.md5(document.getElementById("signin-password").value, 32),
    });

};

//Done Signup
signupPageFourButton.addEventListener("click", () => {
  var errorField = $("#page-four-error");
  errorField.removeClass("active");
  errorField.text("");

  var passwordValidationResult = validatePassword($("#password"), $("#confirm-password"));

  if (passwordValidationResult) {
    errorField.append(passwordValidationResult);
    errorField.addClass("active");
    return;
  }

  confirmSignup();
});

//Done Signin
signinPageButton.addEventListener("click", () => {
  fetch("http://localhost:8080/api/v1/users?email=" + $("#signin-email").val() + "&password=" + md5.md5($("#signin-password").val(), 32)).then(response => {
    var errorField = $("#signin-error");
    errorField.removeClass("active");
    errorField.text("");

    if (response.status != 200 && response.status != 400) {
      errorField.append("Algo deu errado! Tente novamente mais tarde");
      errorField.addClass("active");
      return;
    }

    response.json().then(apiResult => {
      console.log(apiResult);

      if (!apiResult.success) {
        errorField.append(apiResult.errors[0]);
        errorField.addClass("active");
        return;
      }

      confirmSignin();
    });
  });
});