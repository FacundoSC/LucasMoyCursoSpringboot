// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuario();
  $('#usuarios').DataTable();
});

function getHeaders(){
  return { 'Accept': 'application/json', 
  'Content-Type': 'application/json', 
  'Authorization': localStorage.token};
}


async function cargarUsuario(){
  const url ="api/usuarios";
  const cabeceras = {
                      method: 'GET',
                      headers: getHeaders(),
                    };
  const response = await fetch(url,cabeceras);
  const jsonResponse = await response.json();
  const $fragment = document.createDocumentFragment();
  
  jsonResponse.forEach(usuario => {
    const botonEliminar =`<a href="#" onclick=eliminar(${usuario.id}) class="btn btn-danger btn-icon-split"> <span class="text">Eliminar</span></a>`;
    
    const $tr = document.createElement("tr");
    
     $tr.innerHTML=`<td>${usuario.apellido}</td>
     <td>${usuario.nombre}</td><td>${usuario.email}</td><td>${usuario.telefono}</td>
     <td>
     <a href="#" class="btn btn-info btn-icon-split">
         <span class="text">Ver</span>
     </a>
     <a href="#" class="btn btn-primary btn-icon-split">
         <span class="text">Modificar</span>
     </a>
      ${botonEliminar}

 </td>`;
     $fragment.appendChild($tr);

  });


  document.querySelector("#usuarios tbody").appendChild($fragment);
}


async function eliminar(id){

  if(confirm("¿desea eliminar este usuario? ")){
      
  const url ="api/usuarios/"+id;
  const cabeceras = {
                      method: 'DELETE',
                      headers:getHeaders(),
                    };

  const response = await fetch(url,cabeceras);

  location.reload();

  }


 
 
}
