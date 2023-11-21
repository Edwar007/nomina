package com.innovatech.solution.nomina.service.impl;

import com.innovatech.solution.nomina.dta.Usuario;
import com.innovatech.solution.nomina.dto.UsuarioDTO;
import com.innovatech.solution.nomina.repository.impl.UsuarioRepository;
import com.innovatech.solution.nomina.service.UsuarioService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioServiceImp implements UsuarioService {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public String registrar(UsuarioDTO usuario) {
        Usuario user = Usuario.builder()
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .build();
        boolean res = usuarioRepository.existsByEmail(user.getEmail());
        System.out.println(res);
        if(!res){
            entityManager.merge(user);
        }
        return res ? "ya esta" : "creado";
    }
    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }
        String passwordHashed = lista.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return lista.get(0);
        }
        return null;
    }
}
