package br.com.fastfoodacert.groupacert.controller;

import br.com.fastfoodacert.groupacert.entities.dto.ClienteDTO;
import br.com.fastfoodacert.groupacert.entities.dto.ProdutoDTO;
import br.com.fastfoodacert.groupacert.entities.Cliente;
import br.com.fastfoodacert.groupacert.entities.Produto;
import br.com.fastfoodacert.groupacert.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Api("Api Produto")
public class ProdutoController {

    private final ProdutoService service;

    @GetMapping
    @ApiOperation("Buscar todos produtos")
    public ResponseEntity<List<Produto>> buscarTodosProdutos(){
        List<Produto> lista = service.buscarTodosProdutos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Buscar produto por ID.")
    public ResponseEntity<Produto> buscarProdutoId(@PathVariable Integer id){

        return ResponseEntity.ok().body(service.buscarProdutoId(id));
    }

    @PostMapping(value = "/cadastrar")
    @ApiOperation("Cadastrar produto.")
    public ResponseEntity<ClienteDTO> cadastrarProduto(@Valid @RequestBody ProdutoDTO dto){
        service.cadastrarProduto(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Atualizar produto.")
    public ResponseEntity<Cliente> atualizarProduto(@PathVariable Integer id,@Valid @RequestBody ProdutoDTO dto){
        service.atualizarProdutos(id, dto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Deletar produto.")
    public ResponseEntity<Cliente> deletarPedido(@PathVariable Integer id){
        service.buscarProdutoId(id);
        return ResponseEntity.noContent().build();
    }
}
