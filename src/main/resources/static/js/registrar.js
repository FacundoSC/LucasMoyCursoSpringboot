// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on ready
   
  });
  
  
  
  async function registrarUsuario(){
    const url ="api/usuarios";
    let password = document.getElementById("txtPassword").value;
    let repetirPassword = document.getElementById("txtRepetirPassword").value
    if(password !== repetirPassword){
         alert("las contraseñas son distintas");
         return false;
    }
    let usuario ={
        nombre:document.getElementById("txtNombre").value,
        apellido:document.getElementById("txtApellido").value,
        email:document.getElementById("txtEmail").value,
        telefono:"agregar Campo",
        password
    };

    const cabeceras = {
                        method: 'POST',
                        body: JSON.stringify(usuario), 
                        headers: {
                          'Accept': 'application/json',
                          'Content-Type': 'application/json'
                        },
                      };
    
    const response = await fetch(url,cabeceras);
    /*
    const jsonResponse = await response.json();
    console.log(jsonResponse);*/
   
  }
  
  
  
  
  
   
   
  