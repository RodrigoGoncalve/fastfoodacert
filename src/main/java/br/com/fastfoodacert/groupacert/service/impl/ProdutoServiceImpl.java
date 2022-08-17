package br.com.fastfoodacert.groupacert.service.impl;

import br.com.fastfoodacert.groupacert.entities.dto.ProdutoDTO;
import br.com.fastfoodacert.groupacert.entities.Produto;
import br.com.fastfoodacert.groupacert.exceptions.ObjectNoutFoundException;
import br.com.fastfoodacert.groupacert.repository.ProdutoRepositorie;
import br.com.fastfoodacert.groupacert.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepositorie repositorie;

    private final ModelMapper mapper;

    @Override
    public Produto buscarProdutoId(Integer id) {
        Optional<Produto> optional = repositorie.findById(id);
        return optional.orElseThrow(() -> new ObjectNoutFoundException(
                "Produto não encontrado na base de dados"));
    }

    @Override
    public List<Produto> buscarTodosProdutos() {
        return repositorie.findAll();
    }

    @Override
    public void atualizarProdutos(Integer id, ProdutoDTO dto) {
        buscarProdutoId(id);
        dto.setId(id);
        repositorie.save(mapper.map(dto, Produto.class));
    }

    @Override
    public void deletarProduto(Integer id) {
        buscarProdutoId(id);
        repositorie.deleteById(id);
    }

    @Override
    public Produto cadastrarProduto(ProdutoDTO dto) {
        return repositorie.save(mapper.map(dto, Produto.class));
    }
}
