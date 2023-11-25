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
                + '</td> <td><button onclick="abrirVentana(' + per.id + ')">Actualizar</button></td>' + '<td><button onclick="eliminarRegistro(' + per.id + ')">Eliminar</button></td></tr>';

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
    alert(id);
}





