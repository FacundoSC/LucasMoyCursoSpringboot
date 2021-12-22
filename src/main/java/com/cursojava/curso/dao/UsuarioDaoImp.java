package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.EncryptorArgon2Util;
import com.cursojava.curso.utils.EncryptorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{
    // averiguar que hacer  esta anotacion
    @PersistenceContext
    private EntityManager entityManager; // sirve para hacer la conexión a la base de datos

    @Autowired
    @Qualifier("MD5Util")
    private EncryptorUtil encriptor;

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
       // Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
       // String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(encriptor.encriptarCadena(usuario.getPassword()));
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        /*
        //String inyeccionSql="' OR 1=1 --";
        String query = "FROM Usuario WHERE email =:email AND password=:password ";
        List<Usuario> usuarios = entityManager.createQuery(query).setParameter("email",usuario.getEmail()).setParameter("password",usuario.getPassword()).getResultList(); */
        // Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //encriptor.verificarIgualdadDeCadenas(passwordHashed,usuario.getPassword());
        String query = "FROM Usuario WHERE email =:email";
        List<Usuario> usuarios = entityManager.createQuery(query).setParameter("email",usuario.getEmail()).getResultList();
        if(usuarios.isEmpty()){
            return null;
        }
        Usuario elUsuario =usuarios.get(0);
        String passwordHashed = elUsuario.getPassword();
        boolean bandera = encriptor.verificarIgualdadDeCadenas(passwordHashed,usuario.getPassword());
        // el verify recibe como primer parametro la cadena hasheada y como segundo parametro la cadena  en texto plano y nos verifica si   el hash pertenece o no a la cadena  hasheada;
        return  bandera? elUsuario: null;
    }
}
