package br.com.fastfoodacert.groupacert.controller;

import br.com.fastfoodacert.groupacert.entities.Pedido;
import br.com.fastfoodacert.groupacert.entities.dto.PedidoDTO;
import br.com.fastfoodacert.groupacert.service.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
@Api("Api Pedido")
public class PedidoController {

    private final PedidoService service;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cadastrar pedido.")
    public Integer criarPedido(@Valid  @RequestBody PedidoDTO dto){
        Pedido pedido = service.criarPedido(dto);
        return pedido.getId();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Busca pedido por ID.")
    public ResponseEntity<PedidoDTO>getPedidoId(@PathVariable Integer id){

        return ResponseEntity.ok().body(service.buscarPedidoId(id));
    }

    @GetMapping
    @ApiOperation("Busca todos pedidos.")
    public ResponseEntity<List<PedidoDTO>>buscarTodosPedidos(){
        List<PedidoDTO> listPedidos = service.buscarTodosPedidos();
        return ResponseEntity.ok().body(listPedidos);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar pedidos.")
    public ResponseEntity<PedidoDTO> deletar(@PathVariable Integer id){
        service.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Atualizar pedidos.")
    public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable Integer id,@Valid @RequestBody PedidoDTO dto){
        service.atualizarPedido(id, dto);
        return ResponseEntity.noContent().build();
    }
}
