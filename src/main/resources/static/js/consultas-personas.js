$(document).ready(function () {
    cargarCargos();
    cargarAreas();
    cargarEstados();
});

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

async function cargarEstados() {
    const select = document.querySelector("#txtEstado");
    let nuevaOpcion1 = document.createElement("option");
    nuevaOpcion1.value = 1;
    nuevaOpcion1.text = "Activo";
    select.add(nuevaOpcion1);

    let nuevaOpcion2 = document.createElement("option");
    nuevaOpcion2.value = 2;
    nuevaOpcion2.text = "Desactivado";
    select.add(nuevaOpcion2);
}

function limpiar() {
    document.getElementById('txtIdentificacion').value = "";
    document.getElementById('txtApellidos').value = "";
    document.getElementById('txtArea').value = "";
    document.getElementById('txtCargo').value = "";
    document.getElementById('txtEstado').value = "";
    document.getElementById('txtSalDes').value = "";
    document.getElementById('txtSalHas').value = "";
    let tabla = document.querySelector('#tablaPagos tbody');
    tabla.innerHTML = '';
}

async function buscarPersonas() {

    let identificacion = document.getElementById('txtIdentificacion').value;
    let apellidos = document.getElementById('txtApellidos').value;
    let salarioDesde = document.getElementById('txtSalDes').value;
    let salarioHasta = document.getElementById('txtSalHas').value;

    let sEstado = document.querySelector("#txtEstado");
    let estado="";
    if(sEstado.selectedIndex > 0){
        estado = (sEstado.options[sEstado.selectedIndex]).text;
    }
    
    let sArea = document.querySelector("#txtArea");
    let area="";
    if(sArea.selectedIndex > 0){
        area = (sArea.options[sArea.selectedIndex]).text;
    }

    let sCargo = document.querySelector("#txtCargo");
    let cargo="";
    if(sCargo.selectedIndex > 0){
        cargo = (sCargo.options[sCargo.selectedIndex]).text;
    }

    let datos = {
        "identificacion": identificacion,
        "apellidos": apellidos,
        "salarioDesde": salarioDesde,
        "salarioHasta": salarioHasta,
        "estado": estado,
        "area": area,
        "cargo": cargo,
    };

    const respuesta = await fetch("/personal/busqueda-personas", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    let personas = await respuesta.json();

    let tabla = document.querySelector('#tablaPersonas tbody');
    if (personas.length === 0) {
        console.log("No hay datos para mostrar");
        // Si la tabla existe, limpiarla
        tabla.innerHTML = '';
    } else {
        console.log("Personas:", personas);
        let listadoHtml = '';
        for (let per of personas) {

            let filaPersona = '<tr><td>' + per.id + '</td><td>' + per.identificacion + '</td><td>' + per.nombres + '</td><td>' + per.apellidos + '</td><td>' + per.salario 
            + '</td><td>' + per.cuentaBancaria + '</td><td>' + per.fechaIngreso + '</td><td>' + per.fechaNac + '</td><td>' + per.fechaRetiro + '<td>' + per.estado + '</td><td>' + per.telefono 
            + '</td><td>' + per.correo + '</td><td>' + per.area.nombre + '</td><td>' + per.cargo.nombre + '</td><td>' + per.tipoContrato.nombre 
            + '</td><td>' + per.banco.razonSocial + '</td><td>' + per.eps.nombre + '</td><td>' +  per.pensiones.nombre + '</td>'
            + '<td><button onclick="actualizarPersona(' + per.id + ')">Actualizar</button></td>'   + '<td><button onclick="eliminarRegistro(' + per.id + ')">Eliminar</button></td></tr>'; 

            listadoHtml += filaPersona;
        }

        let tabla = document.querySelector('#tablaPersonas tbody');
        if (tabla) {
            tabla.outerHTML = listadoHtml;
        } else {
            // Crear la tabla si no existe
            let tablaNueva = document.createElement('tbody');
            tablaNueva.innerHTML = listadoHtml;
            document.querySelector('#tablaPersonas').appendChild(tablaNueva);
        }
    }
}