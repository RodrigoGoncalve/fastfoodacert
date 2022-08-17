package br.com.fastfoodacert.groupacert.service;

import br.com.fastfoodacert.groupacert.entities.dto.ProdutoDTO;
import br.com.fastfoodacert.groupacert.entities.Produto;

import java.util.List;

public interface ProdutoService {

    Produto buscarProdutoId(Integer id);

    List<Produto> buscarTodosProdutos();

    void atualizarProdutos(Integer id, ProdutoDTO dto);

    void deletarProduto(Integer id);

    Produto cadastrarProduto(ProdutoDTO cliente);
}
