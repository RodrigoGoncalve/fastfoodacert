package br.com.fastfoodacert.groupacert.controller;

import br.com.fastfoodacert.groupacert.entities.Entrega;
import br.com.fastfoodacert.groupacert.entities.dto.EntregaDTO;
import br.com.fastfoodacert.groupacert.service.EntregaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entrega")
@RequiredArgsConstructor
@Api("Api Entrega")
public class EntregaController {

    private final EntregaService service;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cadastro de uma nova entrega.")
    public Integer cadastrarEntrega(@Valid  @RequestBody EntregaDTO dto){
        Entrega entrega = service.cadastrarEntrega(dto);
        return entrega.getId();
    }

    @GetMapping("/{id}")
    @ApiOperation("busca entrega por ID.")
    public ResponseEntity<EntregaDTO> buscarEntregasPoId(@PathVariable Integer id){

        return ResponseEntity.ok().body(service.buscarEntregaPorId(id));
    }

    @GetMapping
    @ApiOperation("Busca em todas as entregas.")
    public ResponseEntity<List<EntregaDTO>> buscarTodosEntregas(){
        return ResponseEntity.ok().body(service.buscarTodasEntregas());
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar entrega.")
    public ResponseEntity<EntregaDTO> deletar(@PathVariable Integer id){
        service.deletarEntrega(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar entrega.")
    public ResponseEntity<EntregaDTO> atualizarEntrega(@PathVariable Integer id,@Valid @RequestBody EntregaDTO dto){
        service.atualizarEntrega(id, dto);
        return ResponseEntity.noContent().build();
    }
}
