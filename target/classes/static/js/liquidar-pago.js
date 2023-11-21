$(document).ready(function() {

});


window.onload = function () {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0');
    var yyyy = today.getFullYear();
    today = yyyy + '-' + mm + '-' + dd;
    document.getElementById("txtFecha").setAttribute("min", today);
    document.getElementById("txtFecha").setAttribute("max", today);
};

function validarCampo(id) {
    if (document.getElementById(id).value == "" || document.getElementById(id).value < 0) {
        return 0;
    } else {
        return document.getElementById(id).value;
    }
}

function mostrarDatos() {
    datos.style.display = 'block';
}

function mostrarTotales() {
    datos1.style.display = 'block';
}

async function calPrima(fecNom, salario) {

    const datos = {
        fecNom: fecNom,
        salario: salario
    };

    const request = await fetch('/pagonomina/cal-prima', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    const prima = await request.json();
    document.getElementById("txtPrima").value = prima;

}

function valIdentificacion() {
    let identificacion = document.getElementById("txtIdentificacion").value;
    let fecha = document.getElementById("txtFecha").value;
    if (identificacion == "") {
        alert("Digite identificacion");
    } else {
        if (fecha == "") {
            alert("Eliga una fecha");
        } else {
            usuario(identificacion)
        }
    }
}


async function usuario(identificacion) {
    const request = await fetch("/personal/buscar-persona/" + identificacion, {
        method: "GET",
        headers: { "Accept": "application/json", "Content-Type": "application/json" }

    });
    const per = await request.json();
    console.log("Persona: ", per);
    if (per.mensaje === "No se ha encontrado a nadie") {
        document.getElementById("txtIdentificacion").value = "";
        alert("No se ha encontrado a nadie");
    } else {
        setData(per);
    }

}

function setData(per) {

    let fecNom = document.getElementById("txtFecha").value;
    let salario = per.salario;
    calPrima(fecNom, salario);

    if (per.salario <= 2320000) {
        document.getElementById("txtSubTrans").value = 106454;
    } else {
        document.getElementById("txtSubTrans").value = 0;
    }

    document.getElementById("txtNombres").value = per.nombres;
    document.getElementById("txtApellidos").value = per.apellidos;
    document.getElementById("txtEmail").value = per.correo;
    document.getElementById("txtSalario").value = per.salario;
    document.getElementById("txtCuenta").value = per.cuentaBancaria;
    document.getElementById("txtCargo").value = per.cargo.nombre;
    document.getElementById("txtArea").value = per.area.nombre;
    document.getElementById("txtTipoContrato").value = per.tipoContrato.nombre;
    document.getElementById("txtBanco").value = per.banco.razonSocial;
    mostrarDatos();
}


async function calcularPago() {

    document.getElementById('txtGastosRep').readOnly = true;
    document.getElementById('txtViaticos').readOnly = true;
    document.getElementById('txtComision').readOnly = true;
    document.getElementById('txtHorExDiu').readOnly = true;
    document.getElementById('txtHorExNoc').readOnly = true;
    document.getElementById('txtHorExDiuDomFes').readOnly = true;
    document.getElementById('txtHorExNocDomFes').readOnly = true;


    let salario = validarCampo('txtSalario');
    let identificacion = validarCampo('txtIdentificacion');
    let fecha = validarCampo('txtFecha');
    let prima = validarCampo('txtPrima');
    let subTrans = validarCampo('txtSubTrans');
    let gastosRep = validarCampo('txtGastosRep');
    let viaticos = validarCampo('txtViaticos');
    let comisiones = validarCampo('txtComision');
    let horExDiu = validarCampo('txtHorExDiu');
    let horExNoc = validarCampo('txtHorExNoc');
    let horExDiuDomFes = validarCampo('txtHorExDiuDomFes');
    let horExNocDomFes = validarCampo('txtHorExNocDomFes');

    let datos = {
        "salario": salario,
        "idPersona": identificacion,
        "fecha": fecha,
        "prima": prima,
        "subTrans": subTrans,
        "gasRepre": gastosRep,
        "viaticos": viaticos,
        "comisiones": comisiones,
        "horExtDiu": horExDiu,
        "horExtNoc": horExNoc,
        "horasExtDiuDomFes": horExDiuDomFes,
        "horasExtNocDomFes": horExNocDomFes,
    };

    const request = await fetch('/pagonomina/calcular-pago', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    const pago = await request.json();
    document.getElementById("txtDesEps").value = pago.salud;
    document.getElementById("txtDesPen").value = pago.pension;
    document.getElementById("txtDesFon").value = pago.fonSol;
    document.getElementById("txtTotDev").value = pago.totDev;
    document.getElementById("txtTotDes").value = pago.totDes;
    document.getElementById("txtTotPagar").value = pago.pagoFinal;
    mostrarTotales();
    console.log("pago: ", pago);


}

async function crearPago() {

    //Se crea el pago
    let salario = validarCampo('txtSalario');
    let identificacion = validarCampo('txtIdentificacion');
    let fecha = validarCampo('txtFecha');
    let prima = validarCampo('txtPrima');
    let subTrans = validarCampo('txtSubTrans');
    let gastosRep = validarCampo('txtGastosRep');
    let viaticos = validarCampo('txtViaticos');
    let comisiones = validarCampo('txtComision');
    let horExDiu = validarCampo('txtHorExDiu');
    let horExNoc = validarCampo('txtHorExNoc');
    let horExDiuDomFes = validarCampo('txtHorExDiuDomFes');
    let horExNocDomFes = validarCampo('txtHorExNocDomFes');
    //Datos totales
    let eps = validarCampo('txtDesEps');
    let desPen = validarCampo('txtDesPen');
    let desFon = validarCampo('txtDesFon');
    let totDev = validarCampo('txtTotDev');
    let totDes = validarCampo('txtTotDes');
    let totPag = validarCampo('txtTotPagar');
    //Datos adicionales Jasper
    let nombres = document.getElementById("txtNombres").value;
    let apellidos = document.getElementById("txtApellidos").value;
    let cargo = document.getElementById("txtCargo").value;
    let cuenta = document.getElementById("txtCuenta").value;
    let banco = document.getElementById("txtBanco").value;

    let pago = {
        "idPersona": identificacion,
        "fecha": fecha,
        "salario": salario,
        "prima": prima,
        "subTrans": subTrans,
        "gasRepre": gastosRep,
        "viaticos": viaticos,
        "comisiones": comisiones,
        "horExtDiu": horExDiu,
        "horExtNoc": horExNoc,
        "horasExtDiuDomFes": horExDiuDomFes,
        "horasExtNocDomFes": horExNocDomFes,
        "salud": eps,
        "pension": desPen,
        "fonSol": desFon,
        "totDev": totDev,
        "totDes": totDes,
        "pagoFinal": totPag,
    };

    let jasper = {
        "cedula": identificacion,
        "fecha": fecha,
        "nombre": nombres + " " + apellidos,
        "cargo": cargo,
        "cuenta": cuenta,
        "banco": banco,
        "salario": salario,
        "totDev": totDev,
        "totDes": totDes,
        "totalPagar": totPag,
    };

    const request = await fetch('/pagonomina/crear-pago', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(pago)
    });

    const reques = await fetch('/pagonomina/crear-pdf', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jasper)
    });

    if (reques.ok) {
        
        const pdf = await reques.blob();
        const url = URL.createObjectURL(pdf);
        window.open(url);
        alert("Nomina Generada");
        location.reload();
    } else {
        alert("ha ocurrido un error");
        console.error('Error al obtener el PDF');
    }
    

}

