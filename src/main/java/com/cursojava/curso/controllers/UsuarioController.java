package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuario")
    public Usuario obtenerUsuario(@RequestParam Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("facundo");
        usuario.setApellido("cordoba");
        usuario.setEmail("cordobafs@gmail.com");
        usuario.setTeléfono("3815935909");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios" ,method = RequestMethod.GET)
    public List<Usuario> listarUsuarios(@RequestHeader(value = "Authorization") String token){
       return (validarToken(token))?usuarioDao.getUsuarios(): null;
        /*
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("facundo");
        usuario.setApellido("cordoba");
        usuario.setEmail("cordobafs@gmail.com");
        usuario.setTeléfono("3815935909");
        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNombre("roman");
        usuario2.setApellido("cordoba");
        usuario2.setEmail("rolicor@gmail.com");
        usuario2.setTeléfono("3815935909");
        List<Usuario> usuarios = Arrays.asList(usuario,usuario2);
        return usuarios;*/
    }

    private boolean validarToken(String token) {
        String id = jwtUtil.getKey(token);
        return (id != null);
    }

    @DeleteMapping(value ="api/usuarios/{id}" )
    public void eliminarUsuario(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {
        if(validarToken(token)){
            usuarioDao.eliminar(id);
        }
        else{
            System.out.println("token invalido  hacer algo diferente");
        }
    }


    /***
     * metodo del controlador usuario que sirve para verificar  si un  usuario esta  registrado en la BBDD
     *
     *
     * @param usuario
     */
    @PostMapping(value = "api/usuarios" )
    public void registrarUsuario(@RequestBody Usuario usuario){
        //@Requestbody transforma el json en  un objeto en java
         usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/buscarUsuario")
    public Usuario buscarUsuario(@RequestParam String id){
        Usuario usuario = new Usuario();
        usuario.setNombre("facundo");
        usuario.setApellido("corodoba");
        usuario.setEmail("cordobafs@gmail.com");
        usuario.setTeléfono("3815935909");
        return usuario;
    }



    @RequestMapping(value = "prueba/{id}")
    public List<String> prueba(@PathVariable int id){
        return List.of("Luna","Lala","Gorda","Petiso");
    }

    @RequestMapping("api/hello")
    ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @GetMapping("api/age")
    ResponseEntity<String> age( @RequestParam int year) {
        JSONObject responseJSON = new JSONObject();
        HttpStatus status;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if(year <2022){
            responseJSON.put("message","el usuario tiene "+(2021-year)+" años");
            responseJSON.put("status",HttpStatus.OK.toString());
            status =HttpStatus.OK;
        }else{
            responseJSON.put("message","el campo  año no puede ser mayor a el anio actual");
            responseJSON.put("status","NO_OK");
            status= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseJSON.toString(),headers,status);
    }


}
