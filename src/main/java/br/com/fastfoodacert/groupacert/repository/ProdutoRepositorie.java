package br.com.fastfoodacert.groupacert.repository;

import br.com.fastfoodacert.groupacert.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositorie extends JpaRepository<Produto, Integer> {
}
