package br.com.fastfoodacert.groupacert.service.impl;

import br.com.fastfoodacert.groupacert.entities.Usuario;
import br.com.fastfoodacert.groupacert.exceptions.ObjectNoutFoundException;
import br.com.fastfoodacert.groupacert.exceptions.SenhaInvalidaException;
import br.com.fastfoodacert.groupacert.repository.UsuarioRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private  PasswordEncoder encoder;

    @Autowired
    private  UsuarioRepositorie repositorie;


    @Transactional
    public Usuario salvar(Usuario usuario){
        return repositorie.save(usuario);
    }

    public UserDetails authenticar(Usuario usuario){
        UserDetails userDetails = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = encoder.matches(usuario.getSenha(), userDetails.getPassword());

        if (senhasBatem){
            return userDetails;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repositorie.findByLogin(username)
                .orElseThrow(()->
                        new ObjectNoutFoundException("Usuario não encontrado."));

        String [] roles = usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}