// Call the dataTables jQuery plugin
$(document).ready(function() {
   cargarPersonal();
  $('#tablaPersonal').DataTable();
});

async function cargarPersonal(){
  const respuesta = await fetch("/personal/personas", {
    metod: 'GET',
    headres: {
      'Accept' : 'aplication/json',
      'Content-Type':'aplication/json'
    } 
  });
  const personas = await respuesta.json();

  let listadoHtml = '';
  for(let per of personas){    
    let filaPersona = '<tr><td>'+per.id+'</td><td>'+per.identificacion+'</td><td>'+per.nombres+'</td><td>'+per.apellidos+'</td><td>'+per.telefono+'</td>'
        +'<td>'+per.correo+'</td><td>'+per.cargo.id+' - '+per.cargo.nombre+'</td><td>'+per.area.nombre+'</td><td>'+per.salario+'<td>'+per.tipoContrato.nombre+'</td>'
        +'<td>'+per.cuentaBancaria+'</td><td>'+per.banco.razonSocial+'</td><td>'+per.eps.nombre+'</td><td>'+per.pensiones.nombre+'</td>'
        +'<td>'+per.fondo.nombre+'</td><td>'+per.fechaIngreso+'</td><td>'+per.estado+'</td></tr>';
    
        listadoHtml += filaPersona;
  }
  document.querySelector('#tablaPersonal tbody').outerHTML = listadoHtml;
}