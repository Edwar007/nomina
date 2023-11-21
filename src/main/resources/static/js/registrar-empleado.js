$(document).ready(function () {
  cargarCargos();
  cargarAreas();
  cargarContratos();
  cargarBancos();
  cargarEps();
  cargarPensiones();
});

/*async function registrarUsuario() {
  let datos = {};
  datos.email = document.getElementById('txtEmail').value;
  datos.password = document.getElementById('txtPassword').value;

  let repetirPassword = document.getElementById('txtRepetirPassword').value;

  if (repetirPassword != datos.password) {
    alert('La contraseña que escribiste es diferente.');
    return;
  }
  const request = await fetch('usuarios/registrarUsuario', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  alert("La cuenta fue creada con exito!");
  window.location.href = 'login.html'
}*/

async function cargarCargos() {
  const select = document.querySelector("#txtCargo");
  const respuesta = await fetch("/cargar/cargos", {
    metod: "GET",
    headres: {
      Accept: "aplication/json",
      "Content-Type": "aplication/json",
    },
  });
  const cargos = await respuesta.json();

  for (let car of cargos) {
    let nuevaOpcion = document.createElement("option");
    nuevaOpcion.value = car.id;
    nuevaOpcion.text = car.nombre;
    select.add(nuevaOpcion);
  }  
}

async function cargarAreas() {
  const select = document.querySelector("#txtArea");
  const respuesta = await fetch("/cargar/areas", {
    metod: "GET",
    headres: {
      Accept: "aplication/json",
      "Content-Type": "aplication/json",
    },
  });
  const areas = await respuesta.json();

  for (let are of areas) {
    let nuevaOpcion = document.createElement("option");
    nuevaOpcion.value = are.id;
    nuevaOpcion.text = are.nombre;
    select.add(nuevaOpcion);
  }  
}

async function cargarContratos() {
  const select = document.querySelector("#txtTipoContrato");
  const respuesta = await fetch("/cargar/contratos", {
    metod: "GET",
    headres: {
      Accept: "aplication/json",
      "Content-Type": "aplication/json",
    },
  });
  const contratos = await respuesta.json();

  for (let con of contratos) {
    let nuevaOpcion = document.createElement("option");
    nuevaOpcion.value = con.id;
    nuevaOpcion.text = con.nombre;
    select.add(nuevaOpcion);
  } 
}

async function cargarBancos() {
  const select = document.querySelector("#txtBanco");
  const respuesta = await fetch("/cargar/bancos", {
    metod: "GET",
    headres: {
      Accept: "aplication/json",
      "Content-Type": "aplication/json",
    },
  });
  const bancos = await respuesta.json();

  for (let ban of bancos) {
    let nuevaOpcion = document.createElement("option");
    nuevaOpcion.value = ban.id;
    nuevaOpcion.text = ban.razonSocial;
    select.add(nuevaOpcion);
  } 
}

async function cargarEps() {
  const select = document.querySelector("#txtEps");
  const respuesta = await fetch("/cargar/eps", {
    metod: "GET",
    headres: {
      Accept: "aplication/json",
      "Content-Type": "aplication/json",
    },
  });
  const listaeps = await respuesta.json();

  for (let eps of listaeps) {
    let nuevaOpcion = document.createElement("option");
    nuevaOpcion.value = eps.id;
    nuevaOpcion.text = eps.nombre;
    select.add(nuevaOpcion);
  } 
}

async function cargarPensiones() {
  const select = document.querySelector("#txtPension");
  const respuesta = await fetch("/cargar/pensiones", {
    metod: "GET",
    headres: {
      Accept: "aplication/json",
      "Content-Type": "aplication/json",
    },
  });
  const pensiones = await respuesta.json();

  for (let pen of pensiones) {
    let nuevaOpcion = document.createElement("option");
    nuevaOpcion.value = pen.id;
    nuevaOpcion.text = pen.nombre;
    select.add(nuevaOpcion);
  } 
}


async function registrarEmpleado() {

  let identificacion = document.getElementById('txtIdentificacion').value;  
  let nombres = document.getElementById('txtNombres').value;
  let apellidos = document.getElementById('txtNombres').value;
  let salario = document.getElementById('txtSalario').value; 
  let cuenta_bancaria = document.getElementById('txtCuenta').value;
  let fecha_ingreso  = document.getElementById('txtFecIngreso').value;
  let fecha_nac = document.getElementById('txtFecNacimiento').value;
  let estado = 'ACTIVO';
  let telefono  = document.getElementById('txtTelefono').value;
  let correo = document.getElementById('txtEmail').value;
  let cargo_id = document.getElementById('txtCargo').value;
  let area_id = document.getElementById('txtArea').value;
  let tipo_contrato_id = document.getElementById('txtTipoContrato').value;  
  let banco_id = document.getElementById('txtBanco').value;
  let eps_id = document.getElementById('txtEps').value;
  let pensiones_id = document.getElementById('txtPension').value;
  let fondo_id = document.getElementById('txtFondoEmp').value;

  let datos = {
      "identificacion":identificacion,      
      "nombres":nombres,
      "apellidos":apellidos,
      "salario":salario,
      "cuenta_bancaria":cuenta_bancaria,
      "fecha_ingreso":fecha_ingreso,
      "fecha_nac":fecha_nac,
      "estado":estado,
      "telefono":telefono,
      "correo":correo,
      "cargo":cargo_id,
      "area":area_id,      
      "tipoContrato":tipo_contrato_id,      
      "banco":banco_id,
      "eps":eps_id,
      "pensiones":pensiones_id,
      "fondo":fondo_id      
  };

  const request = await fetch('/personal/registrar-persona', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  alert("¡El empleado fue creado con exito!");
  //window.location.href = 'tablaPersonal.html'
}