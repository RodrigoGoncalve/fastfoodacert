package br.com.fastfoodacert.groupacert.repository;

import br.com.fastfoodacert.groupacert.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorie extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);
}
