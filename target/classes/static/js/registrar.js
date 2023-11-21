$(document).ready(function () {

});

function limpiar() {
    document.getElementById('txtEmail').value = "";
    document.getElementById('txtPassword').value = "";
    document.getElementById('txtRepetirPassword').value = "";
}

async function registrarUsuario() {
  errCor.style.display = 'none';
  errCon.style.display = 'none';
  let email = document.getElementById('txtEmail').value;
  let password = document.getElementById('txtPassword').value;
  let repetirPassword = document.getElementById('txtRepetirPassword').value;

  if (repetirPassword != password) {
    alert('La contraseña que escribiste es diferente.');
    document.getElementById('txtPassword').value = "";
    document.getElementById('txtRepetirPassword').value = "";
  } else {
    let user = { "email": email, "password": password };

    const request = await fetch('usuarios/registrar-usuario', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(user)
    });

    const res = await request.text();

    switch (res) {
      case "correo":
        errCor.style.display = 'block';
        break;
    
      case "clave":
        errCon.style.display = 'block';
        break;
    
      case "ya esta":
        alert("¡Esa cuenta ya existe!!");
        limpiar();
        break;
    
      case "creado":
        alert("¡La cuenta fue creada con éxito!");
        window.location.href = 'login.html';
        break;
    
      default:
        console.log("No se reconoce el resultado retornado.");
    }
  }
}
