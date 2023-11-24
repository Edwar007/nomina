$(document).ready(function () {

});

let pagos;

async function buscarPago() {

    let identificacion = document.getElementById('txtIdentificacion').value;
    let fecha = document.getElementById('txtFecha').value;
    let desMin = document.getElementById('txtDesMin').value;
    let desMax = document.getElementById('txtDesMax').value;
    let devMin = document.getElementById('txtDevMin').value;
    let devMax = document.getElementById('txtDevMax').value;
    let pagMin = document.getElementById('txtPagMin').value;
    let pagMax = document.getElementById('txtPagMax').value;

    let datos = {
        "identificacion": identificacion,
        "fecha": fecha,
        "totDesDesde": desMin,
        "totDesHasta": desMax,
        "totDevDesde": devMin,
        "totDevHasta": devMax,
        "pagFinDesde": pagMin,
        "pagFinHasta": pagMax,
    };

    console.log("Pagos:", datos);
    const respuesta = await fetch("/pagonomina/busqueda-pagos", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    pagos = await respuesta.json();

    let tabla = document.querySelector('#tablaPagos tbody');
    if (pagos.length === 0) {
        console.log("No hay datos para mostrar");
        // Si la tabla existe, limpiarla
        tabla.innerHTML = '';
    } else {
        console.log("Pagos:", pagos);
        let listadoHtml = '';
        for (let per of pagos) {
            let filaPersona = '<tr><td>' + per.id + '</td><td>' + per.idPersona + '</td><td>' + per.fecha + '</td><td>' + per.pagoFinal + '</td><td>' + per.totDev + '</td>'
                + '<td>' + per.totDes + '</td><td>' + per.prima + '</td><td>' + per.subTrans + '</td><td>' + per.gasRepre + '<td>' + per.viaticos + '</td>'
                + '<td>' + per.comisiones + '</td><td>' + per.salud + '</td><td>' + per.pension + '</td><td>' + per.fonSol + '</td>'
                + '<td>' + per.horExtDiu + '</td><td>' + per.horExtNoc + '</td><td>' + per.horasExtDiuDomFes + '</td><td>' + per.horasExtNocDomFes  
                + '</td> <td><button onclick="actualizarPersona(' + per.id + ')">Actualizar</button></td>'   + '<td><button onclick="eliminarRegistro(' + per.id + ')">Eliminar</button></td></tr>';

            listadoHtml += filaPersona;
        }

        let tabla = document.querySelector('#tablaPagos tbody');
        if (tabla) {
            tabla.outerHTML = listadoHtml;
        } else {
            // Crear la tabla si no existe
            let tablaNueva = document.createElement('tbody');
            tablaNueva.innerHTML = listadoHtml;
            document.querySelector('#tablaPagos').appendChild(tablaNueva);
        }
    }
}

function limpiar() {
    document.getElementById('txtIdentificacion').value = "";
    document.getElementById('txtFecha').value = "";
    document.getElementById('txtDesMin').value = "";
    document.getElementById('txtDesMax').value = "";
    document.getElementById('txtDevMin').value = "";
    document.getElementById('txtDevMax').value = "";
    document.getElementById('txtPagMin').value = "";
    document.getElementById('txtPagMax').value = "";
    let tabla = document.querySelector('#tablaPagos tbody');
    tabla.innerHTML = '';
}

async function actualizarPersona(id) {

    var modal = document.getElementById("actualizarModal");
    modal.style.display = "block";
/*
    let registroBuscado = pagos.find(per => per.id === id);
    let identificacion = document.getElementById('txtIdentificacion').value;
    let nombres = document.getElementById('txtNombres').value;
    let apellidos = document.getElementById('txtApellidos').value;
    let salario = document.getElementById('txtSalario').value;
    let cuenta_bancaria = document.getElementById('txtCuenta').value;
    let fecha_ingreso = document.getElementById('txtFecIng').value;
    let fecha_nac = document.getElementById('txtFecNac').value;
    let telefono = document.getElementById('txtTelefono').value;
    let correo = document.getElementById('txtEmail').value;

    let sCargo = document.getElementById('txtCargo');
    let cargo_id = sCargo.options[sCargo.selectedIndex].value;
    let sArea = document.getElementById('txtArea');
    let area_id = sArea.options[sArea.selectedIndex].value;
    let sTc = document.getElementById('txtTipoContrato');
    let tipo_contrato_id = sTc.options[sTc.selectedIndex].value;
    let sBanco_id = document.getElementById('txtBanco');
    let banco_id = sBanco_id.options[sBanco_id.selectedIndex].value;
    let sEps_id = document.getElementById('txtEps');
    let eps_id = sEps_id.options[sEps_id.selectedIndex].value;
    let sPensiones_id = document.getElementById('txtPension');
    let pensiones_id = sPensiones_id.options[sPensiones_id.selectedIndex].value;

    let datos = {
        "id": id,
        "identificacion": identificacion,
        "nombres": nombres,
        "apellidos": apellidos,
        "salario": salario,
        "cuentaBancaria": cuenta_bancaria,
        "telefono": telefono,
        "correo": correo,
        "fechaIngreso": fecha_ingreso,
        "fechaNac": fecha_nac,
        "cargo": {
            "id": cargo_id
        },
        "area": {
            "id": area_id
        },
        "tipoContrato": {
            "id": tipo_contrato_id
        },
        "banco": {
            "id": banco_id
        },
        "eps": {
            "id": eps_id
        },
        "pensiones": {
            "id": pensiones_id
        },
    };

    const request = await fetch('/personal/actualizar-persona', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    if (request.ok) {
        alert("¡El empleado fue actualizado con exito!");
        location.reload();

    } else {
        alert("No se pudo actualizar al empleado");
    }
    */
}



