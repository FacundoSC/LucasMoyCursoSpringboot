
  async function iniciarSeccion(){
    let password = document.getElementById("txtPassword").value;
    let usuario ={
        email:document.getElementById("txtEmail").value,
        password
    };

    console.log(usuario);
    const url ="api/login";
    const cabeceras = {
                        method: 'POST',
                        body: JSON.stringify(usuario), 
                        headers: {
                          'Accept': 'application/json',
                          'Content-Type': 'application/json'
                        },
                      };
    
    const request = await fetch(url,cabeceras);
    const response = await request.text();
    if(response !=="FAIL"){
        localStorage.token = response;
        localStorage.email = usuario.email;
        //sessionStorage
        //CacheStorage
        
        window.location.href ="/usuarios.html";

    }else{
         alert("las creedenciales no son validas");
    }
   
  
   
  }
  
  
  
  
  
   
   
  