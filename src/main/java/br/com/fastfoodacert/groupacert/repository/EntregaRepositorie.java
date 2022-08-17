package br.com.fastfoodacert.groupacert.repository;

import br.com.fastfoodacert.groupacert.entities.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepositorie extends JpaRepository<Entrega, Integer> {
}
