package br.com.fastfoodacert.groupacert.repository;

import br.com.fastfoodacert.groupacert.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorie extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByEmail(String email);
}
