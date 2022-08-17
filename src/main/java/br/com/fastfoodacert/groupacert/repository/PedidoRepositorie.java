package br.com.fastfoodacert.groupacert.repository;

import br.com.fastfoodacert.groupacert.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorie extends JpaRepository<Pedido, Integer> {
}
