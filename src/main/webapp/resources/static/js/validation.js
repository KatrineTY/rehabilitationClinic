const LOGIN_PATTEN = /^[a-zA-Z]+(\.[a-zA-Z]+)*$/g;

let form = document.getElementById('loginForm');
let login = document.getElementById('login');
let generateError = function (text) {
    let error = document.createElement('div');
    error.className = 'error';
    error.style.color = 'red';
    error.innerHTML = text;
    return error;
};

let removeValidation = function () {
    let errors = form.querySelectorAll('.error');
    for (let i = 0; i < errors.length; i++) {
        errors[i].remove();
    }
};

let checkLogin = function (event) {
    let stopSubmit;

    if (!login.value.match(LOGIN_PATTEN)) {
        stopSubmit = true;
        console.log('invalid');
        let error = generateError('Login must consist of letters or "."');
        login.parentElement.insertBefore(error, login)
    }

    if (stopSubmit) {
        event.preventDefault();
    }

};

form.addEventListener('submit', function (event) {
    removeValidation();
    checkLogin(event);
});